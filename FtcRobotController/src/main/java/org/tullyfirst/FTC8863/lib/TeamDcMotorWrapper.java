package org.tullyfirst.FTC8863.lib;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by ball on 11/28/2015.
 */
public class TeamDcMotorWrapper {

    /**
     * Defines the type of motor.
     */
    public enum MotorType {
        NXT, ANDYMARK_20, ANDYMARK_40, ANDYMARK_60, TETRIX
    }

    /**
     * Defines the state of the motor
     * IDLE = not moving, able to move freely (float)
     * HOLD = not moving but actively holding a position under PID control
     * MOVING = actvitely rotating
     * INTERUPTED = not sure about this one yet, thinking it may be needed to indicate when the
     *    motor movement was interupted before it completed
     */
    public enum MotorState {
        IDLE, HOLD, INTERUPTED, MOVING
    }

    /**
     * What to do when the current motor movement finishes
     * COAST = power removed, the motor will move freely
     * HOLD = motor is powered and actively holding a position under PID control
     */
    public enum NextMotorState {
        COAST, HOLD
    }

    /**
     * Does the motor track a series of movements with its encoders?
     * ABSOLUTE = track movements so encoders will not be reset
     * RELATIVE = every movement is individual so encoders will be reset
     */
    public enum MotorMoveType {
        ABSOLUTE, RELATIVE
    }

    //*********************************************************************************************
    //          PRIVATE DATA FIELDS
    //
    // can be accessed only by this class, or by using the public
    // getter and setter methods
    //*********************************************************************************************
    public com.qualcomm.robotcore.hardware.DcMotor FTCDcMotor;
    /**
     * Type of motor. Controls the encoder counts per revolution
     */
    private MotorType motorType = MotorType.ANDYMARK_40;
    /**
     *  Encoder counts per shaft revolution for this motor
     */
    private int countsPerRev = 0;

    /**
     *  Number of cm moved for each motor shaft revolution
     */
    private double cmPerRev = 0;

    /**
     * Hold the desired enocoder count for RUN_TO_POSITION
     */
    private int desiredEncoderCount = 0;

    /**
     * The tolerance range for saying of the encoder count has been reached.
     */
    private int encoderTolerance = 0;

    /**
     * The current state of the motor.
     */
    private MotorState motorState = MotorState.IDLE;

    /**
     * The desired state of the motor after the rotation is finished.
     */
    private NextMotorState nextMotorState = NextMotorState.COAST;

    /**
     * Relative or absolute movements
     */
    private MotorMoveType motorMoveType = MotorMoveType.RELATIVE;

    //*********************************************************************************************
    //          GETTER and SETTER Methods
    //*********************************************************************************************

    public MotorType getMotorType() {
        return motorType;
    }

    public void setMotorType(MotorType motorType) {
        this.motorType = motorType;
    }

    public int getCountsPerRev() {
        return countsPerRev;
    }

    public void setCountsPerRev(int countsPerRev) {
        this.countsPerRev = countsPerRev;
    }

    public double getCmPerRev() {
        return cmPerRev;
    }

    public void setCmPerRev(double cmPerRev) {
        this.cmPerRev = cmPerRev;
    }

    public int getDesiredEncoderCount() {
        return desiredEncoderCount;
    }

    public void setDesiredEncoderCount(int desiredEncoderCount) {
        this.desiredEncoderCount = desiredEncoderCount;
    }

    public int getEncoderTolerance() {
        return encoderTolerance;
    }

    public void setEncoderTolerance(int encoderTolerance) {
        this.encoderTolerance = encoderTolerance;
    }

    public MotorState getMotorState() {
        return motorState;
    }

    protected void setMotorState(MotorState motorState) {
        this.motorState = motorState;
    }

    public NextMotorState getNextMotorState() {
        return nextMotorState;
    }

    protected void setNextMotorState(NextMotorState nextMotorState) {
        this.nextMotorState = nextMotorState;
    }

    public MotorMoveType getMotorMoveType() {
        return motorMoveType;
    }

    public void setMotorMoveType(MotorMoveType motorMoveType) {
        this.motorMoveType = motorMoveType;
    }

    //*********************************************************************************************
    //          Constructors
    //*********************************************************************************************

    public TeamDcMotorWrapper(String motorName) {
        HardwareMap hardwareMap = new HardwareMap();
        FTCDcMotor = hardwareMap.dcMotor.get(motorName);
        initMotorDefaults();
    }

