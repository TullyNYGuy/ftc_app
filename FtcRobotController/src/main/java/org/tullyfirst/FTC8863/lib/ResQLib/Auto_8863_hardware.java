package org.tullyfirst.FTC8863.lib.ResQLib;

import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.Range;


public class Auto_8863_hardware extends init_lib {

    public static final double MIN_SERVO_POSITION = 0;
    public static final double MAX_SERVO_POSITION = 1;

    public String t_message;

    @Override
    public void start(){
        leftZipServoArm.setPosition(s_position(.8));
        rightZipServoArm.setPosition(s_position(.75));
        helperServoArm1.setPosition(s_position(1));
        helperServoArm2.setPosition(s_position(1));
        climberServoArm.setPosition(s_position(0));
    }//start

    @Override
    public void stop(){
        leftZipServoArm.setPosition(s_position(.8));
        rightZipServoArm.setPosition(s_position(.75));
        helperServoArm1.setPosition(s_position(1));
        helperServoArm2.setPosition(s_position(1));
        climberServoArm.setPosition(s_position(0));
    }//stop

    public double s_position(double pos){

        return Range.clip(pos, MIN_SERVO_POSITION, MAX_SERVO_POSITION);
    }


    double L_drive_power(){
        double l_return = 0.0;

        if(leftDriveMotor != null){
            l_return = leftDriveMotor.getPower ();
        }

        return l_return;
    }

    double R_drive_power(){
        double l_return = 0.0;

        if(rightDriveMotor != null){
            l_return = leftDriveMotor.getPower ();
        }

        return l_return;
    }

    void set_drive_power(double left_power, double right_power){
        if(leftDriveMotor != null){
            leftDriveMotor.setPower(left_power);
        }
        if(rightDriveMotor != null){
            rightDriveMotor.setPower(right_power);
        }
    }


    void L_motor_using_encoder(){
        if(leftDriveMotor != null){
            leftDriveMotor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        }
    }

    void R_motor_using_encoder(){
        if(rightDriveMotor != null){
            rightDriveMotor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        }
    }

    public void run_using_encoder(){
        R_motor_using_encoder();
        L_motor_using_encoder();
    }


    void L_motor_without_encoder(){
        if(leftDriveMotor != null){
            if(leftDriveMotor.getMode() == DcMotorController.RunMode.RESET_ENCODERS){
                leftDriveMotor.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
            }
        }
    }

    void R_motor_without_encoder(){
        if(rightDriveMotor != null){
            if(rightDriveMotor.getMode() == DcMotorController.RunMode.RESET_ENCODERS){
                rightDriveMotor.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
            }
        }
    }

    public void runWithoutEncoder(){
        R_motor_without_encoder();
        L_motor_without_encoder();
    }


    void L_motor_reset(){
        if(leftDriveMotor != null){
            leftDriveMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        }
    }

    void R_motor_reset(){
        if(rightDriveMotor != null){
            rightDriveMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        }
    }

    public void motorEncoderReset(){
        R_motor_reset();
        L_motor_reset();
    }


    int L_encoder_count(){
        int l_return = 0;

        if(leftDriveMotor != null){
            l_return = leftDriveMotor.getCurrentPosition ();
        }

        return l_return;
    }

    int R_encoder_count(){
        int l_return = 0;

        if(rightDriveMotor != null){
            l_return = rightDriveMotor.getCurrentPosition ();
        }
        return l_return;
    }

    boolean if_L_encoder_reached(double p_count){
        boolean l_return = false;

        if(leftDriveMotor != null){
            // TODO Implement stall code using these variables.
            if(Math.abs (leftDriveMotor.getCurrentPosition()) > p_count){
                l_return = true;
            }
        }
        return l_return;
    }

    boolean if_R_encoder_reached(double p_count){
        boolean l_return = false;

        if(rightDriveMotor != null){
            // TODO Implement stall code using these variables.
            if(Math.abs (rightDriveMotor.getCurrentPosition()) > p_count){
                l_return = true;
            }
        }
        return l_return;
    }

    public boolean ifEncodersReached(double p_left_count, double p_right_count){
        boolean l_return = false;

        if(if_L_encoder_reached(p_left_count) && if_R_encoder_reached(p_right_count)){
            l_return = true;
        }

        return l_return;

    }

    public boolean driveUsingEncoders(double p_left_power, double p_right_power, double p_left_count, double p_right_count){
        boolean l_return = false;
        run_using_encoder();
        set_drive_power (p_left_power, p_right_power);

        if(ifEncodersReached(p_left_count, p_right_count)){
            motorEncoderReset();
            set_drive_power (0.0f, 0.0f);
            l_return = true;
        }

        return l_return;
    }

    boolean if_L_encoder_reset(){
        boolean l_return = false;

        if(L_encoder_count () == 0){
            l_return = true;
        }

        return l_return;
    }

    boolean if_R_encoder_reset(){
        boolean l_return = false;

        if(R_encoder_count () == 0){

            l_return = true;
        }

        return l_return;
    }

    public boolean ifEncodersReset(){
        boolean l_return = false;

        if(if_L_encoder_reset() && if_R_encoder_reset()){

            l_return = true;
        }

        return l_return;
    }



}
