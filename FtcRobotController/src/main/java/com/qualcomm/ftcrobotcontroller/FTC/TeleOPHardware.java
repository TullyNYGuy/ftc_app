package com.qualcomm.ftcrobotcontroller.FTC;


import com.qualcomm.robotcore.util.Range;

public class TeleOPHardware extends init_lib {

    public static final float MIN_DCMOTOR_POSITION = -1;
    public static final float MAX_DCMOTOR_POSITION = 1;
    public static final double MIN_SERVO_POSITION = 0;
    public static final double MAX_SERVO_POSITION = 1;

    public float driveSpeed = 1;
    public float direction = 1;
    public double drive = 0;

    float rightMotors;
    float leftMotors;

    boolean servoPressed = false;
    boolean servo2StagePressed = false;
    boolean servo3StagePressed = false;


    public double s_position(double pos){
        return Range.clip(pos, MIN_SERVO_POSITION, MAX_SERVO_POSITION);
    }

    public double servoButtonToggle(boolean Button, double servoPosition, double Stage1, double Home){
        double servoReturn = Home;
        if(Button && !servoPressed){
            if(servoPosition == Home){
                servoReturn = Stage1;
            }
            else if(servoPosition == Stage1){
                servoReturn = Home;
            }
            servoPressed = true;
        }
        if(!Button && servoPressed){
            servoPressed = false;
        }
        return servoReturn;
    }

    public double servoButtonToggle2Stage(boolean Button, double servoPosition, double Stage1, double Stage2, double Home){
        double servo2StageReturn = Home;
        if(Button && !servo2StagePressed){
            if(servoPosition == Home){
                servo2StageReturn = Stage1;
            }
            else if(servoPosition == Stage1){
                servo2StageReturn = Stage2;
            }
            else if(servoPosition == Stage2){
                servo2StageReturn = Home;
            }
            servo2StagePressed = true;
        }
        if(!Button && servo2StagePressed){
            servo2StagePressed = false;
        }
        return servo2StageReturn;
    }

    public double servoButtonToggle3Stage(boolean Button, double servoPosition, double Stage1, double Stage2, double Stage3, double Home){
        double servos3StageReturn = Home;
        if(Button && !servo3StagePressed){
            if(servoPosition == Home){
                servos3StageReturn = Stage1;
            }
            else if(servoPosition == Stage1){
                servos3StageReturn = Stage2;
            }
            else if(servoPosition == Stage2){
                servos3StageReturn = Stage3;
            }
            else if(servoPosition == Stage3){
                servos3StageReturn = Home;
            }
            servo3StagePressed = true;
        }
        if(!Button && servo3StagePressed){
            servo3StagePressed = false;
        }
        return servos3StageReturn;
    }


    //motor scale
    public float scale_motor_power (float p_power) {
        float l_scale;
        float l_power = Range.clip (p_power, MIN_DCMOTOR_POSITION, MAX_DCMOTOR_POSITION);
        float[] l_array =
                { 0.00f, 0.05f, 0.09f, 0.10f, 0.12f
                        , 0.15f, 0.18f, 0.24f, 0.30f, 0.36f
                        , 0.43f, 0.50f, 0.60f, 0.72f, 0.85f
                        , 1.00f, 1.00f
                };
        int l_index = (int)(l_power * 16.0);
        if (l_index < 0)
        {
            l_index = -l_index;
        }
        else if (l_index > 16)
        {
            l_index = 16;
        }
        if (l_power < 0)
        {
            l_scale = -l_array[l_index];
        }
        else
        {
            l_scale = l_array[l_index];
        }

        return l_scale;
    }

    public void set_motor(double left_power, double right_power) {
        leftDriveMotor.setPower(left_power);
        rightDriveMotor.setPower(right_power);
    }

    public void Drive(){
        //tank drive
        if(driveType == DriveType.TANK) {
            leftMotors = ((scale_motor_power(-gamepad1.left_stick_y))*driveSpeed)*direction;
            rightMotors = ((scale_motor_power(-gamepad1.right_stick_y))*driveSpeed)*direction;
        }

        //forward/backward
        if(driveType == DriveType.JOYSTICK){
            leftMotors = ((scale_motor_power(-gamepad1.left_stick_y))*driveSpeed)*direction;
            rightMotors = ((scale_motor_power(-gamepad1.right_stick_y))*driveSpeed)*direction;
        }


        set_motor(leftMotors, rightMotors);
    }