    /**
     * Set some reasonable defaults for a motor. The user should then set the real values.
     */
    private void initMotorDefaults(){
        setMotorType(MotorType.ANDYMARK_40);
        setCountsPerRevForMotorType(MotorType.ANDYMARK_40);
        setCmPerRev(0);
        setDesiredEncoderCount(0);
        setEncoderTolerance(10);
        setMotorState(MotorState.IDLE);
        setNextMotorState(NextMotorState.COAST);
        setMotorMoveType(MotorMoveType.RELATIVE);
    }

    //*********************************************************************************************
    //          Helper Methods
    //*********************************************************************************************

    /**
     * Get the number of encoder counts per revolution of the shaft based on the type of motor.
     *
     * @param motorType Type of motor.
     * @return Number of encoder counts per revolution of the output shaft of the motor
     */
    public int setCountsPerRevForMotorType(MotorType motorType) {

        int countsPerRev;

        switch (motorType){
            case NXT:
                setCountsPerRev(360);
                countsPerRev = 360;
                break;
            case ANDYMARK_20:
                setCountsPerRev(560);
                countsPerRev = 560;
                break;
            case ANDYMARK_40:
                setCountsPerRev(1120);
                countsPerRev = 1120;
                break;
            case ANDYMARK_60:
                setCountsPerRev(1680);
                countsPerRev = 1680;
                break;
            case TETRIX:
                setCountsPerRev(1440);
                countsPerRev = 1140;
                break;
            default:
                setCountsPerRev(0);
                countsPerRev = 0;
                break;
        }
        return countsPerRev;
    }

    /**
     * Calculate the number or motor revolutions needed to move whatever is attached to the motor
     * a certain distance. It uses the cmPerRev value for the calculation.
     *
     * @param distance The amount to move whatever is attached. Although "distance" implies a
     *                 distance, it could be angles, distance or any other units.
     * @return Number of motor revolutions to turn.
     */
    public double getRevsForDistance(double distance){
        return distance / getCmPerRev();
    }

    /**
     * Calculate the number of encoder counts needed to move whatever is attached to the motor
     * a certain distance. It uses the cmPerRev value and number of encoder counts per revolution
     * for the calculation. The number of encoder counts per rev is dependent on the motor type.
     *
     * @param distance The amount to move whatever is attached. Although "distance" implies a
     *                 distance, it could be angles, distance or any other units.
     * @return Number of enocder counts to turn.
     */
    public int getEncoderCountForDistance(double distance){
        return (int)((double)getCountsPerRev() * getRevsForDistance(distance));
    }

    /**
     * If the motor is set for relative movement, the encoder will be reset. But if the motor
     * is set for absolute movement, the encoder needs to keep track of where the motor is, so
     * it cannot be reset.
     */
    public void resetEncoder(){
        if (getMotorMoveType() == MotorMoveType.RELATIVE) {
            FTCDcMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        }
    }

    /**
     * If true is set, reset the encoder, no matter whether the motor is set for relative or
     * absolute movement.
     * Use this method to set the zero point on a motor that will be moved absolute from now on.
     *
     * @param override If true, then reset the encoder, no matter what.
     */
    public void resetEncoder(boolean override){
        if (override) {
            FTCDcMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        } else {
            resetEncoder();
        }
    }

    //*********************************************************************************************
    //          Methods for rotating the motor to a desired encoder count
    //*********************************************************************************************

    /**
     * Rotate the motor so that the output moves a certain distance. The encoder is used to
     * determine if the distance has been met.
     *
     * @param power Power input for the motor. Note that it will be clipped to less than +/-0.8.
     * @param distance Motor will be rotated so that it results in a movement of this distance.
     * @param afterCompletion What to do after this movement is completed: HOLD or COAST
     * @return true if successfully completed
     */
    public boolean rotateToDistance(double power, double distance, NextMotorState afterCompletion ) {
        if(this.motorState != MotorState.MOVING) {
            int encoderCountForDistance = getEncoderCountForDistance(distance);
            return rotateToEncoderCount(power, encoderCountForDistance, afterCompletion);
        } else {
            return false;
        }
    }

