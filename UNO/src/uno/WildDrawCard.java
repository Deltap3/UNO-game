/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author maist
 */
//a wild draw card is a wild card
//that makes the next player
//draw 4 cards
public class WildDrawCard extends WildCard{

    
    public WildDrawCard() {
        super(0,'+');
    }


    @Override
    public void play(Game g){
           
        super.play(g);
        g.draw(4);
    }
    @Override
    public void play(Game g, int colour){
        super.play(g,colour);
        g.draw(4);
    }
}
