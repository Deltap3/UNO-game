/*
    UNO GAME
    Juliette DANIEL et Pierre MAISTERRENA
    ING3 ex ING2 TDE02
 */
package uno;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;

//manages a game of UNO
public class Game {
    //attributes
    private int numberOfPlayers;//number of human players
    private int numberOfAI;//number of AI players
    private int nbDraw;//number of cards drawn by a player
    private Card upperCard;//upper card
    
    //these boolean are true when a draw, reverse or skip card is played
    // and then they become false until they're played again
    private boolean reversed;
    private boolean drew;
    private boolean skipped;
    
    private ArrayList<Player> playerList = new ArrayList<Player>();//list of all players
    private ArrayList<Card> deck = new ArrayList<Card>();//deck
    
    //constructor
    //create the deck with all the cards
    public Game() {
        for(int i = 0; i < 4; ++i)
            deck.add(new WildCard());
        for(int i = 0; i < 4; ++i)
            deck.add(new WildDrawCard());
        for(int j = 1; j <= 4; ++j){
            deck.add(new NumberCard(j,'0',0));
            for(int i = 0; i < 2; ++i){
                
                for(int k=1;k<10;k++)
                {

                    char symbol=(char)(k+'0');
                   deck.add(new NumberCard(j,symbol,k)); 

                }
                deck.add(new ReverseCard(j,'R'));
                deck.add(new SkipCard(j,'S'));
                deck.add(new DrawCard(j,'D'));
            }
        }
        reversed = false;
        drew = false;
        skipped = false;
    }
    
    
    //accessors
    public void setUpperCard(Card upper){
        upperCard = upper;
    }
    public Card getUpperCard(){
        return upperCard;
    }
    public Player getPLayer(int i){
        return playerList.get(i);
    }
    public ArrayList<Card> getDeck(){
        return deck;
    }
    public ArrayList<Player> getPlayerList()
    {
        return playerList;
    }
    
    //shuffle the deck
    public void shuffle(){
        for(int i = 0; i < 10 ; ++i)
            Collections.shuffle(deck);
    }
    
    //setup the game (define the number of players and AI)
    //and give them the 7 first cards in their hands
    public void playersSetUp()
    {
        //there is a maximum of 10 player total
        String str;
        //ask user imput for number of human players
        do{
            try{
                str=JOptionPane.showInputDialog("How many human players are they for this game ? (1 players minumum and 10 maximum)");
                if(str.isEmpty())
                    throw new IllegalArgumentException("please enter the number of players");
                numberOfPlayers = Integer.parseInt(str);
                if(numberOfPlayers < 1 || numberOfPlayers > 10)
                    throw new IllegalArgumentException("Can't have this number of players");
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, e.getMessage(),"",JOptionPane.ERROR_MESSAGE);

            }
        }while(numberOfPlayers < 1 || numberOfPlayers > 10);
        
        int maxNumberOfAI=10-numberOfPlayers;
        //ask user imput for number of AI players
        if(maxNumberOfAI>0)
        {
            do{
                try{
                    str=JOptionPane.showInputDialog("How many AI  are they for this game ? (0 AI minumum and "+ maxNumberOfAI+" maximum)");
                    if(str.isEmpty())
                        throw new IllegalArgumentException("please enter the number of AI");
                    numberOfAI = Integer.parseInt(str);
                    if(numberOfAI < 0 || numberOfAI > maxNumberOfAI)
                        throw new IllegalArgumentException("Can't have this number of AI");
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null, e.getMessage(),"error",JOptionPane.ERROR_MESSAGE);
                }

            }while(numberOfAI < 0 || numberOfAI > maxNumberOfAI);
        }
        //add players to the game
        for(int i = 0; i < numberOfPlayers+numberOfAI; ++i){
            playerList.add(new Player(i));
        }
    }
    
    //setup a new round
    public void setup(){
       //deals the cards to all players and AI
        for(int i = 0; i < playerList.size(); ++i){
            for(int j = 0; j < 7; ++j){
                playerList.get(i).draw(this,1);
            }
        }
        drawInitialCard();
    }
    
    //return a used card to the deck
    public void returnToDeck(Card usedCard)
    {
        deck.add(usedCard);
    }
    
    //set up the first up front card
    private void drawInitialCard(){
        int i = 0;
        //it cannot be a wild card or a draw 4 card
        while(deck.get(i).getSymbol() == 'W' || deck.get(i).getSymbol() == '+')
            i++;
        upperCard = deck.get(i);
        deck.remove(i);
    }
    //reverse playing order
    public void reverse(){
        reversed = !reversed;
    }
    //draw nb number of card
    public void draw(int nb){
        drew = !drew;
        nbDraw = nb;
    }
    //skip a player's turn
    public void skip(){
        skipped = !skipped;
    }
    
    //starts and manage a round
    public void start(){
        boolean AIwin = false,playerWin = false;
        int i = 0;
        do{
            JOptionPane.showMessageDialog(null, "Upper Card : ["+upperCard.displayColour()+upperCard.getSymbol()+"]");
            if(i < numberOfPlayers)//if the player is human
            {
                playerWin = playerList.get(i).playTurnPlayer(this);
                if(playerWin)
                    playerList.get(i).setScore(playerList.get(i).getScore()+computeScore());
            }
            else
            {
                AIwin = playerList.get(i).playTurnAI(this);
                if(AIwin)
                    playerList.get(i).setScore(playerList.get(i).getScore()+computeScore());
            } 
            //depending on playing order
            //i will be incremented upward or downward
            if(reversed)
                i--;
            else
                i++;
            //manage to skip a player
            if(skipped){
                if(reversed)
                    i--;
                else
                    i++;
                
                skip();//return the boolean to normal
            }
            
            if(i < 0)
                i = playerList.size()-1;
            else if(i >= playerList.size())
                i = 0;
            //manages to make next player draw cards
            if(drew){
                if(playerList.get(i).getNumber() < numberOfPlayers)
                    JOptionPane.showMessageDialog(null, "Player "+ (i+1) +" will draw "+nbDraw+" cards");
                else
                    JOptionPane.showMessageDialog(null, "AI "+ (i+1) +" will draw "+nbDraw+" cards");
                playerList.get(i).draw(this,nbDraw);
                draw(0);
            }   
        }while(!AIwin && !playerWin);//the round stop when someone wins
        //inform user
        if(AIwin)
            JOptionPane.showMessageDialog(null,"\nThe AI won and you have lost");
        else 
            JOptionPane.showMessageDialog(null, "\nCongratulations you have won !"); 
    }
    //return all cards in hand to the deck after a game
    //reset players chosen card
    public void afterGame()
    {
        for (Player play : playerList) {
           for(Card leftoverCard :play.getHand())
           {
               deck.add(leftoverCard);
           }
           play.getHand().clear();
           play.setChosenCard(null);
        }
    }
    //compute the score won after a round
    public int computeScore()
    {
        int total=0;
        for(Player p: playerList)
        {
            total+=p.computeHandScore();
        }
        return total;
    }
    //display total score at the end of the game
    public void displayScores()
    {
        String str="Score board: \n";
        for(int i=0;i<playerList.size();i++)
        {
            if(i < numberOfPlayers)
                str+="Player "+(i+1)+" : "+playerList.get(i).getScore()+"\n";
            else
                str+="AI "+(i+1)+" : "+playerList.get(i).getScore()+"\n";
        }
        
        JOptionPane.showMessageDialog(null, str);
        
    }
    
}
