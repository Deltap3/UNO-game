/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

import java.util.ArrayList;
import java.util.Collections;
//import java.util.List;
//import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author maist
 */
public class Game {
    private int numberOfPlayers;
    private int numberOfAI;
    private int nbDraw;
    private Card upperCard;
    private boolean reversed;
    private boolean drew;
    private boolean skipped;
    private ArrayList<Player> playerList = new ArrayList<Player>();
    private ArrayList<Card> deck = new ArrayList<Card>();
    public Game() {
        for(int i = 0; i < 4; ++i)
            deck.add(new WildCard());
        for(int i = 0; i < 4; ++i)
            deck.add(new WildDrawCard());
        for(int j = 1; j <= 4; ++j){
            deck.add(new NumberCard(j,'0'));
            for(int i = 0; i < 2; ++i){
                
                for(int k=1;k<10;k++)
                {

                    char symbol=(char)(k+'0');
                   deck.add(new NumberCard(j,symbol)); 

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
    public void setUpperCard(Card upper){
        upperCard = upper;
    }
    public Card getUpperCard(){
        return upperCard;
    }
    public void shuffle(){
        for(int i = 0; i < 10 ; ++i)
            Collections.shuffle(deck);
    }
    public Player getPLayer(int i){
        return playerList.get(i);
    }
    public void playersSetUp()
    {
        String str;
        //System.out.println("How many human players are they for this game ? (1 players minumum and 10 maximum)");
        //Scanner keyboard = new Scanner(System.in);
        do{
            str=JOptionPane.showInputDialog("How many human players are they for this game ? (1 players minumum and 10 maximum)");
            numberOfPlayers = Integer.parseInt(str);
            if(numberOfPlayers < 1 || numberOfPlayers > 10)
                throw new Error("Can't have this number of players");
        }while(numberOfPlayers < 1 || numberOfPlayers > 10);
        
        int maxNumberOfAI=10-numberOfPlayers;
        if(maxNumberOfAI>0)
        {
            //System.out.println("How many AI  are they for this game ? (0 AI minumum and "+ maxNumberOfAI+" maximum)");
            do{
                str=JOptionPane.showInputDialog("How many AI  are they for this game ? (0 AI minumum and "+ maxNumberOfAI+" maximum)");
                numberOfAI = Integer.parseInt(str);
                if(numberOfAI < 0 || numberOfAI > maxNumberOfAI)
                    throw new Error("Can't have this number of AI");
            }while(numberOfAI < 0 || numberOfAI > maxNumberOfAI);
        }
        for(int i = 0; i < numberOfPlayers+numberOfAI; ++i){
            playerList.add(new Player(i));
        }
    }
    public void setup(){
       //deals the cards
        for(int i = 0; i < playerList.size(); ++i){
            for(int j = 0; j < 7; ++j){
                playerList.get(i).draw(this,1);
            }
        }
        //set up the first up front card
        drawInitialCard();
    }
    public ArrayList<Card> getDeck(){
        return deck;
    }
    public void returnToDeck(Card usedCard)
    {
        deck.add(usedCard);
    }
    
    private void drawInitialCard(){
        int i = 0;
        while(deck.get(i).getSymbol() == 'W' || deck.get(i).getSymbol() == '+')
            i++;
        upperCard = deck.get(i);
        deck.remove(i);
    }
    
    public void reverse(){
        reversed = !reversed;
    }
    public void draw(int nb){
        drew = !drew;
        nbDraw = nb;
    }
    public void skip(){
        skipped = !skipped;
    }
    public void start(){
        boolean AIwin = false,playerWin = false;
        int i = 0;
        
        do{
            JOptionPane.showMessageDialog(null, "Upper Card : ["+upperCard.displayColour()+upperCard.getSymbol()+"]");
            //System.out.println("Upper Card : ["+upperCard.displayColour()+upperCard.getSymbol()+"]");
            if(i < numberOfPlayers)
            {
                
                playerWin = playerList.get(i).playTurnPlayer(this);
                if(playerWin)
                    playerList.get(i).setScore(playerList.get(i).getScore()+100);
            }
                
            else
            {
                AIwin = playerList.get(i).playTurnAI(this);
                if(AIwin)
                    playerList.get(i).setScore(playerList.get(i).getScore()+100);
            } 
            if(reversed)
                i--;
            else
                i++;
            if(skipped){
                if(reversed)
                    i--;
                else
                    i++;
                skip();
                
            }
            if(i < 0)
                i = playerList.size()-1;
            else if(i >= playerList.size())
                i = 0;
            if(drew){
                if(playerList.get(i).getNumber() < numberOfPlayers)
                    JOptionPane.showMessageDialog(null, "Player "+ (i+1) +" will draw "+nbDraw+" cards");
                else
                    JOptionPane.showMessageDialog(null, "AI "+ (i+1) +" will draw "+nbDraw+" cards");
                playerList.get(i).draw(this,nbDraw);
                draw(0);
            }   
        }while(!AIwin && !playerWin);
        if(AIwin)
            JOptionPane.showMessageDialog(null,"\nThe AI won and you have lost");
        else 
            JOptionPane.showMessageDialog(null, "\nCongratulations you have won !");
        
        
    }
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
