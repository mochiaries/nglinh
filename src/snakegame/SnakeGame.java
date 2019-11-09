/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.awt.Color;
import javax.swing.JFrame;

public class SnakeGame {

    public static void main(String[] args) {    
        JFrame frame = new JFrame("Snake Game !");
        frame.setBounds(10, 10, 900, 700);
        frame.setBackground(Color.BLACK);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        GamePlay gamePlay = new GamePlay();
        frame.add(gamePlay);
    }
    
}
