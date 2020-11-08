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
//a draw card is a "basic" card with
//a special effect of making the next player
//draw 2 cards
public class DrawCard extends NumberCard{

    public DrawCard(int myColour, char mySymbol) {
        super(myColour, mySymbol,20);
    }
    public void play(Game g){
        g.draw(2);
    }
}
