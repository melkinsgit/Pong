package com.margaret;

public class HumanPaddle {

    static int humanPaddleY = Main.screenSize / 2 ;
    static int humanPaddleMaxSpeed = 5;   //This doesn't quite do the same thing... this is how many pixels human moves per key press TODO use this in a better way
    static int humanPaddleSpeed = 0;      // ??? never used ??? "speed" is pixels moved up or down per clock tick
    static int humanWins = 0; //Counter for how many times human wins

    protected static void moveDown() {  // TODO why did this have to become static when I took them out of PongController
        //Coordinates decrease as you go up the screen, that's why this looks backwards.
        if (humanPaddleY < Main.screenSize - Main.paddleSize) {
            humanPaddleY+=humanPaddleMaxSpeed;
        }
    }

    protected static void moveUp() {  // TODO why did this have to become static when I took them out of PongController
        //Coordinates increase as you go down the screen, that's why this looks backwards.
        if (humanPaddleY > Main.paddleSize) {
            humanPaddleY-=humanPaddleMaxSpeed;
        }
    }
}
