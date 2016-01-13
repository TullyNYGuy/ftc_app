package org.tullyfirst.FTC8863.lib.ResQLib;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.tullyfirst.FTC8863.lib.FTCLib.CRServo;
import org.tullyfirst.FTC8863.lib.FTCLib.Servo8863;

public class DeliveryBox {

    //*********************************************************************************************
    //          ENUMERATED TYPES
    //
    // user defined types
    //
    //*********************************************************************************************


    //*********************************************************************************************
    //          PRIVATE DATA FIELDS
    //
    // can be accessed only by this class, or by using the public
    // getter and setter methods
    //*********************************************************************************************

    CRServo slideServo;
    Servo8863 dumpServo;

    private double dumpHomePosition = 0.0;
    private double dumpUpPosition = .25;
    private double dumpDownPosition = 0.0;

    private double slideServoZeroThrottle = .52;
    private double slideServoZeroZone = .1;

    //*********************************************************************************************
    //          GETTER and SETTER Methods
    //
    // allow access to private data fields for example setMotorPower,
    // getMotorPosition
    //*********************************************************************************************


    //*********************************************************************************************
    //          Constructors
    //
    // the function that builds the class when an object is created
    // from it
    //*********************************************************************************************

    public DeliveryBox(HardwareMap hardwareMap) {
        slideServo = new CRServo(RobotConfigMapping.getLinearSlideServoName(),hardwareMap, slideServoZeroThrottle, slideServoZeroZone);
        dumpServo = new Servo8863(RobotConfigMapping.getDumpServoName(), hardwareMap, dumpHomePosition, dumpUpPosition, dumpDownPosition, Servo.Direction.REVERSE);

        // set slide servo so box is not moving
        slideServo.updatePosition(slideServoZeroThrottle);
    }


    //*********************************************************************************************
    //          Helper Methods
    //
    // methods that aid or support the major functions in the class
    //*********************************************************************************************


    //*********************************************************************************************
    //          MAJOR METHODS
    //
    // public methods that give the class its functionality
    //*********************************************************************************************

    public void updatePosition(double throttle){
        slideServo.updatePosition(throttle);
    }

    public void raiseDumpRamp(){
        dumpServo.goUp();
    }

    public void lowerDumpRamp() {
        dumpServo.goHome();
    }


}
