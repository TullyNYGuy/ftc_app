package org.tullyfirst.FTC8863.opmodes;



public class TeleOP_8863 extends org.tullyfirst.FTC8863.lib.TeleOP_8863_Telemetry {

    public void loop(){

        //telemetry
        update_telemetry();


        //right servo low
        if (gamepad2.dpad_right && gamepad2.a){
            R_arm.setPosition(s_position(.25));
        }

        //right servo med/high
        if (gamepad2.dpad_right && gamepad2.x){
            R_arm.setPosition(s_position(.1));
        }

        //left servo low
        if (gamepad2.dpad_left && gamepad2.a){
            L_arm.setPosition(s_position(.25));
        }

        //left servo med/high
        if (gamepad2.dpad_left && gamepad2.x){
            L_arm.setPosition(s_position(.1));
        }

        //both servos up
        if (gamepad2.dpad_up){
            R_arm.setPosition(s_position(.8));
            L_arm.setPosition(s_position(.8));
        }

        //support servo
        if (gamepad2.b){
            S_arm1.setPosition(s_position(.35));
            S_arm2.setPosition(s_position(.35));
        }

        if (gamepad2.dpad_down){
            S_arm1.setPosition(s_position(1));
            S_arm2.setPosition(s_position(1));
        }

        //slow motion
        if(gamepad1.right_bumper){
            speed = 3;
        }
        else{
            speed = 1;
        }

        //reverse drive direction
        if (gamepad1.left_bumper){
            direction = -1;
        }
        else{
            direction = 1;
        }

        GP1_LY = -gamepad1.left_stick_y;
        GP1_RY = -gamepad1.right_stick_y;

        //tank drive
        L_motors = ((scale_motor_power(GP1_LY))/speed)*direction;
        R_motors = ((scale_motor_power(GP1_RY))/speed)*direction;

        set_motor(L_motors, R_motors);

    }
}
