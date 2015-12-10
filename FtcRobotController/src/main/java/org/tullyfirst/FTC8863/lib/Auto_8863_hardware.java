package org.tullyfirst.FTC8863.lib;

import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.Range;


public class Auto_8863_hardware extends init_lib{

    public static final double MIN_SERVO_POSITION = 0;
    public static final double MAX_SERVO_POSITION = 1;

    public String t_message;

    @Override
    public void start(){
        L_arm.setPosition(s_position(.85));
        R_arm.setPosition(s_position(.75));
        S_arm1.setPosition(s_position(1));
        S_arm2.setPosition(s_position(1));
        C_arm.setPosition(s_position(0));
    }//start

    @Override
    public void stop(){
        L_arm.setPosition(s_position(.85));
        R_arm.setPosition(s_position(.75));
        S_arm1.setPosition(s_position(1));
        S_arm2.setPosition(s_position(1));
        C_arm.setPosition(s_position(0));
    }//stop

    public double s_position(double pos){

        return Range.clip(pos, MIN_SERVO_POSITION, MAX_SERVO_POSITION);
    }


    double L_drive_power(){
        double l_return = 0.0;

        if(motor_LF != null){
            l_return = motor_LF.getPower ();
        }

        return l_return;
    }

    double R_drive_power(){
        double l_return = 0.0;

        if(motor_RF != null){
            l_return = motor_LF.getPower ();
        }

        return l_return;
    }

    void set_drive_power(double left_power, double right_power){
        if(motor_LF != null){
            motor_LF.setPower(left_power);
        }
        if(motor_RF != null){
            motor_RF.setPower(right_power);
        }
    }


    public void L_motor_using_encoder(){
        if(motor_LF != null){
            motor_LF.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        }
    }

    public void R_motor_using_encoder(){
        if(motor_RF != null){
            motor_RF.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        }
    }

    public void run_using_encoder(){
        R_motor_using_encoder();
        L_motor_using_encoder();
    }


    public void L_motor_without_encoder(){
        if(motor_LF != null){
            if(motor_LF.getMode() == DcMotorController.RunMode.RESET_ENCODERS){
                motor_LF.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
            }
        }
    }

    public void R_motor_without_encoder(){
        if(motor_RF != null){
            if(motor_RF.getMode() == DcMotorController.RunMode.RESET_ENCODERS){
                motor_RF.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
            }
        }
    }

    public void run_without_encoder(){
        R_motor_without_encoder();
        L_motor_without_encoder();
    }


    public void L_motor_reset(){
        if(motor_LF != null){
            motor_LF.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        }
    }

    public void R_motor_reset(){
        if(motor_RF != null){
            motor_RF.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        }
    }

    public void motor_encoder_reset(){
        R_motor_reset();
        L_motor_reset();
    }


    int L_encoder_count(){
        int l_return = 0;

        if(motor_LF != null){
            l_return = motor_LF.getCurrentPosition ();
        }

        return l_return;
    }

    int R_encoder_count(){
        int l_return = 0;

        if(motor_RF != null){
            l_return = motor_RF.getCurrentPosition ();
        }
        return l_return;
    }

    boolean if_L_encoder_reached(double p_count){
        boolean l_return = false;

        if(motor_LF != null){
            // TODO Implement stall code using these variables.
            if(Math.abs (motor_LF.getCurrentPosition()) > p_count){
                l_return = true;
            }
        }
        return l_return;
    }

    boolean if_R_encoder_reached(double p_count){
        boolean l_return = false;

        if(motor_RF != null){
            // TODO Implement stall code using these variables.
            if(Math.abs (motor_RF.getCurrentPosition()) > p_count){
                l_return = true;
            }
        }
        return l_return;
    }

    public boolean if_encoders_reached(double p_left_count, double p_right_count){
        boolean l_return = false;

        if(if_L_encoder_reached(p_left_count) && if_R_encoder_reached(p_right_count)){
            l_return = true;
        }

        return l_return;

    }

    public boolean drive_using_encoders(double p_left_power, double p_right_power, double p_left_count, double p_right_count){
        boolean l_return = false;
        run_using_encoder();
        set_drive_power (p_left_power, p_right_power);

        if(if_encoders_reached(p_left_count, p_right_count)){
            motor_encoder_reset();
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

    public boolean if_encoders_reset(){
        boolean l_return = false;

        if(if_L_encoder_reset() && if_R_encoder_reset()){

            l_return = true;
        }

        return l_return;
    }
}
