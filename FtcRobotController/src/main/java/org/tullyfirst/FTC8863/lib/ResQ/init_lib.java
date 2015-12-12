package org.tullyfirst.FTC8863.lib.ResQ;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class init_lib extends OpMode{

    public DcMotor rightDriveMotor;
    public DcMotor leftDriveMotor;

    public Servo rightZipServoArm;
    public Servo leftZipServoArm;
    public Servo helperServoArm1;
    public Servo helperServoArm2;
    public Servo climberServoArm;


    @Override
    public void init(){


        //motor init
        rightDriveMotor = hardwareMap.dcMotor.get("motor_RF");
        leftDriveMotor = hardwareMap.dcMotor.get("motor_LF");
        rightDriveMotor.setDirection(com.qualcomm.robotcore.hardware.DcMotor.Direction.REVERSE);


        //servo init
        rightZipServoArm = hardwareMap.servo.get("R_arm");
        leftZipServoArm = hardwareMap.servo.get("L_arm");
        leftZipServoArm.setDirection(Servo.Direction.REVERSE);

        helperServoArm1 = hardwareMap.servo.get("S_arm1");
        helperServoArm2 = hardwareMap.servo.get("S_arm2");
        helperServoArm2.setDirection(Servo.Direction.REVERSE);

        climberServoArm = hardwareMap.servo.get("C_arm");

    }//init

    @Override
    public void loop(){

    }//loop

}
