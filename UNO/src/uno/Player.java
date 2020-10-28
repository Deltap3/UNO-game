/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author maist
 */
public class Player {
    private ArrayList<Card> hand = new ArrayList<Card>();
    private int AInumber;
    public Player(int nb) {
        AInumber= nb;
    }
    public boolean playTurnPlayer(Game g){
        Scanner keyboard = new Scanner(System.in);
        int playedCard;
        for(int i = 0; i < hand.size(); ++i)
            System.out.print(i + " [" + hand.get(i).displayColour() + hand.get(i).getSymbol() + "]   ");
        System.out.println("");
        boolean stop = false, play = true;
        do{
            System.out.println("Which card do you want to play ? (please enter his number before the []");
            System.out.println("If you can't play please type '-1' to draw a card");
            playedCard = keyboard.nextInt();
            if(playedCard == -1){
                draw(g, 1);
                stop = true;
                play = false;
            }
            else
                stop = hand.get(playedCard).canPlayOn(g.getUpperCard());
        }while(!stop);
        if(play){
            hand.get(playedCard).play(g);
            g.setUpperCard(hand.get(playedCard));
            hand.remove(playedCard);
        }
        if(hand.size() == 0)
            return true;
        else
            return false;
    }
    public boolean playTurnAI(Game g){
        Random r = new Random();
        int counter = 0;
        boolean stop = false, play = true;
        int playedCard = r.nextInt((hand.size() - 0)) + 0;
        do{
            playedCard = r.nextInt((hand.size() - 0)) + 0;
            if(counter == hand.size()-1){
                draw(g, 1);
                stop = true;
                play = false;
                System.out.println("AI" + AInumber + " drew a card");
            }
            else
                stop = hand.get(playedCard).canPlayOn(g.getUpperCard());
            counter++;  
        }while(!stop);
        if(play){
            if(hand.get(playedCard).getSymbol()!= 'W' && hand.get(playedCard).getSymbol()!= '+')
                hand.get(playedCard).play(g);
            else if(hand.get(playedCard).getSymbol() == '+'){
                WildDrawCard card = (WildDrawCard)hand.get(playedCard);
                card.play(g,r.nextInt((4 - 1)) + 1);
            }
            else if(hand.get(playedCard).getSymbol() == 'W'){
                WildCard card = (WildCard)hand.get(playedCard);
                card.play(g,r.nextInt((4 - 1)) + 1);
            }
            g.setUpperCard(hand.get(playedCard));
            System.out.println("\nAI" + AInumber + " played : [" + hand.get(playedCard).displayColour() + hand.get(playedCard).getSymbol() + "]");
            
            hand.remove(playedCard);
        }
        System.out.println("AI" + AInumber + " got " + hand.size() + " cards in his hand\n");
        counter = 0;
        if(hand.size() == 0)
            return true;
        else
            return false;
    }
    public void draw(Game g, int nbCard){
        for(int j = 0; j < nbCard; ++j){
            hand.add(g.getDeck().get(j));
            g.getDeck().remove(j);
        }
    }
}