    /**
     * Rotate the motor so the encoder gets to a certain count.
     *
     * @param power Power input for the motor. Note that it will be clipped to less than +/-0.8.
     * @param encoderCount Motor will be rotated so that it results in a movement of this distance.
     * @param afterCompletion  What to do after this movement is completed: HOLD or COAST
     * @return true if successfully completed
     */
    public boolean rotateToEncoderCount(double power, int encoderCount, NextMotorState afterCompletion) {
        if(getMotorState() != MotorState.MOVING) {
            // set the field holding the desired rotation
            setDesiredEncoderCount(encoderCount);
            // set what to do after the rotation completes
            setNextMotorState(afterCompletion);
            // reset the encoder
            resetEncoder();
            // set the desired encoder position
            FTCDcMotor.setTargetPosition(encoderCount);
            // set the run mode
            FTCDcMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
            // clip the power so that it does not exceed 80% of max. The reason for this is that the
            // PID controller inside the core motor controller needs to have some room to increase
            // the power when it makes its PID adjustments. If you set the power to 100% then there is
            // no room to increase the power if needed and PID control will not work.
            power = Range.clip(power, -.8, .8);
            FTCDcMotor.setPower(power);
            setMotorState(MotorState.MOVING);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks to see if the rotation to encoder count has completed. If it has then it sets the
     * motor to hold that encoder count or it sets the motor so that it can move freely, depending
     * on the NextMotorState being set to HOLD or COAST.
     * Use this method in a loop to see if the movement has finished. Like and opmode loop() or in
     * a while do loop.
     *
     * @return true if movement complete
     */
    public boolean isRotationComplete() {
        // get the current encoder position
        int currentEncoderCount = FTCDcMotor.getCurrentPosition();

        // is the current position within the tolderance limit of the desired position
        if (Math.abs(this.desiredEncoderCount - currentEncoderCount) <getEncoderTolerance()) {
            // movement is complete. See what to do next
            if(getNextMotorState() == NextMotorState.COAST) {
                FTCDcMotor.setPower(0);
                setMotorState(MotorState.IDLE);
            } else {
                // we want to actively hold position
                // this is the default so don't do anything but set the state
                setMotorState(MotorState.HOLD);
            }
            return true;

        } else {
            return false;
        }
    }

    //*********************************************************************************************
    //          Methods for rotating the motor at a constant speed
    //*********************************************************************************************

    /**
     * Run the motor with encoder feedback. If there is a load on the motor the power will be
     * increased in an attempt to maintain the speed. Note that a high power command may result in
     * the PID not being able to control the motor.
     *
     * @param power Power input for the motor.
     * @param afterCompletion What to do after this movement is completed: HOLD or COAST
     * @return true if successfully completed
     */
     public boolean runUsingEncoder(double power, NextMotorState afterCompletion) {
         if(getMotorState() != MotorState.MOVING) {
            // set what to do after the rotation completes. Completion can only occur with a stop or
            // interrupt
            setNextMotorState(afterCompletion);
            // reset the encoder
            resetEncoder();
            // set the run mode
             FTCDcMotor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
             FTCDcMotor.setPower(power);
            setMotorState(MotorState.MOVING);
            return true;
        } else {
            return false;
        }
    }

    //*********************************************************************************************
    //          Methods for rotating the motor without encoders - open loop
    //*********************************************************************************************

    /**
     * Run the motor without any encoder feedback. If there is a load on the motor the speed will
     * drop.
     *
     * @param power Power input for the motor.
     * @param afterCompletion What to do after this movement is completed: HOLD or COAST
     * @return true if successfully completed
     */
     public boolean runWithoutEncoder(double power, NextMotorState afterCompletion) {
        if(getMotorState() != MotorState.MOVING) {
            // set what to do after the rotation completes. Completion can only occur with a stop or
            // interrupt
            setNextMotorState(afterCompletion);
            // reset the encoder
            resetEncoder();
            // set the run mode
            FTCDcMotor.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
            setMotorState(MotorState.MOVING);
            return true;
        } else {
            return false;
        }
    }
    //*********************************************************************************************
    //          Methods for stopping a motor
    //*********************************************************************************************

    /**
     * Interrupt the motor.
     * If the next motor state is hold, then the encoder position at the time
     * of the interrupt is set as the hold point. The motor will actively hold at that encoder
     * position.
     * If the next motor state is coast, then just shut off the motor power and the motor will
     * move freely.
     */
    public void interruptMotor() {

        if (getNextMotorState() == NextMotorState.HOLD) {
            setMotorState(MotorState.HOLD);
            rotateToEncoderCount(FTCDcMotor.getPower(), FTCDcMotor.getCurrentPosition(), NextMotorState.HOLD);
        } else {
            stopMotor();
        }
    }

    /**
     * Stop the motor. It will just coast, ie rotate freely.
     */
    public void stopMotor() {
        FTCDcMotor.setPowerFloat();
        setMotorState(MotorState.IDLE);
    }
}
