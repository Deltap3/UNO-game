/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

import java.awt.image.BufferedImage;

/*
    Juliette DANIEL et Pierre MAISTERRENA
    ING3 ex ING2 TDE02
 */
//This interface contains all the methods common to all type of cards
public interface Card {
    //getters
    public int getColour();
    public char getSymbol();
    public int getValue();
    //a colour is an int so this method returns a string of the colour of the card
    public String displayColour();
    public BufferedImage getPicture();
    //detects if this card can be played on the card in parameters
    public boolean canPlayOn(Card card);
    //play the card and apply his effects
    public void play(Game g);
}
