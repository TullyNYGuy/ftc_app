package org.tullyfirst.FTC8863.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class ToggleButtonTest extends OpMode {

    Servo testServo;
    double servoReturn;
    String servoMessage;
    boolean pressed = false;


    @Override
    public void init(){
        testServo = hardwareMap.servo.get("testServo");
        testServo.setPosition(0);
    }

    @Override
    public void loop(){

        if(gamepad1.y){
            if(testServo.getPosition() < .55){
                testServo.setPosition(.75);
            }
            if(testServo.getPosition() > .65){
                testServo.setPosition(.5);
            }
        }

        servoButtonToggle2Stage(gamepad1.a, testServo.getPosition(), .75, 0, .5);
        testServo.setPosition(servoReturn);
        telemetry.addData("02", "servo position: " + testServo.getPosition());
    }

    public double servoButtonToggle2Stage(boolean Button, double servoPosition, double Stage1, double Stage2, double Home){
        if(Button && !pressed){
            pressed = true;
            if(servoPosition == Home){
                servoReturn = Stage1;
                servoMessage = "Stage1";
            }
            if(servoPosition == Stage1){
                servoReturn = Stage2;
                servoMessage = "Stage2";
            }
            if(servoPosition == Stage2){
                servoReturn = Home;
                servoMessage = "Home";
            }
        }
        if(!Button && pressed){
            pressed = false;
        }
        telemetry.addData("01", "pos: " + servoMessage);
        return servoReturn;
    }
}
