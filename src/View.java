
import javax.swing.*;
import java.awt.*;

public class View extends JFrame{

    private CardPile[] cardPiles;
    private Controller controller;

    private JToolBar toolBar;

    private JPanel decks;
    private JPanel tableau;


    public View(Controller cont, CardPile[] piles){
        super("Solitaire");
        this.cardPiles = piles;
        this.controller = cont;

        //Code to set JFrame and layout
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 1100, 800);
        this.setLayout(new FlowLayout());
        this.getContentPane().setBackground(Color.green);
        update();
    }

    public void update(){

        if(decks != null){
            this.remove(decks);
        }
        if(tableau != null){
            this.remove(tableau);
        }

        decks = new JPanel();
        decks.setLayout(new FlowLayout());
        decks.setBackground(Color.green);
        JPanel deck = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel foundations = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        decks.add(deck);
        decks.add(foundations);

        tableau = new JPanel();
        tableau.setLayout(new FlowLayout());
        tableau.setBackground(Color.green);

        for(int i = 0; i < 13; i++){
            if(i >= 0 && i <= 5){
                Card card = cardPiles[i].getTopCard();
                CardButton cButton = new CardButton(card,i,cardPiles[i].size());
                cButton.addActionListener(e -> cardButtonPress(cButton));
                // Code to set button location
                if(i == 0 || i == 1){
                    deck.add(cButton);
                }
                else{
                    foundations.add(cButton);
                }
            }
            else{
                JPanel pile = new JPanel();
                pile.setLayout(null);
                Card[] cards = cardPiles[i].getList();
                for(int j = cardPiles[i].size()-1; j >= 0; j--){
                    Card card = cards[j];
                    CardButton cButton = new CardButton(card,i,j);
                    cButton.addActionListener(e -> cardButtonPress(cButton));
                    // Code to set button location
                    cButton.setBounds(25,j*20,100,150);
                    pile.add(cButton);

                }
                pile.setPreferredSize(new Dimension(150,1000));
                tableau.add(pile);
                pile.setVisible(true);

            }
        }

        this.add(decks);
        JLabel line = new JLabel();
        line.setPreferredSize(new Dimension(30000,0));
        this.add(line);
        this.add(tableau);
        decks.setVisible(true);
        deck.setVisible(true);
        foundations.setVisible(true);
        this.setVisible(true);
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
            if(card == null){
                this.suit = null;
                this.value = null;
                this.visible = false;
            }
            else{
                this.suit = card.getSuit();
                this.value = card.getValue();
                this.visible = card.getVisibility();
            }
            this.pile = pile;
            this.pos = pos;

            this.setSize(100, 150);

            Image img;
            if(card == null){
                img = getCardOutline();
            }
            else if(visible){
                img = setImage(value, suit);

            } else{
                img = getCardBack();
            }
            this.setIcon(resizeIcon(img, this.getWidth(), this.getHeight()));
            //makes the button just the image
            this.setBorderPainted(false);
            this.setContentAreaFilled(false);

        }


        public Image setImage(Card.Values cardValue, Card.Suits suit) {
            //Code to set card image depending on suit,value and visibility
            String imageFile = cardValue + "_of_" + suit + ".png";
            ImageIcon icon = null;
            Image image;
            try {
                icon = new ImageIcon(Card.class.getResource("cardImages/" + imageFile));
                image = icon.getImage();
            } catch (NullPointerException e) {
                e.getMessage();
                return null;
            }
            return image;
        }


        //gets card outline to use in empty space
        public static Image getCardOutline() {
            ImageIcon icon = new ImageIcon(Card.class.getResource("cardImages/bottom01.png"));
            Image image = icon.getImage();
            return image;
        }

        //gets back of card image
        public static Image getCardBack() {
            ImageIcon icon = new ImageIcon(Card.class.getResource("cardImages/backOfCard.png"));
            Image image = icon.getImage();
            return image;
        }

        //resizes card image to fit in button
        private static Icon resizeIcon(Image img, int resizeWidth, int resizeHeight){
            Image resizedImage = img.getScaledInstance(resizeWidth, resizeHeight, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        }

    }
}
