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
public class UNO {
    public static void main(String[] args) {
        Game g = new Game();
        g.shuffle();
        g.setup();
        g.shuffle();
        g.start();
    }
}
