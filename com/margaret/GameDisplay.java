package com.margaret;

import javax.swing.*;
import java.awt.*;

public class GameDisplay extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //System.out.println("* Repaint *");

        g.setColor(Color.BLUE);

        if (Main.gameOver == true) {
            g.drawString( "Game over!", 20, 30 );
            return;
        }

        if (Main.removeInstructions == false ) {
            g.drawString("Pong! Press up or down to move", 20, 30);
            g.drawString("Press q to quit", 20, 60);
        }

//            g.setColor(Color.blue);
        g.setColor(Color.MAGENTA);

        //While game is playing, these methods draw the ball, paddles, using the global variables
        //Other parts of the code will modify these variables

        //Ball - a circle is just an oval with the height equal to the width
        g.drawOval((int) Main.ballX, (int) Main.ballY, Main.ballSize, Main.ballSize);
        //Fill the ball with the current color
        g.fillOval((int)Main.ballX, (int)Main.ballY, Main.ballSize, Main.ballSize);
        //Computer paddle
        g.drawLine(Main.paddleDistanceFromSide, Main.computerPaddleY - Main.paddleSize, Main.paddleDistanceFromSide, Main.computerPaddleY + Main.paddleSize);
        //Human paddle
        g.drawLine(Main.screenSize - Main.paddleDistanceFromSide, Main.humanPaddleY - Main.paddleSize, Main.screenSize - Main.paddleDistanceFromSide, Main.humanPaddleY + Main.paddleSize);

    }
}
