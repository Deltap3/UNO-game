/*
    UNO GAME
    Juliette DANIEL et Pierre MAISTERRENA
    ING3 ex ING2 TDE02
 */
package uno;

import java.awt.Color;
import javax.swing.JRadioButton;

//custom button class
//each MyButton corresponds to a said card
public class MyButton extends JRadioButton{
    //attributes
    private Card buttonCard;
    //constructor
    public MyButton(Card myCard)
    {
        super(""+myCard.getSymbol());
        buttonCard = myCard;
        int col=myCard.getColour();
        //set the button's color to the same as the card it represents
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
    //accessor
    public Card getCard()
    {
        return buttonCard;
    }
}
