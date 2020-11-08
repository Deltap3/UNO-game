/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

import javax.swing.JOptionPane;

/*
    Juliette DANIEL et Pierre MAISTERRENA
    ING3 ex ING2 TDE02
 */
public class UNO {
    public static void main(String[] args) {
        //first we create a new game, we shuffle the deck and deals the cars to all players
        Game g = new Game();
        int retry;
        g.shuffle();
        g.playersSetUp();
        //we play different rounds while the player choose to stop
        do{
            g.setup();
            g.shuffle();
            g.start();
            g.afterGame();
            retry=JOptionPane.showConfirmDialog(null, "would you like to play another round?", "end of this round",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        }while (retry!=JOptionPane.NO_OPTION);
        g.displayScores();
        System.exit(0);
    }
}
