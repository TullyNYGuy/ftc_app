package org.tullyfirst.FTC8863.lib.ResQLib;

public class TeleOP_8863_Telemetry extends TeleOP_8863_hardware {

    public void update_telemetry(){

        telemetry.addData("01","left motor: " + left_motor_power());
        telemetry.addData("02","right motors: " + right_motor_power());
        telemetry.addData("03", "speed: " + speed);
        telemetry.addData("04", "R_arm: " + R_servo_pos());
        telemetry.addData("05", "L_arm: " + L_servo_pos());
        telemetry.addData("06", "s_arm: " + S_servo_pos());

    }

}
