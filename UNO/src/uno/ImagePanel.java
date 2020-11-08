/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.util.ArrayList;

/*
    Juliette DANIEL et Pierre MAISTERRENA
    ING3 ex ING2 TDE02
 */
public class ImagePanel extends JPanel{
   
   
    private ArrayList<BufferedImage> images;
    private BufferedImage upperCardPic;

    
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
