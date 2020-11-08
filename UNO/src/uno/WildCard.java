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
import javax.swing.JFrame;
import javax.swing.JOptionPane;


//a wild card is a special card 
//that can be played on any card
//and can change the active color
//of the game
public class WildCard implements Card{
    
    //attributes
    private final int myColour;//card color
    private final char mySymbol;//card symbol
    private int cardValue;//card value (at the end of the game)
    private int newColour;//color called after the card is played
    private BufferedImage pic=null;//image of the card
    
    //constructors
    //default constructor
    public WildCard()
    {
        this.myColour=0;
        this.mySymbol= 'W';
        this.cardValue=50;
        loadImage("wild.jpg");
        
    }
    //constructor used to define a "special" wild card
    //(wild draw card)
    public WildCard(int myColour, char mySymbol, String name) {
        this.myColour = myColour;
        this.mySymbol = mySymbol;
        this.cardValue=50;
        loadImage(name);
    }
    
    //methods of the Card interface:
    
    //return true if you can play on said card
    //however, wild cards are special cards that you
    //can play at anytime
    //therefore, it will always return true
    public boolean canPlayOn(Card c){
        return true;
    }
    //accessors
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
    public int getNewColour(){
        return newColour;
    }
    public BufferedImage getPicture()
    {
        return pic;
    }
    
    //display the card 's color
    //as wildcards are special cards,
    //they don't have a color
    //therefore the String returned is empty
    public String displayColour(){
        
        return "";
    }
    
    //used to load the image of a card
    private void loadImage(String name)
    {
        try{
        pic= ImageIO.read(new File("pictures/"+name));
        }
        catch (IOException e) {
            
            System.out.println("couldn't load image "+name);
        }
    }
    //used when the card is played
    //it will display a color panel 
    //so that the user can call the new color
    public void play(Game g){
        
        Integer choice=0;//store user choice
        //implementation of the color display
        JFrame colorFrame= new JFrame("color choice");//creates the frame
        ColorChoicePanel colorPanel=new ColorChoicePanel(choice);//creates the panel
        colorFrame.add(colorPanel);//add panel to frame
        //setup frame
        colorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        colorFrame.pack();
        colorFrame.setVisible(true);
        
        //wait for user choice
        do{
            choice=colorPanel.getColorChoice();
            try
            {
                Thread.sleep(500);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
        }while(choice < 1 );
        
        //dispose of the color frame after use
        colorFrame.dispose();
        
        //set the new color
        newColour = choice;
        //inform of the color change
        switch(newColour){
            case 1:
                JOptionPane.showMessageDialog(null,"The new colour is Red");
                break;
            case 2:
                JOptionPane.showMessageDialog(null,"The new colour is Blue");
                break;
            case 3:
                JOptionPane.showMessageDialog(null,"The new colour is Green");
                break;
            case 4:
                JOptionPane.showMessageDialog(null,"The new colour is Yellow");
                break;
        }
    }
    //used when the card is played
    //it will set the new color to the one in the parameters
    public void play(Game g, int colour){
        //set the new color
        newColour = colour;
        //inform of the color change
        switch(newColour){
            case 1:
                JOptionPane.showMessageDialog(null,"The new colour is Red");
                break;
            case 2:
                JOptionPane.showMessageDialog(null,"The new colour is Blue");
                break;
            case 3:
                JOptionPane.showMessageDialog(null,"The new colour is Green");
                break;
            case 4:
                JOptionPane.showMessageDialog(null,"The new colour is Yellow");
                break;
        }
    }
}
