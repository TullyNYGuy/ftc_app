package org.tullyfirst.FTC8863.lib.ResQLib;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class init_lib extends OpMode{

    public DcMotor rightDriveMotor;
    public DcMotor leftDriveMotor;
    public DcMotor tapeMotor;
    public DcMotor sweeperMotor;

    public Servo rightZipServoArm;
    public Servo leftZipServoArm;
    public Servo bucketSlide360Servo;
    public Servo rampServo;
    public Servo trapdoorServo;
    public Servo aimingServo;
    public Servo climberServo;
    public Servo barGrabberServo;

    @Override
    public void init(){

        //motor init
        rightDriveMotor = hardwareMap.dcMotor.get("rightDriveMotor");
        leftDriveMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        rightDriveMotor.setDirection(DcMotor.Direction.REVERSE);
        tapeMotor = hardwareMap.dcMotor.get("tapeMotor");
        sweeperMotor = hardwareMap.dcMotor.get("sweeperMotor");

        //servo init
        rightZipServoArm = hardwareMap.servo.get("rightZipServoArm");
        leftZipServoArm = hardwareMap.servo.get("leftZipServoArm");
        leftZipServoArm.setDirection(Servo.Direction.REVERSE);
        bucketSlide360Servo = hardwareMap.servo.get("bucketSlide360Servo");
        rampServo = hardwareMap.servo.get("rampServo");
        trapdoorServo = hardwareMap.servo.get("trapdoorServo");
        aimingServo = hardwareMap.servo.get("aimingServo");
        climberServo = hardwareMap.servo.get("climberServo");
        barGrabberServo = hardwareMap.servo.get("barGrabberServo");

    }//init

    @Override
    public void loop(){

    }//loop

}