    public enum DriveType{
        TANK, JOYSTICK
    }
    public DriveType driveType = DriveType.TANK;
    public String driveTypeMessage;
    boolean drivePressed = false;

    public void driveTypeToggle(boolean Button){
        if(Button && !drivePressed){
            if(driveType == DriveType.TANK){
                driveType = DriveType.JOYSTICK;
                driveTypeMessage = "joystick";
            }
            else{
                driveType = DriveType.TANK;
                driveTypeMessage = "tank";
            }
            drivePressed = true;
        }
        if(!Button && drivePressed){
            drivePressed = false;
        }
    }

    public enum DriveDirection{
        FORWARD, REVERSE
    }
    public DriveDirection driveDirection = DriveDirection.FORWARD;
    public String directionMessage;
    boolean directionPressed = false;

    public void driveDicrectionToggle(boolean Button){
        if(Button && !directionPressed){
            if(driveDirection == DriveDirection.FORWARD){
                direction = -1;
                driveDirection = DriveDirection.REVERSE;
                directionMessage = "reverse";
            }
            else{
                direction = 1;
                driveDirection = DriveDirection.FORWARD;
                directionMessage = "forward";
            }
            directionPressed = true;
        }
        if(!Button && directionPressed){
            directionPressed = false;
        }
    }

    public enum Speed{
        HALF, PARTIAL, FULL
    }
    public Speed speed = Speed.FULL;
    public String speedMessage;
    boolean speedPressed = false;

    public void speedToggle(boolean Button){
        if(Button && !speedPressed){
            if(speed == Speed.FULL){
                speed = Speed.HALF;
                speedMessage = "Half";
            }
            else if(speed == Speed.HALF){
                speed = Speed.PARTIAL;
                speedMessage = "Partial";
            }
            else if(speed == Speed.PARTIAL){
                speed = Speed.FULL;
                speedMessage = "Full";
            }
            speedPressed = true;
        }
        if(!Button && speedPressed){
            speedPressed = false;
        }
    }

    public enum ServoSide{
        LEFT_SIDE, RIGHT_SIDE
    }
    public ServoSide servoSide;
    public String servoSideMessage;
    boolean servoSidePressed = false;

    public void servoSideToggle(boolean Button){
        if(Button && !servoSidePressed){
            if(servoSide == ServoSide.LEFT_SIDE){
                servoSide = ServoSide.RIGHT_SIDE;
                servoSideMessage = "right";
            }
            else if(servoSide == ServoSide.RIGHT_SIDE){
                servoSide = ServoSide.LEFT_SIDE;
                servoSideMessage = "left";
            }
            servoSidePressed = true;
        }
        if(!Button && servoSidePressed){
            servoSidePressed = false;
        }
    }

    public enum SweeperDirection{
        FORWARDS, NEUTRAL, REVERSE
    }
    public SweeperDirection sweeperDirection = SweeperDirection.NEUTRAL;
    public String sweeperMessage;
    boolean sweeperPressed = false;

    public void sweeperDirectionToggle(boolean Button){
        if(Button && !sweeperPressed){
            if(sweeperDirection == SweeperDirection.NEUTRAL){
                sweeperDirection = SweeperDirection.FORWARDS;
                sweeperMessage = "forwards";
            }
            else if(sweeperDirection == SweeperDirection.FORWARDS){
                sweeperDirection = SweeperDirection.REVERSE;
                sweeperMessage = "reverse";
            }
            else if(sweeperDirection == SweeperDirection.REVERSE){
                sweeperDirection = SweeperDirection.NEUTRAL;
                sweeperMessage = "neutral";
            }
            sweeperPressed = true;
        }
        if(!Button && sweeperPressed){
            sweeperPressed = false;
        }
    }




    /*public void toggleButton(boolean button, float switching, float state1, float state2){

        boolean pressed = false;

        if(button && !pressed){
            if(switching == state1){
                switching = state2;
            }
            else{
                switching = state1;
            }
            pressed = true;
        }
        if(!button && pressed){
            pressed = false;
        }
    }*/

}
