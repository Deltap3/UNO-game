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
public class DrawCard implements Card{
    private int myColour;
    private char mySymbol;
    public DrawCard(int myColour, char mySymbol) {
        this.myColour = myColour;
        this.mySymbol = mySymbol;
    }
    public boolean canPlayOn(Card c){
        if(c.getSymbol() == 'W'){
            WildCard card = (WildCard)c;
            return myColour == card.getNewColour();
        }
        else if(c.getSymbol() == '+'){
            WildDrawCard card = (WildDrawCard)c;
            return myColour == card.getNewColour();
        }   
        else
            return c.getSymbol() == 'D' || myColour == c.getColour();
    }
    public int getColour(){
        return myColour;
    }
    public char getSymbol(){
        return mySymbol;
    }
    public String displayColour(){
        switch(myColour){
            case 1:
                return "Red";
            case 2:
                return "Blue";
            case 3:
                return "Green";
            case 4:
                return "Yellow";
        }
        return "";
    }
    public void play(Game g){
        g.draw(2);
    }
}
