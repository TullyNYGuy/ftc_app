package org.tullyfirst.FTC8863.lib.ResQLib;

public class RobotConfigMapping {

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

    private static String leftDriveMotorName = "leftDriveMotor";
    private static String rightDriveMotorName = "rightDriveMotor";

    private static String sweeperMotorName = "sweeperMotor";

    private static String leftZipLineServoName = "leftZipLineServo";
    private static String rightZipLineServoName = "rightZipLineServo";

    private static String linearSlideServoName = "slideServo";
    private static String dumpServoName = "dumpServo";


    //*********************************************************************************************
    //          GETTER and SETTER Methods
    //
    // allow access to private data fields for example setMotorPower,
    // getMotorPosition
    //*********************************************************************************************

    public static String getLeftDriveMotorName() {
        return leftDriveMotorName;
    }

    public static String getRightDriveMotorName() {
        return rightDriveMotorName;
    }

    public static String getSweeperMotorName() {
        return sweeperMotorName;
    }

    public static String getLeftZipLineServoName() {
        return leftZipLineServoName;
    }

    public static String getRightZipLineServoName() {
        return rightZipLineServoName;
    }

    public static String getLinearSlideServoName() {
        return linearSlideServoName;
    }

    public static String getDumpServoName() {
        return dumpServoName;
    }

    //*********************************************************************************************
    //          Constructors
    //
    // the function that builds the class when an object is created
    // from it
    //*********************************************************************************************


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
