
import javax.swing.*;
import java.awt.*;

public class View {

    private CardPile[] cardPiles;
    private Controller controller;

    private JFrame f;
    private JPanel panel;


    public View(Controller cont, CardPile[] piles){

        this.cardPiles = piles;
        this.controller = cont;
        f = new JFrame("Solitaire");
        //Code to set JFrame and layout
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setBounds(100, 100, 1100, 800);
        f.setVisible(true);
        f.setBackground(Color.green);
        update();
    }

    public void update(){

        //Code To clear Current Cards

        for(int i = 0; i < 13; i++){
            if(i >= 0 && i <= 5){
                Card card = cardPiles[i].getTopCard();
                CardButton cButton = new CardButton(card,i,cardPiles[i].size());
                cButton.addActionListener(e -> cardButtonPress(cButton));
                // Code to set button location
            }
            else{
                int j = 0;
                for(Card card : cardPiles[i].getList()){
                    CardButton cButton = new CardButton(card,i,j);
                    cButton.addActionListener(e -> cardButtonPress(cButton));
                    // Code to set button location

                    j++;
                }
            }
        }
    }

    private void cardButtonPress(CardButton cB){
        if(controller.move(cB.pos, cB.pile)){
            update();
        }
    }

    public class CardButton extends JButton{
        private Card.Suits suit;
        private Card.Values value;
        private boolean visible;

        private int pile;
        private int pos;


        public CardButton(Card card, int pile, int pos){
            super();
            this.suit = card.getSuit();
            this.value = card.getValue();
            this.visible = card.getVisibility();
            this.pile = pile;
            this.pos = pos;
            this.setSize(100, 150);

            if(visible = true){

                this.setIcon(resizeIcon(setImage(value, suit), this.getWidth(), this.getHeight()));
                //makes the button just the image
                this.setBorderPainted(false);
                this.setContentAreaFilled(false);
            } else{
                getCardBack();
            }

        }


        public ImageIcon setImage(Card.Values cardValue, Card.Suits suit) {
            //Code to set card image depending on suit,value and visibility
            String imageFile = cardValue + "_of_" + suit + ".png";
            ImageIcon icon = null;
            try {
                icon = new ImageIcon(Card.class.getResource("images/" + imageFile));
            } catch (NullPointerException e) {
                e.getMessage();
            }
            return icon;
        }


        //gets card outline to use in empty space
        public static Image getCardOutline() {
            ImageIcon icon = new ImageIcon(Card.class.getResource("images/bottom01.gif"));
            Image image = icon.getImage();
            return image;
        }

        //gets back of card image
        public static Image getCardBack() {
            ImageIcon icon = new ImageIcon(Card.class.getResource("images/backOfCard.png"));
            Image image = icon.getImage();
            return image;
        }

        //resizes card image to fit in button
        private static Icon resizeIcon(ImageIcon icon, int resizeWidth, int resizeHeight){
            Image img = icon.getImage();
            Image resizedImage = img.getScaledInstance(resizeWidth, resizeHeight, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        }

    }
}
