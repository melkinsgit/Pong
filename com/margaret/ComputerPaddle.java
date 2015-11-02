package com.margaret;

/**
 * Created by sn0173nd on 11/2/2015.
 */
public class ComputerPaddle {
    //Uses the current position of ball and paddle to move the computer paddle towards the ball
    protected static void moveComputerPaddle(){

        //if ballY = 100 and paddleY is 50, difference = 50, need to adjust
        //paddleY by up to the max speed (the minimum of difference and maxSpeed)

        //if ballY = 50 and paddleY = 100 then difference = -50
        //Need to move paddleY down by the max speed

        // constantly moving with the ball because it is called in the action listener that triggers every tick of the time

        int ballPaddleDifference = Main.computerPaddleY - (int)Main.ballY;
        int distanceToMove = Math.min(Math.abs(ballPaddleDifference), Main.computerPaddleMaxSpeed);

        System.out.println("computer paddle speed = " + Main.computerPaddleSpeed);

        if (ballPaddleDifference > 0 ) {   //Difference is positive - paddle is below ball on screen
            Main.computerPaddleY -= distanceToMove;

        } else if (ballPaddleDifference < 0){
            Main.computerPaddleY += distanceToMove;

        } else {
            //Ball and paddle are aligned. Don't need to move!
            Main.computerPaddleSpeed = 0;
        }

    }

}
