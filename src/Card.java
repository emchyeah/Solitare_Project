public class Card {

    private boolean visible;
    private Suits suit;
    private Values value;

    public enum Suits{
        Hearts, Diamonds, Clubs, Spades
    }

    public enum Values{
        Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten , Jack, Queen, King
    }

    public Suits getSuit(){
        return suit;
    }

    public void setSuit(Suits suit){
        this.suit = suit;
    }

    public Values getValue(){
        return value;
    }

    public void setValue(Values value){
        this.value = value;
    }

    public boolean getVisibility(){
        return visible;
    }

    public void setVisibility(boolean visible){
        this.visible = visible;
    }


}
