/*
    UNO GAME
    Juliette DANIEL et Pierre MAISTERRENA
    ING3 ex ING2 TDE02
 */
package uno;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//this class manages the GUI
//shown during a player ' s turn
public class Graphic extends JFrame {
    
   //attributes
   private JPanel buttonPanel;//panel with the buttons for choice
   private JPanel adversariesPanel;//represents adversaries and how many cards they have
   private ImagePanel imagePanel;//image of player hand and upper card
   private Card chosenCard;//for card choice
   //dimensions of the frame
   private final int WINDOW_WIDTH = 1200;
   private final int WINDOW_HEIGHT = 1400;
   
   //constructor
   public Graphic(Game g, Player play)
   {
      super();
      setTitle("Player" + (play.getNumber()+1) + " turn");
      setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      imagePanel=new ImagePanel(g, play);//creates the image panel
      buildButtonPanel(g,play);//builds button panel
      buildAdversariesPanel(g, play);//builds adversaries panel
      
      this.setLayout(new BorderLayout());//set layout
      //adds the pannels
      add(adversariesPanel,BorderLayout.NORTH);
      add(imagePanel, BorderLayout.CENTER);
      add(buttonPanel, BorderLayout.EAST);
      
      pack();
      setVisible(true);
   }
   //accessors
   public void setChosenCard(Card card){
       chosenCard = card;
   }
   public Card getChosenCard(){
       return chosenCard;
   }
   //methods to buid the panels
   private void buildAdversariesPanel(Game g, Player play)
   {
       //an adversaries panel is composed of multiples
       //panels, each representing one adversary
       //with his name and the number of cards
       //left in his hand
       adversariesPanel= new JPanel();
       adversariesPanel.setLayout(new FlowLayout());
       for(Player p: g.getPlayerList())
       {
           if(p==play)
           {}
           else
           {
               JPanel advers= new JPanel();
               advers.setBackground(Color.WHITE);
               advers.add(new JLabel("player "+(p.getNumber()+1)));
               advers.add(new JLabel(" ( "+p.getHand().size()+" ) "));
               adversariesPanel.add(advers);
               
           }
       }
       
   }
   //manage all buttons during the player's choice
   private void buildButtonPanel(Game g, Player play)
   {
       //creates the panel
      buttonPanel = new JPanel(); 
      buttonPanel.add(new JLabel("Your choice"));
      
      //there can only be one card played at a time
      //so we use a button group
      ButtonGroup group = new ButtonGroup();
      
      for (Card card : play.getHand()) {//for each card in player's hand
        if (card.canPlayOn(g.getUpperCard())) //you can only pick cards that you can play
        {
            MyButton btn = new MyButton(card);//create a button for each card
            group.add(btn);//add it to group
            btn.addActionListener(new RadioButtonListener(play,btn));//add listener
            buttonPanel.add(btn);//add button to panel
        } 
      }
      
     
   }
   //this is the custom listener for our buttons
   private class RadioButtonListener implements ActionListener
   {
       //attributes
      private Player player;
      private MyButton btn;
      //constructor
      public RadioButtonListener(Player player, MyButton btn) {
          this.player = player;
          this.btn = btn;
      }
      //implementation of actionPerformed method:
      //gives the course of action when user click on a button
      public void actionPerformed(ActionEvent e)
      {
          //we set the chosen card to the one that 
          //corresponds to the button clicked
         player.setChosenCard(btn.getCard());
      }
   }
}
