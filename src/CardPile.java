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

    public List getList(){
        return cards;
    }

    public Card getTopCard(){
        return getCard(cards.size());
    }

    public Card getCard(int cardPos){
        return cards.get(cardPos);
    }

    public boolean isEmpty(){
        return cards.isEmpty();
    }

    public boolean removeTopCard(){
        return true;
    }

    public boolean addCard(Card card){
         return addCardTwo(card);
    }
    // This is the private class of addCard, renamed to not conflict with public addCard
    private boolean addCardTwo(Card card){
        return cards.add(card);
    }

    private boolean addCardOrdered(Card card){
        return true;
    }

    public boolean setTopCardVisibility(){
        return true;
    }




}
