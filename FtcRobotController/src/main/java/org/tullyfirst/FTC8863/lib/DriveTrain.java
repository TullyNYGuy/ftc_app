package org.tullyfirst.FTC8863.lib;

import java.util.HashMap;

/**
 * Created by ball on 11/26/2015.
 */
public class DriveTrain {

    private float rightPower = 0;
    private float leftPower = 0;

    public float getRightPower(){
        return this.rightPower;
    }

    public void setRightPower(float power){
        this.rightPower = power;
    }

    public float getLeftPower(){
        return this.leftPower;
    }

    public void setLeftPower(float power){
        this.leftPower = power;
    }

    // throttle: left_stick_y ranges from -1 to 1, where -1 is full up, and
    // 1 is full down
    // direction: left_stick_x ranges from -1 to 1, where -1 is full left
    // and 1 is full right

    // The differentialDrive is meant to use one joystick to control the drive train.
    // Moving the joystick forward and backward controls speed (throttle).
    // Moving the joystick left or right controls direction.
    /**
     *
     * @param throttle
     * @param direction
     */
    public void differentialDrive(double throttle, double direction){
        // since up on the joystick is negative, need to adjust the sign
        throttle = throttle * -1;

        // To steer the robot left, the left motor needs to reduce power and the right needs to increase.
        // To steer the robot right, the left motor needs to increase power and the left needs to reduce.
        // Since left on the joystick is negative, we need to add the direction for the left motor and
        // subtract from the right motor
        this.leftPower = (float)(throttle + direction);
        this.rightPower = (float)(throttle - direction);
    }

    // The tank drive uses the left joystick to control the left drive motor and the right joystick
    // to control the right drive motor.

    /**
     *
     * @param leftJoystick
     * @param rightJoystick
     */
    public void tankDrive(double leftJoystick, double rightJoystick){
        this.leftPower = (float)leftJoystick;
        this.rightPower = (float)rightJoystick;
    }
}
