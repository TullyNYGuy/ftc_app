package org.tullyfirst.FTC8863.lib.FTCLib;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by FTC8863 on 12/2/2015.
 */
public class TeamServo {
    //*********************************************************************************************
    //          PRIVATE DATA FIELDS
    //
    // can be accessed only by this class, or by using the public
    // getter and setter methods
    //*********************************************************************************************

    private double homePosition;
    private double upPosition;
    private double downPosition;
    private double positionOne;
    private double positionTwo;
    private double positionThree;
    private Servo teamServo;
    private Servo.Direction direction;

    //*********************************************************************************************
    //          GETTER and SETTER Methods
    //*********************************************************************************************

    public double getHomePosition() {
        return homePosition;
    }

    public void setHomePosition(double homePosition) {
        this.homePosition = homePosition;
    }

    public double getUpPosition() {
        return upPosition;
    }

    public void setUpPosition(double upPosition) {
        this.upPosition = upPosition;
    }

    public double getDownPosition() {
        return downPosition;
    }

    public void setDownPosition(double downPosition) {
        this.downPosition = downPosition;
    }

    public Servo.Direction getDirection() {
        return direction;
    }

    public void setDirection(Servo.Direction direction) {
        this.direction = direction;
    }

    public double getPositionOne() {
        return positionOne;
    }

    public void setPositionOne(double positionOne) {
        this.positionOne = positionOne;
    }

    public double getPositionTwo() {
        return positionTwo;
    }

    public void setPositionTwo(double positionTwo) {
        this.positionTwo = positionTwo;
    }

    public double getPositionThree() {
        return positionThree;
    }

    public void setPositionThree(double positionThree) {
        this.positionThree = positionThree;
    }

    //*********************************************************************************************
    //          Constructors
    //*********************************************************************************************

    public TeamServo(String servoName, HardwareMap hardwareMap, double homePosition, double upPosition, double downPosition, Servo.Direction direction) {
        teamServo = hardwareMap.servo.get(servoName);
        setHomePosition(homePosition);
        setUpPosition(upPosition);
        setDownPosition(downPosition);
        teamServo.setDirection(direction);
        setDirection(direction);
    }

    //*********************************************************************************************
    //          Public Methods
    //*********************************************************************************************

    public void goUp() {
        teamServo.setPosition(getUpPosition());
    }

    public void goDown() {
        teamServo.setPosition(getDownPosition());
    }

    public void goHome() {
        teamServo.setPosition(getHomePosition());
    }

    public void goPositionOne() {
        teamServo.setPosition(getPositionOne());
    }

    public void goPositionTwo() {
        teamServo.setPosition(getPositionTwo());
    }

    public void goPositionThree() {
        teamServo.setPosition(getPositionThree());
    }
}
