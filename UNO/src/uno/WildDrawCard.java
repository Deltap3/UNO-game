/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

import java.util.Scanner;

/**
 *
 * @author maist
 */
public class WildDrawCard implements Card{
    private int myColour;
    private char mySymbol;
    private int newColour;
    public WildDrawCard(int myColour, char mySymbol) {
        this.myColour = myColour;
        this.mySymbol = mySymbol;
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
        System.out.println("Choose a colour that will be the colour of this card");
        System.out.println("1 - Red ; 2 - Blue ; 3 - Green ; 4 - Yellow");
        Scanner keyboard = new Scanner(System.in);
        int choice;
        do{
            choice = keyboard.nextInt();
        }while(choice < 1 && choice > 4);
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
        g.draw(4);
    }
}
