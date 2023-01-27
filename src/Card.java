public class Card {

    private boolean visible;
    private String suit;
    private String value;

    private enum Suits{
        Hearts, Clubs, Spades, Diamonds
    }

    private enum Values{
        One, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten , Jack, Queen, King, Ace
    }

    public String getSuit(){
        return suit;
    }

    public void setSuit(String suit){
        this.suit = suit;
    }

    public String getValue(){
        return value;
    }

    public void setValue(String value){
        this.value = value;
    }

    public boolean getVisibility(){
        return visible;
    }

    public void setVisibility(boolean visible){
        this.visible = visible;
    }


}
