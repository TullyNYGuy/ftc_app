package org.tullyfirst.FTC8863.lib.ResQLib;


import com.qualcomm.robotcore.util.Range;

public class TeleOP_8863_hardware extends init_lib {


    public static final float MIN_DCMOTOR_POSITION = -1;
    public static final float MAX_DCMOTOR_POSITION = 1;

    public static final double MIN_SERVO_POSITION = 0;
    public static final double MAX_SERVO_POSITION = 1;

    public float speed = 1;
    public float direction = 1;
    public double drive = 0;

    public boolean directionToggle = true;
    public boolean driveToggle = true;
    public boolean directionTogglePressed = false;
    public boolean driveTogglePressed = false;

    public float GP1_LY;
    public float GP1_RY;

    public float R_motors;
    public float L_motors;


    @Override
    public void start(){
        leftZipServoArm.setPosition(s_position(.85));
        rightZipServoArm.setPosition(s_position(.75));
        helperServoArm1.setPosition(s_position(1));
        helperServoArm2.setPosition(s_position(1));
        climberServoArm.setPosition(s_position(0));
    }//start

    @Override
    public void stop(){
        leftZipServoArm.setPosition(s_position(.85));
        rightZipServoArm.setPosition(s_position(.75));
        helperServoArm1.setPosition(s_position(1));
        helperServoArm2.setPosition(s_position(1));
        climberServoArm.setPosition(s_position(0));
    }//stop


    public double R_servo_pos(){return rightZipServoArm.getPosition();}
    public double L_servo_pos(){return leftZipServoArm.getPosition();}
    public double S_servo_pos(){return helperServoArm1.getPosition();}

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

    public double left_motor_power(){
        return leftDriveMotor.getPower();
    }

    public double right_motor_power(){
        return rightDriveMotor.getPower();
    }

}
