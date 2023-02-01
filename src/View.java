
import javax.swing.*;

public class View {

    private CardPile[] cardPiles;
    private Controller controller;

    private JFrame f;
    private JPanel panel;

    public View(Controller cont, CardPile[] piles){

        this.cardPiles = piles;
        this.controller = cont;
        f = new JFrame();
        //Code to set JFrame and layout
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
        controller.move(cB.pos, cB.pile);
        update();
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

            setImage();
        }

        private void setImage(){
            //Code to set card image depending on suit,value and visibility
        }

    }
}
