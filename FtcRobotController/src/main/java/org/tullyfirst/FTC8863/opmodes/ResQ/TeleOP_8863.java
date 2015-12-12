package org.tullyfirst.FTC8863.opmodes.ResQ;


import org.tullyfirst.FTC8863.lib.ResQ.TeleOP_8863_Telemetry;

public class TeleOP_8863 extends TeleOP_8863_Telemetry {

    public void loop(){

        //telemetry
        update_telemetry();

        //gamepad 2
        //right servo low
        if (gamepad2.dpad_right && gamepad2.a){
            rightZipServoArm.setPosition(s_position(.25));
        }

        //right servo med/high
        if (gamepad2.dpad_right && gamepad2.x){
            rightZipServoArm.setPosition(s_position(.1));
        }

        //left servo low
        if (gamepad2.dpad_left && gamepad2.a){
            leftZipServoArm.setPosition(s_position(.25));
        }

        //left servo med/high
        if (gamepad2.dpad_left && gamepad2.x){
            leftZipServoArm.setPosition(s_position(.1));
        }

        //both servos up
        if (gamepad2.dpad_up){
            rightZipServoArm.setPosition(s_position(.75));
            leftZipServoArm.setPosition(s_position(.85));
        }

        //support servo
        if (gamepad2.b){
            helperServoArm1.setPosition(s_position(.35));
            helperServoArm2.setPosition(s_position(.35));
        }

        if (gamepad2.y){
            helperServoArm1.setPosition(s_position(1));
            helperServoArm2.setPosition(s_position(1));
        }

        //C_srm
        if(gamepad2.dpad_down){
            climberServoArm.setPosition(s_position(.7));
        }
        else{
            climberServoArm.setPosition(s_position(0));
        }

        //gamepad 1
        //speed change
        if(gamepad1.a){
            speed = .5f;
        }
        if (gamepad1.b){
            speed = 1f;
        }
        if (gamepad1.x){
            speed = .7f;
        }

        //direction toggle
        if(gamepad1.right_bumper && !directionTogglePressed){
            if(directionToggle){
                directionToggle = false;
            }
            else if(!directionToggle){
                directionToggle = true;
            }
            directionTogglePressed = true;
        }

        if(directionToggle){
            direction = 1;
        }
        if(!directionToggle){
            direction = -1;
        }

        //drive toggle
        if(gamepad1.left_bumper && !driveTogglePressed){
            if(driveToggle){
                driveToggle = false;
            }
            else if(!driveToggle){
                driveToggle = true;
            }
            driveTogglePressed = true;
        }


        GP1_LY = -gamepad1.left_stick_y;
        GP1_RY = -gamepad1.right_stick_y;

        //tank drive
        if (driveToggle) {
            L_motors = ((scale_motor_power(GP1_LY))*speed)*direction;
            R_motors = ((scale_motor_power(GP1_RY))*speed)*direction;
        }

        //forward/backward
        if(!driveToggle){
            L_motors = ((scale_motor_power(GP1_RY))*speed)*direction;
            R_motors = ((scale_motor_power(GP1_RY))*speed)*direction;
        }


        set_motor(L_motors, R_motors);

    }//loop
}
