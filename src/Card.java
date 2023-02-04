
import javax.swing.*;
import java.awt.*;

public class Card {

    private boolean visible;
    private Suits suit;
    private Values value;
    private Image image;

    public Card(Values value, Suits suit) {
        setValue(value);
        setSuit(suit);

        String imageFile = value + "_of_" + suit + ".png";
        ImageIcon icon = null;
        try {
            icon = new ImageIcon(Card.class.getResource("images/" + imageFile));
            image = icon.getImage();
        } catch (NullPointerException e) {
            e.getMessage();
        }
    }

    public enum Suits {
        hearts, diamonds, clubs, spades
    }

    public enum Values {
        ace, two, three, four, five, six, seven, eight, nine, ten, jack, queen, king
    }

    public Suits getSuit() {
        return suit;
    }

    public void setSuit(Suits suit) {
        this.suit = suit;
    }

    public Values getValue() {
        return value;
    }

    public void setValue(Values value) {
        this.value = value;
    }

    public boolean getVisibility() {
        return visible;
    }

    public void setVisibility(boolean visible) {
        this.visible = visible;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
