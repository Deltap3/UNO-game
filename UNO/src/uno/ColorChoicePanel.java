/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/*
    Juliette DANIEL et Pierre MAISTERRENA
    ING3 ex ING2 TDE02
 */
public class ColorChoicePanel extends JPanel{
    Integer colorChoice;
    JRadioButton redButton;
    JRadioButton blueButton;
    JRadioButton greenButton;
    JRadioButton yellowButton;
    
    public ColorChoicePanel(Integer choice)
    {
        super(); 
        colorChoice=choice;
        ButtonGroup group = new ButtonGroup();
        ColorButtonListener listener= new ColorButtonListener(colorChoice);
        
        redButton= new JRadioButton("red");
        redButton.setBackground(Color.RED);
        redButton.addActionListener(listener);
        group.add(redButton);

        blueButton= new JRadioButton("blue");
        blueButton.setBackground(Color.BLUE);
        blueButton.addActionListener(listener);
        group.add(blueButton);
        
        greenButton= new JRadioButton("green");
        greenButton.setBackground(Color.GREEN);
        greenButton.addActionListener(listener);
        group.add(greenButton);
        
        yellowButton= new JRadioButton("yellow");
        yellowButton.setBackground(Color.YELLOW);
        yellowButton.addActionListener(listener);
        group.add(yellowButton);
        
        this.add(new JLabel("Your choice \n"));
        this.setLayout(new FlowLayout());
        this.add(redButton);
        this.add(blueButton);
        this.add(greenButton);
        this.add(yellowButton);
        
        
        this.setVisible(true);
    }
    public void setColorChoice(Integer color)
    {
        colorChoice=color;
    }
    public Integer getColorChoice()
    {
        return colorChoice;
    }
    private class ColorButtonListener implements ActionListener
   {
      private Integer choice;
      public ColorButtonListener(Integer colorChoice) {
          choice=colorChoice;
      }
      public void actionPerformed(ActionEvent e)
      {
         if(e.getSource()==redButton)
             setColorChoice(1);
         else if(e.getSource()== blueButton)
             setColorChoice(2);
         else if (e.getSource()==greenButton)
             setColorChoice(3);
         else if (e.getSource()== yellowButton)
             setColorChoice(4);
      }
    }
}
