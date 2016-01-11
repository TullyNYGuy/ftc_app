package org.tullyfirst.FTC8863.opmodes.ResQ;

import org.tullyfirst.FTC8863.lib.ResQLib.TeleOPTelemetry;

public class TeleOP extends TeleOPTelemetry {


    @Override
    public void start(){

    }//start


    @Override
    public void loop(){

        updateTelemetry();

        sweeperDirectionToggle(gamepad2.a);

        servoSideToggle(gamepad2.right_bumper);

        speedToggle(gamepad1.a);

        driveDicrectionToggle(gamepad1.right_bumper);

        driveTypeToggle(gamepad1.left_bumper);

        Drive();

    }//loop


    @Override
    public void stop(){

    }//stop

}
