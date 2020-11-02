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
public class UNO {
    public static void main(String[] args) {
        Game g = new Game();
        int retry;
        g.shuffle();
        g.playersSetUp();
       
        do{
            
        g.setup();
        g.shuffle();
        g.start();
        g.afterGame();
        
        retry=JOptionPane.showConfirmDialog(null, "would you like to play another round?", "end of this round",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        }while (retry!=JOptionPane.NO_OPTION);
        
        g.displayScores();
    }
}
