/*
    UNO GAME
    Juliette DANIEL et Pierre MAISTERRENA
    ING3 ex ING2 TDE02
 */
package uno;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


//"basic" card class
//has no special effect when played
public class NumberCard implements Card{
    //attributes
    private int myColour;//card color
    private int cardValue;//card point value
    private char mySymbol;//card symbol
    private BufferedImage pic=null;//image of the card
    
    //constructor
    public NumberCard(int myColour, char mySymbol, int value) {
        this.myColour = myColour;
        this.mySymbol = mySymbol;
        this.cardValue= value;
        
        loadImage();
    }
    //load card's image from the pictures file
    private void loadImage()
    {
        String name=""+String.valueOf(mySymbol)+myColour+".jpg";
        try{
        pic= ImageIO.read(new File("pictures/"+name));
        }
        catch (IOException e) {
            System.out.println("couldn't load image "+name);
        }
    }
    //a NumberCard implements all methods from Card
    
    //accessors
    //return the card's color
    public int getColour(){
        return myColour;
    }
    //returns the card's symbol
    public char getSymbol(){
        return mySymbol;
    }
    //returns the card's point value
    public int getValue()
    {
        return cardValue;
    }
    //return the card's image
    public BufferedImage getPicture()
    {
        return pic;
    }
    //return true if the card can be played and false otherwise
    public boolean canPlayOn(Card c){
        //if the card is a wild card or a draw 4
        //we look at the new color called
        if(c.getSymbol() == 'W'){
            WildCard card = (WildCard)c;
            return myColour == card.getNewColour();
        }
        else if(c.getSymbol() == '+'){
            WildDrawCard card = (WildDrawCard)c;
            return myColour == card.getNewColour();
        }  
        //else we look at the card's color itself
        //a card can be played on another card
        //with the same color or symbol
        else
            return c.getSymbol() == mySymbol || myColour == c.getColour();
    }
    //display card's color
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
    //a number card has no special effect when played
    public void play(Game g){
        
    }
}
