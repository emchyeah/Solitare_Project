

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;


public class View extends JFrame {

    private CardPile[] cardPiles;
    private final Controller controller;

    private JButton button;
    private JPanel deck;
    private JPanel foundations;
    private JPanel[] buttonPiles;
    // private JLabel imageLabel = new JLabel(new ImageIcon("/cardImages/green-poker.png"));
    static JMenuBar mB;
    static JMenu x;
    static JMenuItem m1;
    Popup popup;

    private static List<CardButton> litCards = new ArrayList<CardButton>();

    public View(Controller cont, CardPile[] piles) {
        super("Solitaire");
        this.cardPiles = piles;
        this.controller = cont;
        //Code to set JFrame and layout
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 1100, 800);
        this.setLayout(new FlowLayout());
        this.getContentPane().setBackground(new Color(2, 97, 19));
        createUI();

        //setting up menu bar
        mB = new JMenuBar();
        x = new JMenu("Help");
        m1 = new JMenuItem("Instructions");

        //setting up popup
        JFrame popFrame = new JFrame("Instructions");
        JLabel popLabel = new JLabel("Hello");
        PopupFactory pf = new PopupFactory();
        popFrame.setSize(400, 400);
        JPanel popPanel = new JPanel();
        popPanel.add(popLabel);
        popup = pf.getPopup(popFrame, popPanel, 180, 100);

        //popup window opens on menu option button click
        m1.addActionListener(e -> {
            popup.show();
        });

