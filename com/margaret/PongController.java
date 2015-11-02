package com.margaret;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PongController implements KeyListener{

    // extends game display

    //Listen for user pressing a key, and moving human paddle in response

        @Override
        public void keyTyped(KeyEvent ev) {
            char keyPressed = ev.getKeyChar();
            char q = 'q';
            if( keyPressed == q){
                System.exit(0);    //quit if user presses the q key.
            }
        }

        @Override
        public void keyReleased(KeyEvent ev) {}   //Don't need this one, but required to implement it.

        @Override
        public void keyPressed(KeyEvent ev) {

            Main.removeInstructions = true;   //game has started

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
            ev.getComponent().repaint();   // repainting screen is ongoing and always when key is pressed
            // This calls paintComponent(Graphics g) again
        }
}
