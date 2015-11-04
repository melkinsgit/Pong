package com.margaret;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

//TODO have paddle speed affect ball's direction
//TODO known issue - sometimes ball gets stuck behind human paddle

public class Main {

    static int screenSize = 300;    //and width - screen is square
    static int paddleSize = 25;     //Actually half the paddle size - how much to draw on each side of center
    static int paddleDistanceFromSide = 10;  //How much space between each paddle and side of screen
    static int gameSpeed = 75;  //How many milliseconds between clock ticks? Reduce this to speed up game
    static boolean gameOver;      //Used to work out what message, if any, to display on the screen

    static boolean playMore = true;
    static boolean valid;
    static Scanner c = new Scanner(System.in);
    static String reply;

    static Timer timer;    //Ticks every *gameSpeed* milliseconds. Every time it ticks, the ball and computer paddle move

    static GameDisplay gamePanel;   //draw the game components here

    public static void main(String[] args) {

        gamePanel = new GameDisplay();  //the class that displays a new game

        // panel is inside the frame, on the panel is the gamePanel
        JPanel content = new JPanel();  // instantiating my screen object
        content.setLayout(new BorderLayout());  // set the border object and feed it to the screen
        content.add(gamePanel, BorderLayout.CENTER);  // put the game panel centered - what and how

        JFrame window = new JFrame();  // instantiating the frame
        window.setUndecorated(true);   // Hides the title bar.

        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);   //Quit the program when we close this window
        window.setContentPane(content);  // put the panel inside the frame, and the panel already has the game on it
        window.setSize(screenSize, screenSize);
        window.setLocation(100, 100);    //Where on the screen will this window appear?
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
                //moveBall() and moveComputerPaddle belong to the outer class - now their own classes
                //So we have to say Ball.moveBall() and ComputerPaddle.moveComputerPaddle() to refer to these methods
                Ball.moveBall();
                ComputerPaddle.moveComputerPaddle();

                System.out.println("Time going");
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

    public static void reStartGame () {
        gameOver = false;
        timer.start(); // put the timer back to 0
        Ball.ballX = Main.screenSize / 2;   //Imagine the ball is in a square box. These are the coordinates of the top of that box.
        Ball.ballY = Main.screenSize / 2;
        //Every time the timer ticks, the actionPerformed method of the ActionListener is called
    }
}



