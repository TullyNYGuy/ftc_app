package com.qualcomm.ftcrobotcontroller.FTC;

public class TeleOPTelemetry extends TeleOPHardware {

    public void updateTelemetry(){
        telemetry.addData("01", "speed: " + speedMessage);
        telemetry.addData("02", "drive type: " + driveTypeMessage);
        telemetry.addData("03", "direction: " + directionMessage);
        telemetry.addData("04", "servo: " + servoSideMessage);
    }

}
