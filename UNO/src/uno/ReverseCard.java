/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

import javax.swing.JOptionPane;

/**
 *
 * @author maist
 */
//a reverse card is a numbercard that
//can reverse the playing order
public class ReverseCard extends NumberCard{

    public ReverseCard(int myColour, char mySymbol) {
        super(myColour,mySymbol,20);
    }


    @Override
    public void play(Game g){
        g.reverse();
        JOptionPane.showMessageDialog(null, "Playing order has been reversed");
    }
}
