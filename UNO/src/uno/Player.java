/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;


/**
 *
 * @author maist
 */
public class Player {

    private ArrayList<Card> hand;
    private int AInumber;
    private int score;
    private Card chosenCard;
    
    public Player(int nb) {
        AInumber = nb;
        chosenCard = null;
        hand = new ArrayList<Card>();
        score=0;
    }
    public ArrayList<Card> getHand(){
        return hand;
    }
    public void setScore(int newScore)
    {
        score=newScore;
    }
    public int getScore()
    {
        return score;
    }
    public void setChosenCard(Card newChosenCard)
    {
        chosenCard=newChosenCard;
    }
    public Card getChosenCard()
    {
        return chosenCard;
    }
    public boolean playTurnPlayer(Game g) {
        System.out.println("It's Player" + (AInumber + 1) + " turn");
        //Scanner keyboard = new Scanner(System.in);
        for (int i = 0; i < hand.size(); ++i) {
            System.out.print(i + " [" + hand.get(i).displayColour() + hand.get(i).getSymbol() + "]   ");
        }
        System.out.println("");
        boolean play = true;
        boolean stop = false;

        do{
        if (this.canPlay(g)) {
            System.out.println("Which card do you want to play ? ");
            setChosenCard(cardChoice(g));
            JOptionPane.showMessageDialog(null,"Played Card : " + getChosenCard().displayColour() + " " + getChosenCard().getSymbol());
            stop=true;
            System.out.println("You chose your card");
        } 
        else {
            JOptionPane.showMessageDialog(null, "You draw a card");
            draw(g, 1);
            play = false;
            stop =true;

        }
        }while(!stop);
        
        if (play) {
            chosenCard.play(g);
            g.returnToDeck(g.getUpperCard());
            g.setUpperCard(chosenCard);
            hand.remove(chosenCard);
        }
        if (hand.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean playTurnAI(Game g) {
        Random r = new Random();
        int counter = 0;
        boolean stop = false, play = true;
        int playedCard = r.nextInt((hand.size() - 0)) + 0;
        do {
            playedCard = r.nextInt((hand.size() - 0)) + 0;
            if (counter == hand.size() - 1) {
                draw(g, 1);
                stop = true;
                play = false;
                JOptionPane.showMessageDialog(null, "AI" + (AInumber + 1) + " drew a card");
                System.out.println("AI" + (AInumber + 1) + " drew a card");
            } else {
                stop = hand.get(playedCard).canPlayOn(g.getUpperCard());
            }
            counter++;
        } while (!stop);
        JOptionPane.showMessageDialog(null,"\nAI" + (AInumber + 1) + " played : [" + hand.get(playedCard).displayColour() + hand.get(playedCard).getSymbol() + "]"
                +"\nAI" + (AInumber+1) + " got " + hand.size() + " cards in his hand\n");
        if (play) {
            if (hand.get(playedCard).getSymbol() != 'W' && hand.get(playedCard).getSymbol() != '+') {
                hand.get(playedCard).play(g);
            } else if (hand.get(playedCard).getSymbol() == '+') {
                WildDrawCard card = (WildDrawCard) hand.get(playedCard);
                card.play(g, r.nextInt((4 - 1)) + 1);
            } else if (hand.get(playedCard).getSymbol() == 'W') {
                WildCard card = (WildCard) hand.get(playedCard);
                card.play(g, r.nextInt((4 - 1)) + 1);
            }
            g.returnToDeck(g.getUpperCard());
            g.setUpperCard(hand.get(playedCard));
            hand.remove(playedCard);
        }
        //System.out.println("\nAI" + (AInumber+1) + " got " + hand.size() + " cards in his hand\n");
        counter = 0;
        if (hand.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void draw(Game g, int nbCard) {
        for (int j = 0; j < nbCard; ++j) {
            hand.add(g.getDeck().get(j));
            g.getDeck().remove(j);
        }
    }

    public boolean canPlay(Game g) {
        boolean possible = false;

        for (Card hand1 : hand) {
            if (hand1.canPlayOn(g.getUpperCard())) {
                possible = true;
            }
        }

        return possible;
    }
    public int getNumber(){
        return AInumber;
    }
    public Card cardChoice(final Game g) {
        Card prev =this.getChosenCard();
        
            Graphic graph = new Graphic(g,this);
            do{
            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
        }while(prev==this.getChosenCard());
            graph.dispose();
        return this.getChosenCard();
    }
    public int computeHandScore()
    {
        int handScore=0;
        for(Card c: hand)
        {
            handScore+=c.getValue();
        }
        return handScore;
    }
}
