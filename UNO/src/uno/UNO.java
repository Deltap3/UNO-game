/*
    UNO GAME
    Juliette DANIEL et Pierre MAISTERRENA
    ING3 ex ING2 TDE02
 */
package uno;

import javax.swing.JOptionPane;

//class containing the main method
public class UNO {
    
    //main method
    public static void main(String[] args) {
        
        //first we create a new game, we shuffle the deck and deals the cars to all players
        Game g = new Game();//instanciation of a new game
        int retry;
        g.shuffle();//shuffle the deck
        g.playersSetUp();//ask user input for the number of human and AI players
        
        //we play different rounds until the player chooses to stop
        do{
            g.setup();//setup a new round
            g.shuffle();//shuffle the deck
            g.start();//starts and play the game
            g.afterGame();//returns to a clean state (player hands return to deck)
            
            //asks players if they want to play another round 
            retry=JOptionPane.showConfirmDialog(null, "would you like to play another round?", "end of this round",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
        }while (retry!=JOptionPane.NO_OPTION);//the loop stops when player refuses to play another round
        
        g.displayScores();//display the score board
        System.exit(0);//necessary since we used JOptionPane
    }
}
