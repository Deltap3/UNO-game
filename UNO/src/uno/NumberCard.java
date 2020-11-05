/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author maist
 */
//"basic" card class
//has no special effect when played
public class NumberCard implements Card{
    private int myColour;
    private int cardValue;
    private char mySymbol;
    private BufferedImage pic=null;
    
    public NumberCard(int myColour, char mySymbol, int value) {
        this.myColour = myColour;
        this.mySymbol = mySymbol;
        this.cardValue= value;
        System.out.println(""+String.valueOf(mySymbol)+myColour);
        loadImage();
    }
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
            return c.getSymbol() == mySymbol || myColour == c.getColour();
    }
    public int getColour(){
        return myColour;
    }
    public char getSymbol(){
        return mySymbol;
    }
    public int getValue()
    {
        return cardValue;
    }
    public BufferedImage getPicture()
    {
        return pic;
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
        
    }
}
