package org.tullyfirst.FTC8863.lib.ResQLib;


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


    public double s_position(double pos){

        return Range.clip(pos, MIN_SERVO_POSITION, MAX_SERVO_POSITION);
    }

    public double servoButtonToggle2Stage(boolean Button, double servoPosition, double Stage1, double Stage2, double Home){
        boolean pressed = false;
        double servoReturn = Home;
        if(Button && !pressed){
            if(servoPosition == Home){
                servoReturn = Stage1;
            }
            else if(servoPosition == Stage1){
                servoReturn = Stage2;
            }
            else if(servoPosition == Stage2){
                servoReturn = Home;
            }
            pressed = true;
        }
        if(!Button && pressed){
            pressed = false;
        }
        return servoReturn;
    }

    public void servoButtonToggle3Stage(boolean Button, double servoPosition, double Stage1, double Stage2, double Stage3, double Home){
        boolean pressed = false;
        if(Button && !pressed){
            if(servoPosition == Home){

            }
            else if(servoPosition == Stage1){

            }
            else if(servoPosition == Stage2){

            }
            else if(servoPosition == Stage3){

            }
            pressed = true;
        }
        if(!Button && pressed){
            pressed = false;
        }
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

    public enum DriveType{
        TANK, JOYSTICK
    }
    public DriveType driveType = DriveType.TANK;
    public String driveTypeMessage;

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

    public enum DriveDirection{
        FORWARD, REVERSE
    }
    public DriveDirection driveDirection = DriveDirection.FORWARD;
    public String directionMessage;

    public void driveDicrectionToggle(boolean Button){
        boolean pressed = false;
        if(Button && !pressed){
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
            pressed = true;
        }
        if(!Button && pressed){
            pressed = false;
        }
    }

    public void driveTypeToggle(boolean Button){
        boolean pressed = false;
        if(Button && !pressed){
            if(driveType == DriveType.TANK){
                driveType = DriveType.JOYSTICK;
                driveTypeMessage = "joystick";
            }
            else{
                driveType = DriveType.TANK;
                driveTypeMessage = "tank";
            }
            pressed = true;
        }
        if(!Button && pressed){
            pressed = false;
        }
    }

    public enum Speed{
        HALF, PARTIAL, FULL
    }
    public Speed speed = Speed.FULL;
    public String speedMessage;

    public void speedToggle(boolean Button){
        boolean pressed = false;

        if(Button && !pressed){
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
            pressed = true;
        }
        if(!Button && pressed){
            pressed = false;
        }
    }

    public enum ServoSide{
        LEFT_SIDE, RIGHT_SIDE
    }
    public ServoSide servoSide;
    public String servoSideMessage;

    public void servoSideToggle(boolean Button){
        boolean pressed = false;
        if(Button && !pressed){
            if(servoSide == ServoSide.LEFT_SIDE){
                servoSide = ServoSide.RIGHT_SIDE;
                servoSideMessage = "right";
            }
            else if(servoSide == ServoSide.RIGHT_SIDE){
                servoSide = ServoSide.LEFT_SIDE;
                servoSideMessage = "left";
            }
            pressed = true;
        }
        if(!Button && pressed){
            pressed = false;
        }
    }

    public enum SweeperDirection{
        FORWARDS, NEUTRAL, REVERSE
    }
    public SweeperDirection sweeperDirection = SweeperDirection.NEUTRAL;
    public String sweeperMessage;

    public void sweeperDirectionToggle(boolean Button){
        boolean pressed = false;
        if(Button && !pressed){
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
            pressed = true;
        }
        if(!Button && pressed){
            pressed = false;
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
