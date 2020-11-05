/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

import java.awt.image.BufferedImage;

/**
 *
 * @author maist
 */
public interface Card {
    public int getColour();
    public char getSymbol();
    public int getValue();
    public String displayColour();
    public BufferedImage getPicture();
    public boolean canPlayOn(Card card);
    public void play(Game g);
}
