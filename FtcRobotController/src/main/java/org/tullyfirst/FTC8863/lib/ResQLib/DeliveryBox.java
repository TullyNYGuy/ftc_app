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

    /**
     * Amount of time to wait between "wiggle" movements when dump ramp is wiggling
     */
    private double dumpWiggleDelay = .5;

    /**
     * Amount of time to wiggle the dump ramp trying to get debris out of the box
     */
    private double dumpWiggleTime = 5;

    /**
     * Amount of movement for each wiggle of the dump ramp
     */
    private double dumpWiggleDelta = -.25;

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
        dumpServo = new Servo8863(RobotConfigMapping.getRampServoName(), hardwareMap, dumpHomePosition, dumpUpPosition, dumpDownPosition, Servo.Direction.REVERSE);

        //setup the dump ramp wiggle for later use
        dumpServo.setupWiggle(dumpUpPosition, dumpWiggleDelay, dumpWiggleDelta, dumpWiggleTime);

        //for reference using a touch sensor
        // private TouchSensor v_sensor_touch;
        //v_sensor_touch = hardwareMap.touchSensor.get ("sensor_touch");
        //v_sensor_touch.isPressed ();
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

    public void initDeliveryBox() {
        // set slide servo so box is not moving
        slideServo.updatePosition(slideServoZeroThrottle);
    }

    public void updateDeliveryBox(double throttle){
        // update the linear slider throttle
        slideServo.updatePosition(throttle);

        //update the wiggle for the dump ramp (if any)
        dumpServo.updateWiggle();
    }

    public void raiseDumpRamp(){
        dumpServo.goUp();
    }

    public void lowerDumpRamp() {
        dumpServo.goHome();
    }

    public void startWiggleDumpRamp() {
        dumpServo.startWiggle();
    }

    public void stopWiggleDumpRamp() {
        dumpServo.stopWiggle();
    }


}
