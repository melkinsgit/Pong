package com.margaret;

public class Ball {
    //Checks to see if the ball has hit a wall or paddle
    //If so, bounce off the wall/paddle
    //And then move ball in the correct direction

    protected static void moveBall() {
        System.out.println("move ball");
        //move in current direction
        //bounce off walls and paddle
        //TODO Take into account speed of paddles

        //Work in double

        boolean hitWall = false;
        boolean hitHumanPaddle = false;
        boolean hitComputerPaddle = false;

        if (Main.ballX <= 0 || Main.ballX >= Main.screenSize ) {  // off the screen width-wise
            Main.gameOver = true;  // the game ends
            return; // return
        }
        if (Main.ballY <= 0 || Main.ballY >= Main.screenSize-Main.ballSize) {  // off the screen length-wise
            hitWall = true;  // going to bounce
        }

        //If ballX is at a paddle AND ballY is within the paddle size.
        //Hit human paddle?
        if (Main.ballX >= Main.screenSize-(Main.paddleDistanceFromSide+(Main.ballSize)) && (Main.ballY > Main.humanPaddleY-Main.paddleSize && Main.ballY < Main.humanPaddleY+Main.paddleSize))
            hitHumanPaddle = true;

        //Hit computer paddle?
        if (Main.ballX <= Main.paddleDistanceFromSide && (Main.ballY > Main.computerPaddleY-Main.paddleSize && Main.ballY < Main.computerPaddleY+Main.paddleSize))
            hitComputerPaddle = true;


        if (hitWall == true) {
            //bounce
            Main.ballDirection = ( (2 * Math.PI) - Main.ballDirection );
            System.out.println("ball direction " + Main.ballDirection);
        }

        //Bounce off computer paddle - just need to modify direction
        if (hitComputerPaddle == true) {
            Main.ballDirection = (Math.PI) - Main.ballDirection;
            //TODO factor in speed into new direction
            //TODO So if paddle is moving down quickly, the ball will angle more down too

        }

        //Bounce off human paddle - just need to modify direction
        if (hitHumanPaddle == true) {
            Main.ballDirection = (Math.PI) - Main.ballDirection;
            //TODO consider speed of paddle
        }

        //Now, move ball correct distance in the correct direction

        // ** TRIGONOMETRY **

        //distance to move in the X direction is ballSpeed * cos(ballDirection)
        //distance to move in the Y direction is ballSpeed * sin(ballDirection)

        Main.ballX = Main.ballX + (Main.ballSpeed * Math.cos(Main.ballDirection));
        Main.ballY = Main.ballY + (Main.ballSpeed * Math.sin(Main.ballDirection));

        // ** TRIGONOMETRY END **

    }

}
