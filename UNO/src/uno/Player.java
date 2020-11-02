/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

/**
 *
 * @author maist
 */
public class Player {

    private ArrayList<Card> hand = new ArrayList<Card>();
    private int AInumber;
    private Card chosenCard;
    JFrame buttonFrame;

    public Player(int nb) {
        AInumber = nb;
        chosenCard = null;
    }

    public void setChosenCard(Card newChosenCard)
    {
        chosenCard=newChosenCard;
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
            cardChoice(g);
            if(chosenCard.canPlayOn(g.getUpperCard()))
                stop=true;
            
            System.out.println("you chose your card");

        } 
        else {
            draw(g, 1);
            play = false;
            stop =true;

        }
        }while(!stop);

        if (play) {
            chosenCard.play(g);
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
                System.out.println("AI" + (AInumber + 1) + " drew a card");
            } else {
                stop = hand.get(playedCard).canPlayOn(g.getUpperCard());
            }
            counter++;
        } while (!stop);
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
            g.setUpperCard(hand.get(playedCard));
            System.out.println("\nAI" + (AInumber + 1) + " played : [" + hand.get(playedCard).displayColour() + hand.get(playedCard).getSymbol() + "]");

            hand.remove(playedCard);
        }
        System.out.println("AI" + AInumber + " got " + hand.size() + " cards in his hand\n");
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

    public void cardChoice(final Game g) {
        //SwingUtilities.invokeLater(new Runnable() {
         //   public void run() {
        Card prevChosenCard=chosenCard;
        
                ArrayList<MyButton> myButtons=new ArrayList<>();
                ButtonGroup group= new ButtonGroup();
                buttonFrame = new JFrame("votre choix :");
                buttonFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                buttonFrame.setLayout(new FlowLayout());
                ButtonListener listener = new ButtonListener();

                for (Card card : hand) {
                    if (card.canPlayOn(g.getUpperCard())) {
                        MyButton btn = new MyButton(card);
                        btn.addActionListener(listener);
                        
                        group.add(btn);
                        buttonFrame.add(btn);
                        myButtons.add(btn);
                        
                    } 
                }
                JToggleButton okButton= new JToggleButton ("OK");
                okButton.addActionListener(listener);
                buttonFrame.add(okButton);
                buttonFrame.pack();
                buttonFrame.setLocationByPlatform(true);
                buttonFrame.setVisible(true);
            /*  
           do{     
               //System.out.println("in the do while");
                if(okButton.isSelected())
                {
                    System.out.println("ok is selected");
                    for(MyButton btn: myButtons)
                    {
                        if(btn.isSelected())
                        {
                            System.out.println("changing selected card");
                            chosenCard=btn.getCard();
                            System.out.println("color: "+chosenCard.getColour());
                            System.out.println("symbol: "+chosenCard.getSymbol());
                        }

                    } 
                }
           }while((chosenCard==prevChosenCard) && (!okButton.isSelected()));
                    */
            //}
        //});
                   

    }

    //internal class definition
    public class ButtonListener implements ActionListener {
        
        
        public void actionPerformed(ActionEvent e) {
            
            
            System.out.println("button selected");
            

        }
    }

}
