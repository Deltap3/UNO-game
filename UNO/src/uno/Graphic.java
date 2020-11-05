/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author maist
 */
public class Graphic extends JFrame {
   private JPanel buttonPanel;
   private ImagePanel imagePanel;
   private Card chosenCard;
   private final int WINDOW_WIDTH = 1200;
   private final int WINDOW_HEIGHT = 1400;
   public Graphic(Game g, Player play)
   {
      setTitle("Player" + (play.getNumber()+1) + " turn");
      setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setBackground(Color.WHITE);
      imagePanel=new ImagePanel(g, play);
      buildButtonPanel(g,play);
      this.setLayout(new BorderLayout());
      add(new JLabel("Player" + (play.getNumber()+1) + " turn"),BorderLayout.NORTH);
      add(imagePanel, BorderLayout.CENTER);
      add(buttonPanel, BorderLayout.EAST);
      pack();
      setVisible(true);
   }
   public void setChosenCard(Card card){
       chosenCard = card;
   }
   public Card getChosenCard(){
       return chosenCard;
   }
   private void buildButtonPanel(Game g, Player play)
   {
     
      Card prevChosenCard = play.getChosenCard();
      ArrayList<MyButton> myButtons=new ArrayList<>();
      ButtonGroup group = new ButtonGroup();
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      for (Card card : play.getHand()) {
        if (card.canPlayOn(g.getUpperCard())) {
            MyButton btn = new MyButton(card);
            group.add(btn);
            btn.addActionListener(new RadioButtonListener(play,btn));
            myButtons.add(btn);
        } 
      }
      buttonPanel = new JPanel(); 
      buttonPanel.add(new JLabel("Your choice"));
      for(int i = 0; i < myButtons.size(); ++i)
          buttonPanel.add(myButtons.get(i));
     
   }
   private class RadioButtonListener implements ActionListener
   {
      private Player player;
      private MyButton btn;
      public RadioButtonListener(Player player, MyButton btn) {
          this.player = player;
          this.btn = btn;
      }
      public void actionPerformed(ActionEvent e)
      {
         player.setChosenCard(btn.getCard());
      }
   }
}
