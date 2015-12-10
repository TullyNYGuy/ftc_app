package org.tullyfirst.FTC8863.lib;

import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.tullyfirst.FTC8863.lib.TeamDcMotorWrapper;

import java.util.HashMap;

/**
 * Created by ball on 11/26/2015.
 */
public class TwoMotorDriveTrain {

    private float rightPower = 0;
    private float leftPower = 0;
    private float cmPerRotation = 0;

    public TeamDcMotorWrapper rightDriveMotor;
    public TeamDcMotorWrapper leftDriveMotor;

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

    public float getCmPerRotation() {
        return cmPerRotation;
    }

    public void setCmPerRotation(float cmPerRotation) {
        this.cmPerRotation = cmPerRotation;
    }

    public TwoMotorDriveTrain(HardwareMap hardwareMap) {
        leftDriveMotor = new TeamDcMotorWrapper("leftDriveMotor", hardwareMap);
        rightDriveMotor = new TeamDcMotorWrapper("rightDriveMotor", hardwareMap);

        rightDriveMotor.setDirection(DcMotor.Direction.REVERSE);
        rightDriveMotor.setMaxMotorPower(1);
        rightDriveMotor.setMinMotorPower(-1);
        rightDriveMotor.setMotorType(TeamDcMotorWrapper.MotorType.ANDYMARK_40);
        rightDriveMotor.setCountsPerRevForMotorType(TeamDcMotorWrapper.MotorType.ANDYMARK_40);
        rightDriveMotor.setMotorMoveType(TeamDcMotorWrapper.MotorMoveType.RELATIVE);
        rightDriveMotor.setEncoderTolerance(5);
        rightDriveMotor.setCmPerRev(10);

        rightDriveMotor.setMaxMotorPower(1);
        rightDriveMotor.setMinMotorPower(-1);
        rightDriveMotor.setMotorType(TeamDcMotorWrapper.MotorType.ANDYMARK_40);
        rightDriveMotor.setCountsPerRevForMotorType(TeamDcMotorWrapper.MotorType.ANDYMARK_40);
        rightDriveMotor.setMotorMoveType(TeamDcMotorWrapper.MotorMoveType.RELATIVE);
        rightDriveMotor.setEncoderTolerance(5);
        rightDriveMotor.setCmPerRev(10);
    }

    public void rotateToDistance(double power, double distance){
        rightDriveMotor.rotateToDistance(power, distance, TeamDcMotorWrapper.NextMotorState.HOLD);
        rightDriveMotor.rotateToDistance(power, distance, TeamDcMotorWrapper.NextMotorState.HOLD);
    }

    public boolean isRotationComplete() {
        if (rightDriveMotor.isRotationComplete() && leftDriveMotor.isRotationComplete()) {
            return true;
        } else {
            return false;
        }

    }
    // Joystick info for reference.
    // Normally:
    // y ranges from -1 to 1, where -1 is full up and 1 is full down
    // x ranges from -1 to 1, where -1 is full left and 1 is full right
    // The Joystick object allow you to invert the sign on the joystick
    // I like to think of the Y up as positive so:
    // All code assumes that joystick y is positive when the joystick is up.
    // Use the JoyStick object INVERT_SIGN to accomplish that.

    // The differentialDrive is meant to use one joystick to control the drive train.
    // Moving the joystick forward and backward controls speed (throttle).
    // Moving the joystick left or right controls direction.
    /**
     * Differential drive has a master speed that gets applied to both motors. That speed is the
     * same. Then the speed to the left and right is adjusted up and down, opposite of each other
     * to turn the robot.
     * @param throttle Master speed applied to both motors.
     * @param direction Adjustment applied to the master speed. Add to left. Subtract from right.
     */
    public void differentialDrive(double throttle, double direction){

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
     * The tank drive applies power values to the left and right motors separately.
     *
     * @param leftValue
     * @param rightValue
     */
    public void tankDrive(double leftValue, double rightValue){
        this.leftPower = (float)leftValue;
        this.rightPower = (float)rightValue;
    }
}
