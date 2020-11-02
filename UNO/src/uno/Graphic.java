/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author maist
 */
public class Graphic extends JFrame {
   private JPanel panel;
   private JLabel messageLabel;
   private Card chosenCard;
   private final int WINDOW_WIDTH = 400;
   private final int WINDOW_HEIGHT = 100;
   public Graphic(Game g, Player play)
   {
      setTitle("Player" + (play.getNumber()+1) + " turn");
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      buildPanel(g,play);
      add(panel);
      setVisible(true);
   }
   public void setChosenCard(Card card){
       chosenCard = card;
   }
   public Card getChosenCard(){
       return chosenCard;
   }
   private void buildPanel(Game g, Player play)
   {
      messageLabel = new JLabel("Your Choice");
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
      panel = new JPanel();
      for(int i = 0; i < myButtons.size(); ++i)
          panel.add(myButtons.get(i));
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
         JOptionPane.showMessageDialog(null,"Played Card : " + player.getChosenCard().displayColour() + " " + player.getChosenCard().getSymbol());
      }
   }
}
