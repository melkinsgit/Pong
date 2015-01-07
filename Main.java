package com.clara;

//import javax.swing.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {
    
    
    
    
    static int screenSize = 300;    //and width
    static int paddleSize = 25;     //Actually half the paddle size - how much to draw on each side of center
    static int paddleDistanceFromSide = 10;
    
    static int gameSpeed = 100;  //How long between clock ticks? Reduce this to speed up game
    
    static int computerPaddleY = screenSize / 2 ;    //center of the paddle
    static int humanPaddleY = screenSize / 2 ;
    
    static int computerPaddleMaxSpeed = 3;   //3 pixels per clock tick
    static int humanPaddleMaxSpeed = 5;   //3 pixels per clock tick
    
    static int humanPaddleSpeed = 0;      // "speed" is pixels moved up or down per clock tick
    static int computerPaddleSpeed = 0;   // same
    
    static double  ballX = screenSize / 2;
    static double  ballY = screenSize / 2;
    
    static double ballSpeed = 5;   //Again, pixels moved per clock tick
    static double ballDirection = 0;  //an angle, in radians 
    
    static Timer timer;
    
    static GameDisplay gamePanel;
    
    static boolean gameOver;
    
    private static class GameDisplay extends JPanel {
        
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            if (gameOver) {
                g.drawString( "Game over!", 20, 30 );
                return;
            }
            
            g.drawString( "Pong! \n Press up or down to start", 20, 30 );
            g.setColor(Color.blue);
            //Ball
            g.drawOval((int)ballX, (int)ballY, 10, 10);
            //Computer paddle
            g.drawLine(paddleDistanceFromSide, computerPaddleY - paddleSize, paddleDistanceFromSide, computerPaddleY + paddleSize);
            //Human paddle
            g.drawLine(screenSize - paddleDistanceFromSide, humanPaddleY - paddleSize, screenSize - paddleDistanceFromSide, humanPaddleY + paddleSize);
            
        }
    }
    
    private static class KeyHandler implements KeyListener {
        
        @Override
        public void keyTyped(KeyEvent e) {}
        
        @Override
        public void keyReleased(KeyEvent e) {}
        
        @Override
        public void keyPressed(KeyEvent ev) {
            if (ev.getKeyCode() == KeyEvent.VK_DOWN) {
                //System.out.println("down key");
                moveUp();
            }
            if (ev.getKeyCode() == KeyEvent.VK_UP) {
                //System.out.println("up key");
                moveDown();
            }
            
            ev.getComponent().repaint();
        }
        
        private void moveDown() {}
        
        private void moveUp() {}
        
        
    }
    
    
    
    public static void main(String[] args) {
        
        gamePanel = new GameDisplay();
        
        KeyHandler listener = new KeyHandler();
        gamePanel.addKeyListener(listener);
        
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        content.add(gamePanel, BorderLayout.CENTER);
        
        JFrame window = new JFrame("Pong!");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(content);
        window.setSize(screenSize, screenSize);
        window.setLocation(100,100);
        window.setVisible(true);
        
        ActionListener gameUpdater = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Do game stuff
                
                moveBall();
                moveComputerPaddle();
                
                if (gameOver) {
                    
                    //displayGameOver();
                    timer.stop();
                }
                
                gamePanel.repaint();
                
            }
            
            void moveBall() {
                System.out.println("move ball");
                //move in current direction
                //bounce off walls and paddle
                //TODO Take into account speed of paddles
                
                //Work in double
                
                boolean hitWall = false;
                boolean hitHumanPaddle = false;
                boolean hitComputerPaddle = false;
                
                if (ballX <= 0 || ballX >= screenSize ) {
                    gameOver = true;
                    return;
                }
                if (ballY >= 0 || ballY >= screenSize ) {
                    hitWall = true;
                }
                
                //If ballX is at a paddle AND ballY is within the paddle size.
                //Hit human paddle?
                if (ballX >= screenSize-paddleDistanceFromSide && (ballY < humanPaddleY+paddleSize && ballY > humanPaddleY+paddleSize))
                    hitHumanPaddle = true;
                
                //Hit computer paddle?
                if (ballX <= paddleDistanceFromSide && (ballY < computerPaddleY+paddleSize && ballY > computerPaddleY+paddleSize))
                    hitComputerPaddle = true;
    
    
                /* Remember: 
                static double ballSpeed = 5;   //Again, pixels moved per clock tick
                static double ballDirection = 0;  //an angle, in radians 
    */
                
                if (hitWall == true) {
                    //TODO bounce
                }
                
                if (hitComputerPaddle == true) {
                    //TODO bounce off paddle 
                    //TODO factor in speed
                }
                
                if (hitHumanPaddle == true) {
                    //TODO bounce off paddle
                    //TODO consider speed
                }
                
                if (hitWall == false && hitComputerPaddle == false && hitHumanPaddle == false) {
                    //TODO ball is in "mid air"
                    //Continue current trajectory
                }
                
            }
            
            void moveComputerPaddle(){
                System.out.println("move computer paddle");
                
                
                //if ballY = 100 and paddleY is 50, difference = 50, need to adjust  
                //paddleY by up to the max speed (the minimum of difference and maxSpeed)
                
                //if ballY = 50 and paddleY = 100 then difference = 50
                //Need to move paddleY down by the max speed
                
                
                int ballPaddleDifference = computerPaddleY - (int)ballY;
                
                if (ballPaddleDifference > 0 ) {
                    int distanceToMove = Math.min(ballPaddleDifference, computerPaddleMaxSpeed);
                    computerPaddleY += distanceToMove;
                    computerPaddleSpeed = distanceToMove;
                } else if (ballPaddleDifference < 0){
                    int distanceToMove = Math.max(ballPaddleDifference, -computerPaddleMaxSpeed);
                    computerPaddleY -= distanceToMove;
                    computerPaddleSpeed = Math.abs(distanceToMove);
                } else {
                    //Ball and paddle are aligned. Don't move!
                    computerPaddleSpeed = 0;
                }
                
                
                
            }
            
            
            
        };
        
        timer = new Timer(gameSpeed, gameUpdater);
        timer.start();
    }
    
}



