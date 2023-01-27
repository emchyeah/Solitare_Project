import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Controller {

    private CardPile[] cardPiles;
    private Object View;

    public Controller(){
        newGame();
        //create View here
    }

    //Create, Shuffles and Distributes Cards into the piles
    public void newGame(){
        cardPiles = new CardPile[13];
        List<Card> deck = createDeck();
        distributeCards(deck);

    }

    private List<Card> createDeck(){
        List<Card> pile = new ArrayList<Card>();
        Card tempCard = null;
        for(Card.Suits suit : Card.Suits.values()){
            for(Card.Values val : Card.Values.values()){
                tempCard = new Card();
                tempCard.setSuit(suit);
                tempCard.setValue(val);
                tempCard.setVisibility(false);
                pile.add(tempCard);
            }
        }

        Collections.shuffle(pile);
        return pile;
    }

    private void distributeCards(List<Card> deck){
        int j = 7;
        Card tempCard = null;
        for(int i = 12; i >= 0; i--){
            cardPiles[i] = new CardPile();

            if(i > 1 && i <=5){
                cardPiles[i].setFoundation(true);
                continue;
            }
            else if(i == 1) {
                continue;
            }
            else if(i == 0){
                for(Card card : deck){
                    cardPiles[i].addCard(card);
                }
                continue;
            }

            for (int k = 0; k < j; k++){
                tempCard = deck.get(0);
                if(k == j-1){
                    tempCard.setVisibility(true);
                }

                cardPiles[i].addCard(tempCard);
                deck.remove(0);
            }
            cardPiles[i].setOrdered(true);
            j--;
        }
    }

    //Snap card moving. Assumes position of card and pile starts at 0
    public boolean move(int cardPos, int pilePos){
        CardPile pile = cardPiles[pilePos];

        Card card;
        if(pilePos >= 0 && pilePos <= 5) {
            card = pile.getTopCard();
        }
        else{
            card = pile.getCard(cardPos);
        }

        if(card == null){
            return false;
        }

        if(pilePos == 0){
            card.setVisibility(true);
            if (cardPiles[1].addCard(card)) {
                pile.removeTopCard();
                return true;
            }
            return false;
        }

        for(int i = 2; i < 13; i++){
            if(i == pilePos){
                continue;
            }

            if(checkCard(i, card)){

            }
        }
        return false;
    }


    private boolean checkCard(int pilePos, Card card){
        return false;
    }
}
