package com.margaret;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//TODO have paddle speed affect ball's direction
//TODO known issue - sometimes ball gets stuck behind human paddle

public class Main {

    static int screenSize = 300;    //and width - screen is square
    static int paddleSize = 25;     //Actually half the paddle size - how much to draw on each side of center
    static int paddleDistanceFromSide = 10;  //How much space between each paddle and side of screen

    static int gameSpeed = 75;  //How many milliseconds between clock ticks? Reduce this to speed up game

    static int computerPaddleY = screenSize / 2 ;    //location of the center of the paddles on the Y-axis of the screen
    static int humanPaddleY = screenSize / 2 ;

    static int computerPaddleMaxSpeed = 3;   //Max number of pixels that computer paddle can move clock tick. Higher number = easier for computer
    static int humanPaddleMaxSpeed = 5;   //This doesn't quite do the same thing... this is how many pixels human moves per key press TODO use this in a better way

    static int humanPaddleSpeed = 0;      // ??? never used ??? "speed" is pixels moved up or down per clock tick
    static int computerPaddleSpeed = 0;   // same

    static double  ballX = screenSize / 2;   //Imagine the ball is in a square box. These are the coordinates of the top of that box.
    static double  ballY = screenSize / 2;   //So this starts the ball in (roughly) the center of the screen
    static int ballSize = 10;                //Diameter of ball

    static double ballSpeed = 5;   //Again, pixels moved per clock tick


    //An angle in radians (which range from 0 to 2xPI (0 to about 6.3).
    //This starts the ball moving down toward the human. Replace with some of the other
    //commented out versions for a different start angle
    //set this to whatever you want (but helps if you angle towards a player)
    static double ballDirection = Math.PI + 1;   //heading left and up
    //static double ballDirection = 1;
    //static double ballDirection = 0;   //heading right
    //static double ballDirection = Math.PI;   //heading left

    static Timer timer;    //Ticks every *gameSpeed* milliseconds. Every time it ticks, the ball and computer paddle move

    static GameDisplay gamePanel;   //draw the game components here
    
    static boolean gameOver;      //Used to work out what message, if any, to display on the screen
    static boolean removeInstructions = false;  // Same as above

    public static void main(String[] args) {
        
        gamePanel = new GameDisplay();  //the class above that displays a new game

        // panel is inside the frame, on the panel is the gamePanel
        JPanel content = new JPanel();  // instantiating my screen object
        content.setLayout(new BorderLayout());  // set the border object and feed it to the screen
        content.add(gamePanel, BorderLayout.CENTER);  // put the game panel centered - what and how
        
        JFrame window = new JFrame();  // instantiating the frame
        window.setUndecorated(true);   // Hides the title bar.

        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);   //Quit the program when we close this window
        window.setContentPane(content);  // put the panel inside the frame, and the panel already has the game on it
        window.setSize(screenSize, screenSize);
        window.setLocation(100,100);    //Where on the screen will this window appear?
        window.setVisible(true);  // see the frame - with no frame you can't see anything else

        // my class is now PongController to create
        PongController listener = new PongController();  //start listening for input from the user - this program responds to up arrows, down arrows and the letter q only
        window.addKeyListener(listener);  // frame is listening for the keyboard

        //Below, we'll create and start a timer that notifies an ActionListener every time it ticks
        //First, need to create the listener:
        ActionListener gameUpdater = new ActionListener() {  // very general listener, if something happens
            @Override
            public void actionPerformed(ActionEvent e) {

                //gameUpdater is an inner class
                //It's containing class is Main
                //moveBall() and moveComputerPaddle belong to the outer class - Main
                //So we have to say Main.moveBall() to refer to these methods
                Ball.moveBall();
                ComputerPaddle.moveComputerPaddle();

                if (gameOver) {
                    timer.stop();
                }
                gamePanel.repaint();
            }
        };
        
        timer = new Timer(gameSpeed, gameUpdater);  // set the timer with a speed and an action listener to constantly listen to
        //Every time the timer ticks, the actionPerformed method of the ActionListener is called
        timer.start();  // start the timer
    } // end MAIN fn
}



