import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TimerTask;
import java.util.Timer;

public class Controller {

    private View view;
    private CardPile[] cardPiles;
    private List<Card> deck;
    private int movedCards;
    private Instant startTime;
    private int gameSeconds;
    private int gameMinutes;

    private int score;
    private int vegasScore;
    private boolean vegasContinous = false;
    private int scoreTime;
    private int draws = 0;

    private TimerTask gameTimer;

    public boolean timed = true;

    private boolean vegas = false;

    public Controller(){
        deck = createDeck();
        newGame();
        view = new View(this, cardPiles);
        startTimer();
        view.updateScore(score);
    }

    public CardPile[] getCardPiles() {
        return cardPiles;
    }

    public int getMovedCards() {
        return movedCards;
    }

    //Create, Shuffles and Distributes Cards into the piles
    public void newGame(){
        Collections.shuffle(deck);
        cardPiles = new CardPile[13];
        distributeCards(copyDeck(deck));

        score = 0;
        if(vegasContinous){
            vegasScore = vegasScore - 52;
        }
        else{
            vegasScore = -52;
        }

        if(view != null){
            startTimer();
            if(!vegas){
                view.updateScore(score);
            }
            else{
                view.updateVegasScore(vegasScore);
            }

        }
    }

    public void resetGame(){
        cardPiles = new CardPile[13];
        distributeCards(copyDeck(deck));

        startTimer();
        score = -50;
        if(vegasContinous){
            vegasScore = vegasScore - 52;
        }
        else{
            vegasScore = -52;
        }


        if(!vegas){
            view.updateScore(score);
        }
        else{
            view.updateVegasScore(vegasScore);
        }
    }

    private void startTimer(){
        if(gameTimer != null){
            gameTimer.cancel();
        }
        scoreTime = 0;
        gameTimer = new GameTimer(this);
        startTime = Instant.now();
        Timer timer = new Timer();
        timer.schedule(gameTimer,5,1000);
    }

    public void updateTime(Instant currTime){
        Duration time = Duration.between(startTime,currTime);
        gameMinutes = time.toMinutesPart();
        gameSeconds = time.toSecondsPart();
        view.updateTime(gameMinutes,gameSeconds);

        if(timed){
            int sec = scoreTime*10;
            if(time.getSeconds() >= sec + 10) {
                scoreTime++;
                score -= 2;
                if(!vegas){
                    view.updateScore(score);
                }
            }
        }
    }

    private List<Card> createDeck(){
        List<Card> pile = new ArrayList<>();
        Card tempCard;
        for(Card.Suits suit : Card.Suits.values()){
            for(Card.Values val : Card.Values.values()){
                tempCard = new Card(val, suit);
                tempCard.setSuit(suit);
                tempCard.setValue(val);
                tempCard.setVisibility(false);
                pile.add(tempCard);
            }
        }
        return pile;
    }

