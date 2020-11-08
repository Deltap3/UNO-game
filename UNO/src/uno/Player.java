/*
    UNO GAME
    Juliette DANIEL et Pierre MAISTERRENA
    ING3 ex ING2 TDE02
 */
package uno;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;


//manages each player's actions
//from chosing a card to playing it
public class Player {

    //attributes
    private ArrayList<Card> hand;//the player's hand
    //each player and AI have a number that we display so that
    //all players can know when it's their turn
    private int AInumber;
    private int score;//player score (including previous rounds)
    private Card chosenCard;//card that the player will play
    
    //constructor
    public Player(int nb) {
        AInumber = nb;
        chosenCard = null;
        hand = new ArrayList<Card>();
        score=0;
    }
    //accessors
    //returns the player's hand
    public ArrayList<Card> getHand(){
        return hand;
    }
    //set the player's score
    public void setScore(int newScore)
    {
        score=newScore;
    }
    //returns the player's score
    public int getScore()
    {
        return score;
    }
    //set a card as the player's chosen card
    public void setChosenCard(Card newChosenCard)
    {
        chosenCard=newChosenCard;
    }
    //returns the player's chosen card
    public Card getChosenCard()
    {
        return chosenCard;
    }
    //returns the AI number
    public int getNumber(){
        return AInumber;
    }
    
    //manage one turn of one human player
    //returns true if the player has won
    public boolean playTurnPlayer(Game g) {
        
        boolean play = true;
        boolean stop = false;
        //play loop
        do{
            if (this.canPlay(g)) {//if you have at least a card you can play
                
                //we wait for the player to choose a card to play
                setChosenCard(cardChoice(g));
                //inform of the played card
                JOptionPane.showMessageDialog(null,"Played Card : " + getChosenCard().displayColour() + " " + getChosenCard().getSymbol());
                stop=true;
                System.out.println("You chose your card");
            } 
            //if there's no playable card the player draw a card
            else {
                //inform the player that he drew a card
                JOptionPane.showMessageDialog(null, "You draw a card");
                draw(g, 1);//draw a card
                play = false;//the player hasn't played since he drew
                stop =true;
            }
        }while(!stop);
        
        //the card is played
        if (play) {//if the player chose a card to play
            chosenCard.play(g);//play the card
            g.returnToDeck(g.getUpperCard());//returns the upper card to the deck
            g.setUpperCard(chosenCard);//the chosen card becomes the next upper card
            hand.remove(chosenCard);//the chosencard is no longer in the player s hand
        }
        //if the players win the game stops
        if (hand.size() == 0)
            return true;
        //if he doesn't the game continues
        else
            return false;
    }
    
    //manage one turn of one AI player
    //returns true if it won the game,
    //returns false otherwise
    public boolean playTurnAI(Game g) {
        //the AI 's actions are based on random
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
                
            } else {
                stop = hand.get(playedCard).canPlayOn(g.getUpperCard());
            }
            counter++;
        } while (!stop);
        //inform of which card has been played and how many remains in AI hand
        JOptionPane.showMessageDialog(null,"\nAI" + (AInumber + 1) + " played : [" + hand.get(playedCard).displayColour() + hand.get(playedCard).getSymbol() + "]"
                +"\nAI" + (AInumber+1) + " got " + hand.size() + " cards in his hand\n");
        
        // play the selected card
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
            //return the upper card to the deck 
            //and takes the chosen card from the hand
            //to put it as the next upper card
            g.returnToDeck(g.getUpperCard());
            g.setUpperCard(hand.get(playedCard));
            hand.remove(playedCard);
        }
        counter = 0;
        //if the AI win it returns true
        if (hand.size() == 0)
            return true;
        
        //else the game continues
        else
            return false;
    }
    
    //draw a said number of cards from the deck
    public void draw(Game g, int nbCard) {
        for (int j = 0; j < nbCard; ++j) {
            hand.add(g.getDeck().get(j));
            g.getDeck().remove(j);
        }
    }
    
    //see if at least a card can be played from hand
    public boolean canPlay(Game g) {
        boolean possible = false;
        for (Card hand1 : hand) {
            if (hand1.canPlayOn(g.getUpperCard())) {
                possible = true;
            }
        }
        return possible;
    }
    
    //allows a human player to chose a card to play
    public Card cardChoice(final Game g) {
        
        Card prev =this.getChosenCard();
        //instanciation of a Graphic object
        //which manages the situation with a GUI
        Graphic graph = new Graphic(g,this);
        //wait until the chosen card has changed
        // = wait until the player has chosen a new card
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
        
            graph.dispose();//dispose of the GUI after use
            
        return this.getChosenCard();//returns the chosen card
    }
    
    //computes the score corresponding to player's hand
    //used to calculate how many points are won 
    //after the end of a round
    public int computeHandScore()
    {
        int handScore=0;
        //for each card in hand,
        //we add its point value to the score
        for(Card c: hand)
        {
            handScore+=c.getValue();
        }
        return handScore;
    }
}
