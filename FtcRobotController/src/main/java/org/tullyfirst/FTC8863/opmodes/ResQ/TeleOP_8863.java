package org.tullyfirst.FTC8863.opmodes.ResQ;

import org.tullyfirst.FTC8863.lib.ResQLib.TeleOP_8863_Telemetry;

public class TeleOP_8863 extends TeleOP_8863_Telemetry {


    @Override
    public void start(){

    }//start


    @Override
    public void loop(){

        //gamepad update
        GP1_LY = -gamepad1.left_stick_y;
        GP1_RY = -gamepad1.right_stick_y;
        //GP2_LY = -gamepad2.right_stick_y;

        //telemetry
        update_telemetry();

        //tape motor
        //tapeMotor.setPower(scale_motor_power(GP2_LY));

        //gamepad 1
        //speed change
        if(gamepad1.a){
            speed = .5f;
        }
        if(gamepad1.b){
            speed = 1f;
        }
        if(gamepad1.x){
            speed = .7f;
        }


        //direction of robot drive toggle
        if(gamepad1.right_bumper && !directionTogglePressed){
            if(driveDirection == DriveDirection.FORWARD){
                //fliping direction to reverse
                direction = -1;
                driveDirection = DriveDirection.REVERSE;
                directionMessage = "reverse";
            }
            else{//drive is reverse
                //flip to forwards
                direction = 1;
                driveDirection = DriveDirection.FORWARD;
                directionMessage = "forward";
            }
            directionTogglePressed = true;
        }
        //if let off bumper reset toggle
        if(!gamepad1.right_bumper && directionTogglePressed){
            directionTogglePressed = false;
        }


        //type of drive toggle
        if(gamepad1.left_bumper && !driveTogglePressed){
            if(driveType == DriveType.TANK){
                driveType = DriveType.JOYSTICK;
                driveTypeMessage = "joystick";
            }
            else{
                driveType = DriveType.TANK;
                driveTypeMessage = "tank";
            }
            driveTogglePressed = true;
        }
        if(!gamepad1.left_bumper){
            driveTogglePressed = false;
        }


        //tank drive
        if(driveType == DriveType.TANK) {
            leftMotors = ((scale_motor_power(GP1_LY))*speed)*direction;
            rightMotors = ((scale_motor_power(GP1_RY))*speed)*direction;
        }

        //forward/backward
        if(driveType == DriveType.JOYSTICK){
            leftMotors = ((scale_motor_power(GP1_RY))*speed)*direction;
            rightMotors = ((scale_motor_power(GP1_RY))*speed)*direction;
        }


        set_motor(leftMotors, rightMotors);

    }//loop


    @Override
    public void stop(){

    }//stop

}
