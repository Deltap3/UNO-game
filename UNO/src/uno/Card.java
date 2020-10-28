/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

/**
 *
 * @author maist
 */
public interface Card {
    public int getColour();
    public char getSymbol();
    public String displayColour();
    public boolean canPlayOn(Card card);
    public void play(Game g);
}
