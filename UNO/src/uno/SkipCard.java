/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

/*
    Juliette DANIEL et Pierre MAISTERRENA
    ING3 ex ING2 TDE02
 */
//a skip card is a number card 
//that can skip the next player
public class SkipCard extends NumberCard{

    
    public SkipCard(int myColour, char mySymbol) {
        super(myColour,mySymbol,20);
    }


    @Override
    public void play(Game g){
        g.skip();
    }
}
