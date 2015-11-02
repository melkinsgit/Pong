package com.margaret;

public class HumanPaddle {

    protected static void moveDown() {  // TODO why did this have to become static when I took them out of PongController
        //Coordinates decrease as you go up the screen, that's why this looks backwards.
        if (Main.humanPaddleY < Main.screenSize - Main.paddleSize) {
            Main.humanPaddleY+=Main.humanPaddleMaxSpeed;
        }
    }

    protected static void moveUp() {  // TODO why did this have to become static when I took them out of PongController
        //Coordinates increase as you go down the screen, that's why this looks backwards.
        if (Main.humanPaddleY > Main.paddleSize) {
            Main.humanPaddleY-=Main.humanPaddleMaxSpeed;
        }
    }
}
