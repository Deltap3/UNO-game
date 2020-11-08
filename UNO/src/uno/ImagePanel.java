/*
    UNO GAME
    Juliette DANIEL et Pierre MAISTERRENA
    ING3 ex ING2 TDE02
 */
package uno;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.util.ArrayList;

//custom panel class
//allow to display a player's hand and
//the upper card as images
public class ImagePanel extends JPanel{
   
   //attributes
    private ArrayList<BufferedImage> images;
    private BufferedImage upperCardPic;

    //constructor
    public ImagePanel(Game g, Player p)
    {
        super();
        upperCardPic=g.getUpperCard().getPicture();
        images=new ArrayList<>();
        for(Card card: p.getHand())
        {
            images.add(card.getPicture());
        }
        
    }
    //overriden paintComponent method
    //to draw the images on the pannel
    public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    
    //drawing upper card
    g.drawImage(upperCardPic, 0, 0, this);
    
    //drawing hand
    for(int i=0;i<images.size();i++)
    {
        g.drawImage(images.get(i), getWidth()*(i)/images.size(), getHeight()/2, this);
    }
    repaint();
  }


}
