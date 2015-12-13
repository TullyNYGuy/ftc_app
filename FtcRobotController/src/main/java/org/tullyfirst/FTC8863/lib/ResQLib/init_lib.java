package org.tullyfirst.FTC8863.lib.ResQLib;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class init_lib extends OpMode{

    public DcMotor rightDriveMotor;
    public DcMotor leftDriveMotor;

    public Servo rightZipServoArm;
    public Servo leftZipServoArm;
    public Servo helperServoArm1;
    public Servo helperServoArm2;
    public Servo climberServoArm;

    public ResQRobot robot;

    public ElapsedTime elapsedTime;

    @Override
    public void init(){

        robot = new ResQRobot(hardwareMap);

        elapsedTime = new ElapsedTime();

        //motor init
        rightDriveMotor = hardwareMap.dcMotor.get("rightDriveMotor");
        leftDriveMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        rightDriveMotor.setDirection(com.qualcomm.robotcore.hardware.DcMotor.Direction.REVERSE);

        //servo init
        rightZipServoArm = hardwareMap.servo.get("rightZipServoArm");
        leftZipServoArm = hardwareMap.servo.get("leftZipServoArm");
        leftZipServoArm.setDirection(Servo.Direction.REVERSE);

        helperServoArm1 = hardwareMap.servo.get("helperServoArm1");
        helperServoArm2 = hardwareMap.servo.get("helperServoArm2");
        helperServoArm2.setDirection(Servo.Direction.REVERSE);

        climberServoArm = hardwareMap.servo.get("climberServoArm");

    }//init

    @Override
    public void loop(){

    }//loop

}
