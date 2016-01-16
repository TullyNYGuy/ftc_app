package org.tullyfirst.FTC8863.lib.ResQLib;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.tullyfirst.FTC8863.lib.FTCLib.Servo8863;

public class ClimberDumpServo {

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

    private Servo8863 climberDumpServo;

    private double dumpHomePosition = 0.0;
    private double dumpUpPosition = .25;
    private double dumpDownPosition = 0.0;

    //*********************************************************************************************
    //          GETTER and SETTER Methods
    //
    // allow access to private data fields for example setMotorPower,
    // getMotorPosition
    //*********************************************************************************************

    // no getters and setters needed since the variable are all private and no one using this class
    // needs access to them. Only need getter and setter if someone needs access to the variables.

    //*********************************************************************************************
    //          Constructors
    //
    // the function that builds the class when an object is created
    // from it
    //*********************************************************************************************

    public ClimberDumpServo(HardwareMap hardwareMap) {
        //climberDumpServo = new Servo8863(RobotConfigMapping.getClim, hardwareMap, dumpHomePosition, dumpUpPosition, dumpDownPosition, Servo.Direction.REVERSE);
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
}