    private void distributeCards(List<Card> deck){
        int j = 7;
        Card tempCard;
        for(int i = 12; i >= 0; i--){
            cardPiles[i] = new CardPile();

            if(i > 1 && i <=5){
                cardPiles[i].setFoundation(true);

                Card.Suits suit;
                if(i == 2){
                    suit = Card.Suits.hearts;
                }
                else if(i == 3){
                    suit = Card.Suits.clubs;
                } else if (i == 4) {
                    suit = Card.Suits.diamonds;
                }
                else{
                    suit = Card.Suits.spades;
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

    private List<Card> copyDeck(List<Card> deck){
        List<Card> newDeck = new ArrayList<>();
        for(Card card : deck){
            newDeck.add(new Card(card.getValue(), card.getSuit()));
        }
        return newDeck;
    }

    //Snap card moving. Assumes position of card and pile starts at 0
    public int move(int cardPos, int pilePos){
        CardPile pile = cardPiles[pilePos];

        Card card;
        if(pilePos <= 5) {
            card = pile.getTopCard();
        }
        else{
            card = pile.getCard(cardPos);
        }

        if(pilePos == 0){
            if(cardPiles[0].isEmpty() && cardPiles[1].isEmpty()){
                return -1;
            }

            if(cardPiles[0].isEmpty() && !cardPiles[1].isEmpty()){
                if(vegas){
                    return -1;
                }
                Card currCard;
                while(!cardPiles[1].isEmpty()){
                    currCard = cardPiles[1].removeTopCard();
                    currCard.setVisibility(false);
                    cardPiles[0].addCard(currCard);
                }
                movedCards = 1;

                if(draws >= 1){
                    score = score -100;
                    if(!vegas){
                        view.updateScore(score);
                    }
                    else{
                        view.updateVegasScore(vegasScore);
                    }
                }
                draws ++;
                return 0;
            }

            card.setVisibility(true);
            if (cardPiles[1].addCard(card)) {
                pile.removeTopCard();
                movedCards = 1;
                return 1;
            }
            return -1;
        }

        if(card == null || !card.getVisibility()){
            return -1;
        }

        for(int i = 2; i < 13; i++){
            if(i == pilePos){
                continue;
            }

            if(checkCard(i, card)){
                if(cardPiles[i].getFoundation() && pile.getTopCard() != card){
                    continue;
                }

                cardScore(pilePos, i);

                Card currCard;
                List<Card> cards = new ArrayList<>();
                movedCards = 0;
                while((currCard = pile.removeTopCard()) != card){
                    movedCards++;
                    cards.add(currCard);
                }
                movedCards++;
                cards.add(currCard);

                while(cards.size() > 0){
                    currCard = cards.remove(cards.size()-1);
                    cardPiles[i].addCard(currCard);
                }

                if(!pile.isEmpty() && !pile.getTopCard().getVisibility()){
                    pile.getTopCard().setVisibility(true);
                    score += 5;
                }

                if(!vegas){
                    view.updateScore(score);
                }
                else{
                    view.updateVegasScore(vegasScore);
                }

                if(checkWin()){
                    endGame();
                }
                return i;
            }
        }
        return -1;
    }

    private boolean checkWin(){
        for(int i = 2; i <= 5; i++){
            if(cardPiles[i].size() != 13){
                return false;
            }
        }
        return true;
    }

    private void cardScore(int pilePos, int i){
        if(pilePos == 1){
            score += 5;
        }

        if(i <= 5){
            score += 10;
            vegasScore += 5;
        }

        if(pilePos != 1 && i > 5){
            score += 3;
        }

        if(pilePos <= 5 && pilePos > 1){
            score -= 15;
        }

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
            else return !pile.isEmpty() && getValueIndex(pile.getTopCard()) == val - 1;
        }
        return false;
    }

    private boolean checkTableau(CardPile pile, Card card){
        if(pile.isEmpty() && card.getValue() == Card.Values.king){
            return true;
        }
        if(pile.isEmpty()){
            return false;
        }

        int topColor = getColor(pile.getTopCard());
        int cardColor = getColor(card);

        if(topColor != cardColor){
            return getValueIndex(pile.getTopCard()) == getValueIndex(card) + 1;
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
        if(card.getSuit() == Card.Suits.hearts || card.getSuit() == Card.Suits.diamonds ){
            return 1;
        }
        else{
            return 0;
        }
    }

    public void setRegular(){
        vegas = false;
    }

    public void setVegas(){
        vegas = true;
    }

    public void setVegasContinous(boolean flag){vegasContinous = flag;}

    public void setVegasScore(int x){vegasScore = x;}

    public boolean getVegasContinous(){return vegasContinous;}

    private void endGame(){
        EndGame end = new EndGame(view);
        end.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        end.setSize(400, 250);
        end.setLocation(450, 300);
        end.getContentPane().setBackground(new Color(62, 210, 175));
        end.setVisible(true);
    }
}
