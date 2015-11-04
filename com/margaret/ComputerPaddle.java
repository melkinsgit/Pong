package com.margaret;

public class ComputerPaddle {

    static int computerPaddleY = Main.screenSize / 2 ;  //location of the center of the paddles on the Y-axis of the screen
    static int computerPaddleMaxSpeed = 3;  //Max number of pixels that computer paddle can move clock tick. Higher number = easier for computer
    static int computerPaddleSpeed = 0;  // same
    static int computerWins = 0; // Counter for how many times computer wins

    //Uses the current position of ball and paddle to move the computer paddle towards the ball
    protected static void moveComputerPaddle(){

        //if ballY = 100 and paddleY is 50, difference = 50, need to adjust
        //paddleY by up to the max speed (the minimum of difference and maxSpeed)

        //if ballY = 50 and paddleY = 100 then difference = -50
        //Need to move paddleY down by the max speed

        // constantly moving with the ball because it is called in the action listener that triggers every tick of the time

        int ballPaddleDifference = computerPaddleY - (int)Ball.ballY;
        int distanceToMove = Math.min(Math.abs(ballPaddleDifference), computerPaddleMaxSpeed);

        System.out.println("computer paddle speed = " + computerPaddleSpeed);

        if (ballPaddleDifference > 0 ) {   //Difference is positive - paddle is below ball on screen
            computerPaddleY -= distanceToMove;

        } else if (ballPaddleDifference < 0){
            computerPaddleY += distanceToMove;

        } else {
            //Ball and paddle are aligned. Don't need to move!
            computerPaddleSpeed = 0;
        }

    }

}
