package org.tullyfirst.FTC8863.opmodes;



public class TeleOP_8863 extends org.tullyfirst.FTC8863.lib.TeleOP_8863_Telemetry {

    public void loop(){

        //telemetry
        update_telemetry();


        //right servo low
        if (gamepad2.dpad_right && gamepad2.a){
            R_arm.setPosition(s_position(.25));
        }

        //right servo med
        if (gamepad2.dpad_right && gamepad2.b){
            R_arm.setPosition(s_position(.1));
        }

        //right servo high
        if (gamepad2.dpad_right && gamepad2.x){
            R_arm.setPosition(s_position(.1));
        }

        //left servo low
        if (gamepad2.dpad_left && gamepad2.a){
            L_arm.setPosition(s_position(.25));
        }

        //left servo med
        if (gamepad2.dpad_left && gamepad2.b){
            L_arm.setPosition(s_position(.1));
        }

        //right servo high
        if (gamepad2.dpad_left && gamepad2.x){
            L_arm.setPosition(s_position(.1));
        }

        //both servos up
        if (gamepad2.dpad_up){
            R_arm.setPosition(s_position(.8));
            L_arm.setPosition(s_position(.8));
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

        //switches tank drive to stick drive
        if(gamepad1.dpad_down){
            drive_switch = 1;
        }
        else{
            drive_switch = 0;
        }


        //drive motors
        //tank drive
        if(drive_switch == 0){
            L_motors = ((scale_motor_power(GP1_LY))/speed)*direction;
            R_motors = ((scale_motor_power(GP1_RY))/speed)*direction;
        }

        //stick drive forward and backwards only
        if(drive_switch == 1){
            L_motors = ((scale_motor_power(GP1_RY))/speed)*direction;
            R_motors = ((scale_motor_power(GP1_RY))/speed)*direction;
        }

        set_motor(L_motors, R_motors);

    }
}
