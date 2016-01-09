package org.tullyfirst.FTC8863.lib.ResQLib;


import com.qualcomm.robotcore.util.Range;

public class TeleOPHardware extends init_lib {


    public enum DriveType{
        TANK, JOYSTICK
    }
    public DriveType driveType = DriveType.TANK;
    public boolean driveTogglePressed = false;
    public String driveTypeMessage;


    public enum DriveDirection{
        FORWARD, REVERSE
    }
    public DriveDirection driveDirection = DriveDirection.FORWARD;
    public boolean directionTogglePressed = false;
    public String directionMessage;


   /* public enum ServoSide{
        LEFT_SIDE, RIGHT_SIDE
    }
    public ServoSide servoSide;
    public String servoSideMessage;*/
    
    public static final float MIN_DCMOTOR_POSITION = -1;
    public static final float MAX_DCMOTOR_POSITION = 1;

    public static final double MIN_SERVO_POSITION = 0;
    public static final double MAX_SERVO_POSITION = 1;


    public float speed = 1;
    public float direction = 1;
    public double drive = 0;


    public float GP1_LY;
    public float GP1_RY;
    public float GP2_LY;


    public float rightMotors;
    public float leftMotors;


    public double s_position(double pos){

        return Range.clip(pos, MIN_SERVO_POSITION, MAX_SERVO_POSITION);
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
