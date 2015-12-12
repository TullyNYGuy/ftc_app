package org.tullyfirst.FTC8863.lib.ResQLib;

public class TeleOP_8863_Telemetry extends TeleOP_8863_hardware {

    public void update_telemetry(){
        telemetry.addData("01", "speed: " + speed);
        telemetry.addData("02", "servo: " + servoSideMessage);
        telemetry.addData("03", "drive type: " + driveTypeMessage);
        telemetry.addData("04", "direction: " + directionMessage);
        telemetry.addData("04", "popper pos: " + poperPositionMessage);

    }

}
