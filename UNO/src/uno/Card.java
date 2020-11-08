/*
    UNO GAME
    Juliette DANIEL et Pierre MAISTERRENA
    ING3 ex ING2 TDE02
 */
package uno;

import java.awt.image.BufferedImage;


//This interface contains all the methods common to all type of cards
public interface Card {
    //getters
    public int getColour();
    public char getSymbol();
    public int getValue();
    public BufferedImage getPicture();
    
    //a colour is an int so this method returns a string of the colour of the card
    public String displayColour();
    
    //detects if this card can be played on the card in parameters
    public boolean canPlayOn(Card card);
    
    //play the card and apply its effects
    public void play(Game g);
}
