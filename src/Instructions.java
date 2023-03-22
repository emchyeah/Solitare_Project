import javax.swing.*;
import java.awt.*;

public class Instructions extends JDialog {
    JLabel label;

    public Instructions(JFrame frame){
        super(frame, "Instructions", true);
        setLayout(new FlowLayout());

        label = new JLabel(
                "<html>" +
                        "<U><B>Rank of Cards</B></U><br>" +
                        "The rank of cards in Solitaire games is: K (high), Q, J, 10, 9, 8, 7, 6, 5, 4, 3, 2, A (low).<br><br> " +

                        "<U><B>Types of Piles</B></U><br>" +
                        "There are four different types of piles in Solitaire:<br><ul>" +
                        "<li><I>The Tableau</I>: Seven piles that make up the main table.</li>" +
                        "<li><I>The Foundations</I>: Four piles on which a whole suit or sequence must be built up. In most Solitaire games, the<br>" +
                        "four aces are the bottom card or base of the foundations. The foundation piles are hearts, diamonds, spades, and clubs.</li>" +
                        "<li><I>The Stock (or “Hand”) Pile</I>: If the entire pack is not laid out in a tableau at the beginning of a game, the remaining<br>" +
                        "cards form the stock pile from which additional cards are brought into play according to the rules.</li>" +
                        "<li><I>The Talon (or “Waste”) Pile</I>: Cards from the stock pile that have no place in the tableau or on foundations<br>" +
                        "are laid face up in the waste pile.</li></ul>" +

                        "<U><B>How to Play</B></U><br>" +
                        "The initial array may be changed by \"building\" - transferring cards among the face-up cards in the tableau.<br>" +
                        "Certain cards of the tableau can be played at once, while others may not be played until certain blocking cards are<br>" +
                        "removed. For example, of the seven cards facing up in the tableau, if one is a nine and another is a ten, you may<br>" +
                        "transfer the nine to on top of the ten to begin building that pile in sequence. Since you have moved the nine from one<br>" +
                        "of the seven piles, you have now unblocked a face down card; this card can be turned over and now is in play.<br><br>" +

                        "As you transfer cards in the tableau and begin building sequences, if you uncover an ace, the ace should be placed in one<br>" +
                        "of the foundation piles. The foundations get built by suit and in sequence from ace to king.<br><br>" +

                        "Continue to transfer cards on top of each other in the tableau in sequence. If you can’t move any more face up cards, you can<br>" +
                        "utilize the stock pile by flipping over the first card. This card can be played in the foundations or tableau. If you cannot play<br>" +
                        "the card in the tableau or the foundations piles, move the card to the waste pile and turn over another card in the stock pile.<br><br>" +

                        "If a vacancy in the tableau is created by the removal of cards elsewhere it is called a “space”, and it is of major importance in<br>" +
                        "manipulating the tableau. If a space is created, it can only be filled in with a king. Filling a space with a king could potentially<br>" +
                        "unblock one of the face down cards in another pile in the tableau.<br><br>" +

                        "When you hover over a card, it will highlight in blue to indicate which card you are on. Cards that are successfully moved will highlight<br>" +
                        "green, unless moved to a foundation pile. Any unsuccessful moves will be highlight in red. Continue to transfer cards in the tableau and<br>" +
                        "bring cards into play from the stock pile until all the cards are built in suit sequences in the foundation piles to win!<br><br>" +

                        "<U><B>Scoring System</B></U><br>" +
                        "In Standard scoring (the default), you earn points based on time and moves:<ul>" +
                        "<li>10 points for each card moved to a suit stack.</li> " +
                        "<li>5 points for each card moved from the deck to a row stack.</li>" +
                        "<li>5 points for each card turned face-up in a row stack.</li>" +
                        "<li>3 points for each card moved from one row stack to another.</li>" +
                        "<li>-2 points for each 10 seconds elapsed during a timed game.</li>" +
                        "<li>-15 points for each card moved from a suit stack to a row stack.</li>" +
                        "<li>-100 points for each pass through the deck after one pass (Draw One option).</li>" +
                        "<li>You receive a bonus when you complete a timed game. The shorter the game, the larger the bonus.</li></ul>" +

                        "In Vegas scoring, your ante is $52 to begin playing each game:<ul>" +
                        "<li>The object is to earn more than you wagered.</li>" +
                        "<li>You earn $5 for each card moved to a suit stack.</li>" +
                        "<li>There is no time penalty or bonus awarded.</li>" +
                        "<li>If you select the Keep cumulative score option, you see a running total of your score from game to game.</li>" +
                        "<li>When you play using the Draw One option, you can go through the deck only once.</li>" +
                        "<li>When you play using the Draw Three option, you can go through the deck only three times.</li></ul>" +
                        "</html>");
        add(label);
    }

}
