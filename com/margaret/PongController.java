package com.margaret;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PongController implements KeyListener{

    //Listens for user pressing a key, and moving human paddle in response

        @Override
        public void keyTyped(KeyEvent ev) {
            char keyPressed = ev.getKeyChar();
            char q = 'q';
            char r = 'r';
            if( keyPressed == q){
                System.exit(0);    //quit if user presses the q key.
            }
            if ( Main.gameOver == true && keyPressed == r){
                    Main.reStartGame();
//                    Ball.playAgain = false;
            }
        }

        @Override
        public void keyReleased(KeyEvent ev) {
//            char keyReleased = ev.getKeyChar();
//            char r = 'r';
//            if( keyReleased == r){
//                if (Ball.playAgain == true){
//                    ev.getComponent().repaint(); // TODO is this right?
//                }
//            }
        }   //Don't need this one, but required to implement it.

        @Override
        public void keyPressed(KeyEvent ev) {

            GameDisplay.removeInstructions = true;   //game has started

            if (ev.getKeyCode() == KeyEvent.VK_DOWN) {
                System.out.println("down key");
                HumanPaddle.moveDown();
            }
            if (ev.getKeyCode() == KeyEvent.VK_UP) {
                System.out.println("up key");
                HumanPaddle.moveUp();
            }

            //ev.getComponent() returns the GUI component that generated this event
            //In this case, it will be GameDisplay JPanel
            ev.getComponent().repaint();   // repainting screen is ongoing and also when key is pressed
            // This calls paintComponent(Graphics g) again
        }
}
