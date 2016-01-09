package org.tullyfirst.FTC8863.lib.ResQLib;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class init_lib extends OpMode{

    public DcMotor rightDriveMotor;
    public DcMotor leftDriveMotor;
    public DcMotor tapeMotor;

    //public Servo rightZipServoArm;
    //public Servo leftZipServoArm;

    @Override
    public void init(){

        //motor init
        rightDriveMotor = hardwareMap.dcMotor.get("rightDriveMotor");
        leftDriveMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        rightDriveMotor.setDirection(com.qualcomm.robotcore.hardware.DcMotor.Direction.REVERSE);

        tapeMotor = hardwareMap.dcMotor.get("tapeMotor");

        //servo init
        //rightZipServoArm = hardwareMap.servo.get("rightZipServoArm");
        //leftZipServoArm = hardwareMap.servo.get("leftZipServoArm");
        //leftZipServoArm.setDirection(Servo.Direction.REVERSE);

    }//init

    @Override
    public void loop(){

    }//loop

}
