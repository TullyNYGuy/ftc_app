package org.tullyfirst.FTC8863.lib.ResQLib;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.tullyfirst.FTC8863.lib.FTCLib.DriveTrain;

/**
 * Created by ball on 1/9/2016.
 */
public class ResQRobot {

    //*********************************************************************************************
    //          ENUMERATED TYPES
    //
    // user defined types
    //
    //*********************************************************************************************

    public enum Mode {
        TELEOP, AUTONOMOUS
    }

    public static DriveTrain driveTrain;

    public static DeliveryBox deliveryBox;

    //*********************************************************************************************
    //          PRIVATE DATA FIELDS
    //
    // can be accessed only by this class, or by using the public
    // getter and setter methods
    //*********************************************************************************************

    public double deliveryBoxThrottle;

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

    private ResQRobot() {
    }

    public static ResQRobot ResQRobotTeleop(HardwareMap hardwareMap) {
        ResQRobot resQRobot = new ResQRobot();
        driveTrain = DriveTrain.DriveTrainTeleop(hardwareMap);

        deliveryBox = new DeliveryBox(hardwareMap);

        return resQRobot;
    }

    public static ResQRobot ResQRobotAutonomous(HardwareMap hardwareMap) {
        ResQRobot resQRobot = new ResQRobot();
        driveTrain = DriveTrain.DriveTrainAutonomous(hardwareMap);

        deliveryBox = new DeliveryBox(hardwareMap);

        return resQRobot;
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

    public void updateRobot(){
        deliveryBox.updatePosition(deliveryBoxThrottle);
    }
}
