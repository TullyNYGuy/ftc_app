package org.tullyfirst.FTC8863.lib;


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
    private Servo teamServo;

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

    //*********************************************************************************************
    //          Constructors
    //*********************************************************************************************

    public TeamServo(String servoName, HardwareMap hardwareMap, double homePosition, double upPosition, double downPosition) {
        teamServo = hardwareMap.servo.get(servoName);
        setHomePosition(homePosition);
        setUpPosition(upPosition);
        setDownPosition(downPosition);
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
}