        x.add(m1);
        mB.add(x);
        this.add(mB);

    }

    private void createUI() {
        JPanel menus = new JPanel();
        JPanel decks = new JPanel();
        JPanel filler = new JPanel();
        JPanel found = new JPanel();
        JPanel filler2 = new JPanel();
        JPanel scorePanel = new RoundedPanel(20, Color.WHITE);
        decks.setLayout(new FlowLayout(FlowLayout.LEFT));
        decks.setBackground(new Color(2, 97, 19));
        menus.setBackground(new Color(2, 97, 19));
        menus.setLayout(new FlowLayout(FlowLayout.LEFT));
        menus.setPreferredSize(new Dimension(1050, 40));
        found.setLayout(new FlowLayout(FlowLayout.RIGHT));
        found.setBackground(new Color(2, 97, 19));
        filler.setLayout(new FlowLayout(FlowLayout.LEFT));
        filler.setPreferredSize(new Dimension(390, 35));
        filler.setBackground(new Color(2, 97, 19));
        filler2.setLayout(new FlowLayout(FlowLayout.LEFT));
        filler2.setPreferredSize(new Dimension(795, 10));
        filler2.setBackground(new Color(2, 97, 19));
        scorePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        scorePanel.setPreferredSize(new Dimension(80, 35));
        scorePanel.setBackground(new Color(2, 97, 19));


        deck = new JPanel();
        deck.setBackground(new Color(2, 97, 19));

        foundations = new JPanel();
        foundations.setBackground(new Color(2, 97, 19));

        //New game button
        button = new JButton("New Game");
        button.setLayout(new FlowLayout(FlowLayout.LEFT));
        button.setPreferredSize(new Dimension(100, 35));
        button.addActionListener(e -> newG("New Game"));

        //Label for "SCORE:" text
//        JLabel scoreLabel = new JLabel("SCORE:");
//        scoreLabel.setForeground(Color.WHITE);
//        scoreLabel.setLayout(new FlowLayout(FlowLayout.LEFT));

        //Panel that stores the score Label counter


        // Label of the actual score count
        JLabel score = new JLabel();


        scorePanel.add(score);
        menus.add(button);
        menus.add(filler2);
//        menus.add(scoreLabel);
        menus.add(scorePanel);
        decks.add(deck);
        decks.add(filler);
        decks.add(foundations);

        JPanel tableau = new JPanel();
        tableau.setLayout(new FlowLayout());
        tableau.setBackground(new Color(2, 97, 19));
        buttonPiles = new JPanel[7];

        for (int i = 0; i < 13; i++) {
            if (i <= 5) {
                Card card = cardPiles[i].getTopCard();
                CardButton cButton = new CardButton(card, i, cardPiles[i].size());
                cButton.setPreferredSize(new Dimension(100, 150));
                cButton.addActionListener(e -> cardButtonPress(cButton));
                // Code to set button location
                if (i == 0 || i == 1) {
                    deck.add(cButton);
                } else {
                    foundations.add(cButton);
                }
            } else {
                JPanel pile = new JPanel();
                pile.setLayout(null);
                pile.setBackground(new Color(2, 97, 19));
                Card[] cards = cardPiles[i].getList();
                for (int j = cardPiles[i].size() - 1; j >= 0; j--) {
                    Card card = cards[j];
                    CardButton cButton = new CardButton(card, i, j);
                    cButton.addActionListener(e -> cardButtonPress(cButton));
                    // Code to set button location
                    cButton.setBounds(25, j * 20, 100, 150);
                    pile.add(cButton);

                }
                pile.setPreferredSize(new Dimension(150, 1000));
                buttonPiles[i - 6] = pile;
                tableau.add(pile);
                pile.setVisible(true);

            }
        }



        this.add(menus);
        this.add(decks);
        JLabel line = new JLabel();
        line.setPreferredSize(new Dimension(30000, 0));
        this.add(line);
        this.add(tableau);
        menus.setVisible(true);
        decks.setVisible(true);
        filler.setVisible(true);
        found.setVisible(true);
        button.setVisible(true);
//        scoreLabel.setVisible(true);
        scorePanel.setVisible(true);
        deck.setVisible(true);
        foundations.setVisible(true);
        this.setVisible(true);
    }


    private void update(int pile) {
        if (pile <= 1) {
            updateDeck();
        }

        if (pile >= 2 && pile <= 5) {
            updateFoundations();
        }

        if (pile > 5) {
            updatePile(pile);
        }
    }

    private void updateDeck() {
        Component[] comps = deck.getComponents();
        int j = 0;
        CardButton cB;
        for (Component comp : comps) {
            if (comp.getClass() == CardButton.class) {
                cB = (CardButton) comp;
                cB.changeCard(cardPiles[j].getTopCard(), j, cardPiles[j].size() - 1);
                j++;
            }
        }
    }

    private void updateFoundations() {
        Component[] comps = foundations.getComponents();
        int j = 2;
        CardButton cB;
        for (Component comp : comps) {
            if (comp.getClass() == CardButton.class) {
                cB = (CardButton) comp;
                cB.changeCard(cardPiles[j].getTopCard(), j, cardPiles[j].size() - 1);
                j++;
            }
        }
    }

    private void updatePile(int pile) {
        Component[] comps = buttonPiles[pile - 6].getComponents();
        Card[] cards = cardPiles[pile].getList();

        int j = cards.length - 1;
        int movedCards = controller.getMovedCards();

        CardButton cB;
        for (Component comp : comps) {
            if (comp.getClass() == CardButton.class) {
                if (j < 0) {
                    buttonPiles[pile - 6].remove(comp);
                    continue;
                }

                cB = (CardButton) comp;
                if (!cB.checkCard(cards[j])) {
                    cB.changeCard(cards[j], pile, j);
                }
                cB.setBounds(25, j * 20, 100, 150);

                if(movedCards > 0){
                    cB.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                    litCards.add(cB);
                    movedCards--;
                }
                j--;
            }
        }


        for (int i = j; i >= 0; i--) {
            CardButton cButton = new CardButton(cards[i], pile, i);
            cButton.addActionListener(e -> cardButtonPress(cButton));
            // Code to set button location
            cButton.setBounds(25, i * 20, 100, 150);
            buttonPiles[pile - 6].add(cButton);
        }

        buttonPiles[pile - 6].updateUI();
    }

    private void cardButtonPress(CardButton cB) {
        int pile = controller.move(cB.pos, cB.pile);
        if (pile != -1) {
            update(pile);
            update(cB.pile);

            //adds green border on successful click
            //cB.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
        } else {
            //adds red border on unsuccessful click
            cB.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        }

    }

    // Method for re-setting the game
    private void newG(String s) {
        controller.newGame();
        cardPiles = controller.getCardPiles();
        update(0);
        update(2);
        update(6);
        update(7);
        update(8);
        update(9);
        update(10);
        update(11);
        update(12);
    }

    public static void clearLitCards(){
        for(CardButton cB : litCards){
            cB.setBorder(UIManager.getBorder("control"));
        }
        litCards.clear();
    }

    public class CardButton extends JButton {
        private Card.Suits suit;
        private Card.Values value;
        private boolean visible;

        private int pile;
        private int pos;


        public CardButton(Card card, int pile, int pos) {
            super();

            //adds black border to card buttons
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
            addMouseListener(new MouseAdapter() {
                @Override
                //adds blue border to button when hovered over
                public void mouseEntered(MouseEvent e) {
                    setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
                    View.clearLitCards();
                }

                @Override
                //removes border when mouse leaves button
                public void mouseExited(MouseEvent e) {
                    setBorder(UIManager.getBorder("control"));
                }
            });


            if (card == null) {
                this.suit = null;
                this.value = null;
                this.visible = false;
            } else {
                this.suit = card.getSuit();
                this.value = card.getValue();
                this.visible = card.getVisibility();
            }
            this.pile = pile;
            this.pos = pos;

            this.setSize(100, 150);

            Image img;
            if (card == null) {
                img = getCardOutline();
            } else if (visible) {
                img = setImage(value, suit);

            } else {
                img = getCardBack();
            }
            assert img != null;
            this.setIcon(resizeIcon(img, this.getWidth(), this.getHeight()));
            //makes the button just the image
            this.setBorderPainted(true);
            this.setContentAreaFilled(false);

        }

        public void changeCard(Card card, int pile, int pos) {
            if (card == null) {
                this.suit = null;
                this.value = null;
                this.visible = false;
            } else {
                this.suit = card.getSuit();
                this.value = card.getValue();
                this.visible = card.getVisibility();
            }

            this.pile = pile;
            this.pos = pos;

            Image img;
            if (card == null) {
                img = getCardOutline();
            } else if (visible) {
                img = setImage(value, suit);

            } else {
                img = getCardBack();
            }
            assert img != null;
            this.setIcon(resizeIcon(img, this.getWidth(), this.getHeight()));
            this.setBorderPainted(true);
            this.setContentAreaFilled(false);
        }

        private boolean checkCard(Card card) {
            return suit == card.getSuit() && value == card.getValue() && visible == card.getVisibility();
        }


        private Image setImage(Card.Values cardValue, Card.Suits suit) {
            //Code to set card image depending on suit,value and visibility
            String imageFile = cardValue + "_of_" + suit + ".png";
            ImageIcon icon;
            Image image;
            try {
                icon = new ImageIcon(Card.class.getResource("cardImages/" + imageFile));
                image = icon.getImage();
            } catch (NullPointerException e) {
                e.getMessage();
                return null;
            }
            return image;
        }


        //gets card outline to use in empty space
        private static Image getCardOutline() {
            ImageIcon icon = new ImageIcon(Card.class.getResource("cardImages/bottom01.png"));
            return icon.getImage();
        }

        //gets back of card image
        private static Image getCardBack() {
            ImageIcon icon = new ImageIcon(Card.class.getResource("cardImages/backOfCard.png"));
            return icon.getImage();
        }

        //resizes card image to fit in button
        private static Icon resizeIcon(Image img, int resizeWidth, int resizeHeight) {
            Image resizedImage = img.getScaledInstance(resizeWidth, resizeHeight, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        }

    }

    //Class for making rounded rectangle corners !!! lol
    class RoundedPanel extends JPanel {
        private Color backgroundColor;
        private int cornerRadius;

        public RoundedPanel(int radius, Color bgColor) {
            super();
            cornerRadius = radius;
            backgroundColor = bgColor;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Dimension arcs = new Dimension(cornerRadius, cornerRadius);
            int width = getWidth();
            int height = getHeight();
            Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            //Draws the rounded panel with borders.
            if (backgroundColor != null) {
                graphics.setColor(backgroundColor);
            } else {
                graphics.setColor(getBackground());
            }
            graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); //paint background
            graphics.setColor(getForeground());
            graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); //paint border
        }
    }

}