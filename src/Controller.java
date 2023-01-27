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

                Card.Suits suit;
                if(i == 2){
                    suit = Card.Suits.Hearts;
                }
                else if(i == 3){
                    suit = Card.Suits.Clubs;
                } else if (i == 4) {
                    suit = Card.Suits.Diamonds;
                }
                else{
                    suit = Card.Suits.Spades;
                }

                cardPiles[i].setSuit(suit);
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

        if(card == null || !card.getVisibility()){
            return false;
        }

        if(pilePos == 0){
            if(cardPiles[0].isEmpty() && !cardPiles[1].isEmpty()){
                Card currCard;
                while(!cardPiles[1].isEmpty()){
                    currCard = cardPiles[1].removeTopCard();
                    currCard.setVisibility(false);
                    cardPiles[0].addCard(currCard);
                }
            }

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
                if(cardPiles[i].getFoundation() && pile.getTopCard() != card){
                    return false;
                }

                Card currCard;
                List<Card> cards = new ArrayList<Card>();
                while((currCard = pile.removeTopCard()) != card){
                    cards.add(currCard);
                }

                currCard = null;
                while(cards.size() > 0){
                    currCard = cards.remove(cards.size());
                    boolean test = cardPiles[i].addCard(currCard);
                }

                if(!pile.getTopCard().getVisibility()){
                    pile.getTopCard().setVisibility(true);
                }
                return true;
            }
        }
        return false;
    }


    private boolean checkCard(int pilePos, Card card){
        CardPile pile = cardPiles[pilePos];
        if(pile.getFoundation()){
            return checkFoundations(pile, card);
        }
        else{
            return checkTableau(pile, card);
        }
    }


    private boolean checkFoundations(CardPile pile, Card card){
        if(pile.getSuit() == card.getSuit()) {
            int val = getValueIndex(card);
            if(val == 0 && pile.isEmpty()){
                return true;
            }
            else if(getValueIndex(pile.getTopCard()) == val-1){
                return true;
            }
        }
        return false;
    }

    private boolean checkTableau(CardPile pile, Card card){
        if(pile.isEmpty() && card.getValue() == Card.Values.King){
            return true;
        }

        int topColor = getColor(pile.getTopCard());
        int cardColor = getColor(card);

        if(topColor != cardColor){
            if(getValueIndex(pile.getTopCard()) == getValueIndex(card)+1){
                return true;
            }
        }
        return false;
    }

    private int getValueIndex(Card card){
        Card.Values[] val = Card.Values.values();
        for(int i = 0; i < 13; i++){
            if(val[i] == card.getValue()){
                return i;
            }
        }
        return -1;
    }

    private int getColor(Card card){
        if(card.getSuit() == Card.Suits.Hearts || card.getSuit() == Card.Suits.Diamonds ){
            return 1;
        }
        else{
            return 0;
        }
    }
}
