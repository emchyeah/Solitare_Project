import java.util.ArrayList;
import java.util.List;

public class CardPile {

    private List<Card> cards;
    private boolean ordered;
    private boolean foundation;

    private Card.Suits foundationSuit;
    private Card card;

    public CardPile(){
        cards = new ArrayList<Card>();
    }

    public boolean getOrdered(){
        return ordered;
    }

    public void setOrdered(Boolean ordered){
        this.ordered = ordered;
    }

    public boolean getFoundation(){return foundation;}

    public void setFoundation(Boolean foundation){this.foundation = foundation;}

    public Card.Suits getSuit(){return foundationSuit;}

    public void setSuit(Card.Suits suit){foundationSuit = suit;}

    public Card[] getList(){
        return cards.toArray(new Card[0]);
    }

    public Card getTopCard(){
        int size = cards.size()-1;
        if (size == -1){
            return null;
        }
        return getCard(size);
    }

    public Card getCard(int cardPos){
        return cards.get(cardPos);
    }

    public boolean isEmpty(){
        return cards.isEmpty();
    }

    public int size(){
        return cards.size();
    }

    public Card removeTopCard(){
        return cards.remove(cards.size()-1);
    }

    public boolean addCard(Card card){
        return cards.add(card);
    }
    // This is the private class of addCard, renamed to not conflict with public addCard
    private boolean addCardTwo(Card card){
        return false;
    }

    private boolean addCardOrdered(Card card){
        return true;
    }

    public boolean setTopCardVisibility(){
        return true;
    }




}
