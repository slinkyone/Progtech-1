package trongame;

import gui.TronGameFrame;
import logic.logic;
import javax.swing.JFrame;

public class Trongame {

    public static void main(String[] args) {
       logic logic = new logic(12);
       JFrame jFrame = new TronGameFrame(logic); 
       new TronGameFrame(logic).setVisible(true); 
    }
}
