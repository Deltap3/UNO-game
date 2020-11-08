/*
    UNO GAME
    Juliette DANIEL et Pierre MAISTERRENA
    ING3 ex ING2 TDE02
 */
package uno;


//a skip card is a number card 
//that can skip the next player's turn
public class SkipCard extends NumberCard{

    //constructor
    public SkipCard(int myColour, char mySymbol) {
        //uses the NumberCard constructor
        super(myColour,mySymbol,20);
        
    }

    //overriden play method
    @Override
    public void play(Game g){
        g.skip();//skip next player's turn
    }
}
