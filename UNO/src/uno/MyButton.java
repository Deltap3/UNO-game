/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

import java.awt.Color;
import javax.swing.JRadioButton;

/*
    Juliette DANIEL et Pierre MAISTERRENA
    ING3 ex ING2 TDE02
 */
public class MyButton extends JRadioButton{
    private Card buttonCard;
    public MyButton(Card myCard)
    {
        super(""+myCard.getSymbol());
        buttonCard = myCard;
        int col=myCard.getColour();
            switch(col)
            {
                case 1: //red
                   this.setBackground(Color.RED);
                   this.setForeground(Color.WHITE);
                   break;
                    
                case 2: //blue
                    this.setBackground(Color.BLUE);
                    this.setForeground(Color.WHITE);
                    break;
                    
                case 3: //green
                    this.setBackground(Color.GREEN);
                    this.setForeground(Color.BLACK);
                    break;
                    
                case 4: //yellow
                    this.setBackground(Color.YELLOW);
                    this.setForeground(Color.BLACK);
                    break;
                    
                case 0: //wild card
                    this.setBackground(Color.BLACK);
                    this.setForeground(Color.WHITE);
            }
          this.setOpaque(true);
    }
    public Card getCard()
    {
        return buttonCard;
    }
}
