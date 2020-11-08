/*
    UNO GAME
    Juliette DANIEL et Pierre MAISTERRENA
    ING3 ex ING2 TDE02
 */

package uno;


//a draw card is a "basic" card with
//a special effect of making the next player
//draw 2 cards
public class DrawCard extends NumberCard{

    //constructor
    public DrawCard(int myColour, char mySymbol) {
        super(myColour, mySymbol,20);
    }
    //overriden play method
    public void play(Game g){
        //makes next player draw 2 cards
        g.draw(2);
    }
}
