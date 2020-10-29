/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

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
            deck.add(new WildCard(0,'W'));
        for(int i = 0; i < 4; ++i)
            deck.add(new WildDrawCard(0,'+'));
        for(int j = 1; j <= 4; ++j){
            deck.add(new NumberCard(j,'0'));
            for(int i = 0; i < 2; ++i){
                deck.add(new NumberCard(j,'1'));
                deck.add(new NumberCard(j,'2'));
                deck.add(new NumberCard(j,'3'));
                deck.add(new NumberCard(j,'4'));
                deck.add(new NumberCard(j,'5'));
                deck.add(new NumberCard(j,'6'));
                deck.add(new NumberCard(j,'7'));
                deck.add(new NumberCard(j,'8'));
                deck.add(new NumberCard(j,'9'));
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
    public void setup(){
        System.out.println("How many human players are they for this game ? (1 players minumum and 10 maximum)");
        Scanner keyboard = new Scanner(System.in);
        do{
            numberOfPlayers = keyboard.nextInt();
        }while(numberOfPlayers < 1 || numberOfPlayers > 10);
        int maxNumberOfAI=10-numberOfPlayers;
        if(maxNumberOfAI>0)
        {
            System.out.println("How many AI  are they for this game ? (0 AI minumum and"+ maxNumberOfAI+"maximum)");
            do{
                numberOfAI = keyboard.nextInt();
            }while(numberOfAI < 0 || numberOfAI > maxNumberOfAI);
        }
        for(int i = 0; i < numberOfPlayers+numberOfAI; ++i){
            playerList.add(new Player(i));
            for(int j = 0; j < 7; ++j){
                playerList.get(i).draw(this,1);
            }
        }
        drawInitialCard();
    }
    public ArrayList<Card> getDeck(){
        return deck;
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
            System.out.println("Upper Card : ["+upperCard.displayColour()+upperCard.getSymbol()+"]");
            if(i < numberOfPlayers)
                playerWin = playerList.get(i).playTurnPlayer(this);
            else
                AIwin = playerList.get(i).playTurnAI(this);
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
                playerList.get(i).draw(this,nbDraw);
                draw(0);
            }   
        }while(!AIwin && !playerWin);
        if(AIwin)
            System.out.println("\nThe AI won and you have lost");
        else 
            System.out.println("\nCongratulations you have won !");
    }
}
