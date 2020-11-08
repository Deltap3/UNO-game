/*
    UNO GAME
    Juliette DANIEL et Pierre MAISTERRENA
    ING3 ex ING2 TDE02
 */
package uno;


//a wild draw card is a wild card
//that makes the next player
//draw 4 cards

public class WildDrawCard extends WildCard{

    //constructor
    public WildDrawCard() {
        super(0,'+',"draw4.jpg");
    }

    //overriden play methods
    @Override
    public void play(Game g){
        //use the wildCard method
        //to play the card and choose the color
        super.play(g);
        //makes the next player draw 4 cards
        g.draw(4);
    }
    @Override
    public void play(Game g, int colour){
        //use the wildCard method
        //to play the card and choose the color
        super.play(g,colour);
        //makes the next player draw 4 cards
        g.draw(4);
    }
}
