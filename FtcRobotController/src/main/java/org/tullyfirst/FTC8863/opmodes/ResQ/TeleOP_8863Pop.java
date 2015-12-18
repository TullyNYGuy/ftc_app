package org.tullyfirst.FTC8863.opmodes.ResQ;


import org.tullyfirst.FTC8863.lib.ResQLib.TeleOP_8863_Telemetry;

public class TeleOP_8863Pop extends TeleOP_8863_Telemetry {
    public enum PopState {
        HOME, MOVING_TO_DOWN, DOWN, DOWN_DELAY, MOVING_TO_HOME
    }

    public PopState popState = PopState.HOME;

    public void loop(){

        //telemetry
        update_telemetry();

        //gamepad 2
        if(gamepad2.left_bumper){
            servoSide = ServoSide.LEFT_SIDE;
            servoSideMessage = "left side";
        }
        if(gamepad2.right_bumper){
            servoSide = ServoSide.RIGHT_SIDE;
            servoSideMessage = "right side";
        }

        //right servo low
        if (servoSide == ServoSide.RIGHT_SIDE && gamepad2.a){
            rightZipServoArm.setPosition(s_position(.25));
        }

        //right servo med/high
        if (servoSide == ServoSide.RIGHT_SIDE && gamepad2.x){
            rightZipServoArm.setPosition(s_position(.1));
        }

        //left servo low
        if (servoSide == ServoSide.LEFT_SIDE && gamepad2.a){
            leftZipServoArm.setPosition(s_position(.25));
        }

        //left servo med/high
        if (servoSide == ServoSide.LEFT_SIDE && gamepad2.x){
            leftZipServoArm.setPosition(s_position(.1));
        }

        //both servos up
        if (gamepad2.dpad_up){
            rightZipServoArm.setPosition(s_position(.75));
            leftZipServoArm.setPosition(s_position(.8));
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
        if(gamepad1.b){
            speed = 1f;
        }
        if(gamepad1.x){
            speed = .7f;
        }

/*        switch (popState) {
            case HOME:
                if(gamepad1.y) {
                    //robot.popperMotor.isRotationComplete();
                    robot.popperMotor.resetEncoder(true);
                    elapsedTime.reset();
                    robot.popperMotor.setTargetPosition(280);
                    robot.popperMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
                    robot.popperMotor.setPower(.95);
                    //robot.goPopperMotorPop();
                    popState = PopState.MOVING_TO_DOWN;
                }
                break;
            case MOVING_TO_DOWN:
                //if (robot.popperMotor.isRotationComplete() && elapsedTime.time() > 2.0) {
                if (elapsedTime.time() > .5) {
                    popState = PopState.DOWN;
                }
                break;
            case DOWN:
                robot.popperMotor.resetEncoder(true);
                elapsedTime.reset();
                popState = PopState.DOWN_DELAY;
                break;
            case DOWN_DELAY:
                if (elapsedTime.time() > .5) {
                    robot.popperMotor.setTargetPosition(-280);
                    robot.popperMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
                    robot.popperMotor.setPower(.95);
                    //robot.goPopperMotorHome();
                    popState = PopState.HOME;
                }
                break;
*//*            case MOVING_TO_HOME:
                if (robot.popperMotor.isRotationComplete()) {
                    popState = PopState.HOME;
                }
                break;*//*
        }*/
//        telemetry.addData("Pop State",  popState.toString());
//        telemetry.addData("Encoder", "Encoder: " + String.format("%d", robot.popperMotor.getCurrentPosition()));
/*
        //popper motor button toggle
        if(gamepad1.y && !popperMotorTogglePressed){
            if(popperPosition == PopperPosition.HOME){
                //pops robot
                robot.goPopperMotorPop();
                popperPosition = PopperPosition.DOWN;
                poperPositionMessage = "down";
            }
            else{//robot is popped
                //popper go home
                robot.goPopperMotorHome();
                popperPosition = PopperPosition.HOME;
                poperPositionMessage = "home";
            }
            popperMotorTogglePressed = true;
        }
        //if let off bumper reset toggle
        if(!gamepad1.y && popperMotorTogglePressed){
            popperMotorTogglePressed = false;
        }
*/



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


        GP1_LY = -gamepad1.left_stick_y;
        GP1_RY = -gamepad1.right_stick_y;

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

}
