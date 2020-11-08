/*
    UNO GAME
    Juliette DANIEL et Pierre MAISTERRENA
    ING3 ex ING2 TDE02
 */
package uno;

import javax.swing.JOptionPane;


//a reverse card is a numbercard that
//can reverse the playing order
public class ReverseCard extends NumberCard{

    //constructor
    public ReverseCard(int myColour, char mySymbol) {
        //uses the NumberCard constructor
        super(myColour,mySymbol,20);
    }

    //overriden play method
    @Override
    public void play(Game g){
        
        g.reverse();//reverse the playing order
        //inform user of the change
        JOptionPane.showMessageDialog(null, "Playing order has been reversed");
    }
}
