package org.tullyfirst.FTC8863.lib;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class init_lib extends OpMode{

    public DcMotor motor_RF;
    public DcMotor motor_LF;

    public Servo R_arm;
    public Servo L_arm;
    public Servo S_arm1;
    public Servo S_arm2;
    public Servo C_arm;


    @Override
    public void init(){


        //motor init
        motor_RF = hardwareMap.dcMotor.get("motor_RF");
        motor_LF = hardwareMap.dcMotor.get("motor_LF");
        motor_RF.setDirection(com.qualcomm.robotcore.hardware.DcMotor.Direction.REVERSE);


        //servo init
        R_arm = hardwareMap.servo.get("R_arm");
        L_arm = hardwareMap.servo.get("L_arm");
        L_arm.setDirection(Servo.Direction.REVERSE);

        S_arm1 = hardwareMap.servo.get("S_arm1");
        S_arm2 = hardwareMap.servo.get("S_arm2");
        S_arm2.setDirection(Servo.Direction.REVERSE);

        C_arm = hardwareMap.servo.get("C_arm");

    }//init

    @Override
    public void loop(){

    }//loop

}
