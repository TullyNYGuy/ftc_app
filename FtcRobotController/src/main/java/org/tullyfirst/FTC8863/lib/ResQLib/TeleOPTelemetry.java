package org.tullyfirst.FTC8863.lib.ResQLib;

public class TeleOPTelemetry extends TeleOPHardware {

    public void update_telemetry(){
        telemetry.addData("01", "speed: " + speed);
        telemetry.addData("02", "drive type: " + driveTypeMessage);
        telemetry.addData("03", "direction: " + directionMessage);

    }

}
