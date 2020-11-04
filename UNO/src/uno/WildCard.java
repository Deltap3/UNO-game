/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

//import java.util.Scanner;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
/**
 *
 * @author maist
 */
//a wild card is a special card 
//that can be played on any card
//and can change the active color
//of the game
public class WildCard implements Card{
    private final int myColour;
    private final char mySymbol;
    private int newColour;
    private BufferedImage pic=null;
    
    public WildCard()
    {
        this.myColour=0;
        this.mySymbol= 'W';
        loadImage("wild.jpg");
        
    }
    public WildCard(int myColour, char mySymbol, String name) {
        this.myColour = myColour;
        this.mySymbol = mySymbol;
        loadImage(name);
    }
    public boolean canPlayOn(Card c){
        return true;
    }
    public int getColour(){
        return myColour;
    }
    public char getSymbol(){
        return mySymbol;
    }
    public int getNewColour(){
        return newColour;
    }
    public String displayColour(){
        
        return "";
    }
    private void loadImage(String name)
    {
        try{
        pic= ImageIO.read(new File("pictures/"+name));
        }
        catch (IOException e) {
            
            System.out.println("couldn't load image "+name);
        }
    }
    public void play(Game g){
        
        String str;
        int choice;
        do{
            str=JOptionPane.showInputDialog("Call the next color: \n"+
                "1 - Red ; 2 - Blue ; 3 - Green ; 4 - Yellow");
            choice = Integer.parseInt(str);
        }while(choice < 1 || choice > 4);
        newColour = choice;
        switch(newColour){
            case 1:
                System.out.println("The new colour is Red");
                break;
            case 2:
                System.out.println("The new colour is Blue");
                break;
            case 3:
                System.out.println("The new colour is Green");
                break;
            case 4:
                System.out.println("The new colour is Yellow");
                break;
        }
    }
    public void play(Game g, int colour){
        newColour = colour;
        switch(newColour){
            case 1:
                System.out.println("The new colour is Red");
                break;
            case 2:
                System.out.println("The new colour is Blue");
                break;
            case 3:
                System.out.println("The new colour is Green");
                break;
            case 4:
                System.out.println("The new colour is Yellow");
                break;
        }
    }
}
