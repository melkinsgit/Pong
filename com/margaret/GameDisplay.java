package com.margaret;

import javax.swing.*;
import java.awt.*;

public class GameDisplay extends JPanel {

    static boolean removeInstructions = false;  // static boolean removeInstructions = false;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //System.out.println("* Repaint *");

        g.setColor(Color.BLUE);

        if (Main.gameOver == true) {
            g.drawString( "Game over!", 20, 30 );
            g.drawString( "Computer won " + ComputerPaddle.computerWins + " time(s).", 20, 50);  // output computer total wins
            g.drawString( "Human won " + HumanPaddle.humanWins + " time(s).", 20, 70);  // output human total wins
            g.drawString( "Press 'q' to quit or 'r' to play another game", 20, 90);
//            Ball.playAgain = false;
//            Main.reStartGame();
            return;
        }

        if (removeInstructions == false ) {
            g.drawString("Pong! Press up or down to move", 20, 30);
            g.drawString("Press q to quit", 20, 60);
        }

//            g.setColor(Color.blue);
        g.setColor(Color.MAGENTA);

        //While game is playing, these methods draw the ball, paddles, using the global variables
        //Other parts of the code will modify these variables

        //Ball - a circle is just an oval with the height equal to the width
        g.drawOval((int) Ball.ballX, (int) Ball.ballY, Ball.ballSize, Ball.ballSize);
        //Fill the ball with the current color
        g.fillOval((int)Ball.ballX, (int)Ball.ballY, Ball.ballSize, Ball.ballSize);
        //Computer paddle
        g.drawLine(Main.paddleDistanceFromSide, ComputerPaddle.computerPaddleY - Main.paddleSize, Main.paddleDistanceFromSide, ComputerPaddle.computerPaddleY + Main.paddleSize);
        //Human paddle
        g.drawLine(Main.screenSize - Main.paddleDistanceFromSide, HumanPaddle.humanPaddleY - Main.paddleSize, Main.screenSize - Main.paddleDistanceFromSide, HumanPaddle.humanPaddleY + Main.paddleSize);

    }
}
