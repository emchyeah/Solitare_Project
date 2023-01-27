import java.util.List;

public class CardPile {

    private List<Card> cards;
    private boolean ordered;
    private Card card;

    public boolean getOrdered(){
        return ordered;
    }

    public void setOrdered(Boolean ordered){
        this.ordered = ordered;
    }

    public List getList(){
        return cards;
    }

    public Card getTopCard(){
        return card;
    }

    public boolean removeTopCard(){
        return true;
    }

    public boolean addCard(Card card){
        return true;
    }
    // This is the private class of addCard, renamed to not conflict with public addCard
    private boolean addCardTwo(Card card){
        return true;
    }

    private boolean addCardOrdered(Card card){
        return true;
    }

    public boolean setTopCardVisibility(){
        return true;
    }




}
