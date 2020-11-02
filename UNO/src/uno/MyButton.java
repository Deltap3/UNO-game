/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JRadioButton;

/**
 *
 * @author Juju
 */
public class MyButton extends JRadioButton{
    
    private Card buttonCard;
    

    
    public MyButton(Card myCard)
    {
        super(""+myCard.getSymbol());
        int col=myCard.getColour();
            switch(col)
            {
                case 1: //red
                   this.setBackground(Color.RED);
                   break;
                    
                case 2: //blue
                    this.setBackground(Color.BLUE);
                    break;
                    
                case 3: //green
                    this.setBackground(Color.GREEN);
                    break;
                    
                case 4: //yellow
                    this.setBackground(Color.YELLOW);
                    break;
                    
                case 0: //wild card
                    this.setBackground(Color.BLACK);
            }
            
          this.setForeground(Color.white);
          this.setOpaque(true);
    }
    
    public Card getCard()
    {
        return buttonCard;
    }
}
