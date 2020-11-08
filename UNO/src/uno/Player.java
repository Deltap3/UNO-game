/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;


/*
    Juliette DANIEL et Pierre MAISTERRENA
    ING3 ex ING2 TDE02
 */
public class Player {

    private ArrayList<Card> hand;
    //each player and AI have a number that we display so in multiplayer
    //all players can know when it's their turn
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
    //manage one turn of one player
    public boolean playTurnPlayer(Game g) {
        //we display his hand
        System.out.println("It's Player" + (AInumber + 1) + " turn");
        for (int i = 0; i < hand.size(); ++i) {
            System.out.print(i + " [" + hand.get(i).displayColour() + hand.get(i).getSymbol() + "]   ");
        }
        System.out.println("");
        boolean play = true;
        boolean stop = false;
        //we create a button for each playable card
        do{
            if (this.canPlay(g)) {
                System.out.println("Which card do you want to play ? ");
                //we wait for the player to choose a card to play
                setChosenCard(cardChoice(g));
                JOptionPane.showMessageDialog(null,"Played Card : " + getChosenCard().displayColour() + " " + getChosenCard().getSymbol());
                stop=true;
                System.out.println("You chose your card");
            } 
            //if there's no playable card the player draw a card
            else {
                JOptionPane.showMessageDialog(null, "You draw a card");
                draw(g, 1);
                play = false;
                stop =true;
            }
        }while(!stop);
        //the card is played
        if (play) {
            chosenCard.play(g);
            g.returnToDeck(g.getUpperCard());
            g.setUpperCard(chosenCard);
            hand.remove(chosenCard);
        }
        //if the players win the game stops
        if (hand.size() == 0)
            return true;
        //if he doesn't the game continues
        else
            return false;
    }
    //manage one turn of one AI
    public boolean playTurnAI(Game g) {
        Random r = new Random();
        int counter = 0;
        boolean stop = false, play = true;
        //we make him choose a random card within the playable cards
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
        //we play the selected card
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
        counter = 0;
        //if the AI win the game stops
        if (hand.size() == 0)
            return true;
        //if the AI win the game stops
        else
            return false;
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
    //wait until the player choose a card
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
