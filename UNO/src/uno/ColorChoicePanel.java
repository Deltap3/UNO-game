/*
    UNO GAME
    Juliette DANIEL et Pierre MAISTERRENA
    ING3 ex ING2 TDE02
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

//custom panel to manage the user's color choice
//when playing a wild card or wild draw card
public class ColorChoicePanel extends JPanel{
    
    //attributes
    Integer colorChoice;//the color chosen
    //there are 4 buttons:
    //1 for each color
    JRadioButton redButton;
    JRadioButton blueButton;
    JRadioButton greenButton;
    JRadioButton yellowButton;
    
    public ColorChoicePanel(Integer choice)
    {
        super(); 
        colorChoice=choice;
        //we use a button group so that only one color can
        //be chosen at a time
        ButtonGroup group = new ButtonGroup();
        //specific listener
        ColorButtonListener listener= new ColorButtonListener(colorChoice);
        
        //creates  the four buttons 
        //red button
        redButton= new JRadioButton("red");
        redButton.setBackground(Color.RED);
        redButton.addActionListener(listener);
        group.add(redButton);
        //blue button
        blueButton= new JRadioButton("blue");
        blueButton.setBackground(Color.BLUE);
        blueButton.addActionListener(listener);
        group.add(blueButton);
        //green button
        greenButton= new JRadioButton("green");
        greenButton.setBackground(Color.GREEN);
        greenButton.addActionListener(listener);
        group.add(greenButton);
        //yellow button
        yellowButton= new JRadioButton("yellow");
        yellowButton.setBackground(Color.YELLOW);
        yellowButton.addActionListener(listener);
        group.add(yellowButton);
        
        //add label and buttons to panel
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
    
    //inetrnal class declaration
    //custom button listener for color choice
    private class ColorButtonListener implements ActionListener
   {
        //attribute
      private Integer choice;
      //consstructor
      public ColorButtonListener(Integer colorChoice) {
          choice=colorChoice;
      }
      //implementation of actionPerformed
      //manage action when button is clicked
      public void actionPerformed(ActionEvent e)
      {
          //set the color in relation with which
          //button was pressed
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
