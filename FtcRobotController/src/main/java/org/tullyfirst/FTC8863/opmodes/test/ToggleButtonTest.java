package org.tullyfirst.FTC8863.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class ToggleButtonTest extends OpMode {

    Servo testServo;
    int step;
    double servoReturn;
    String servoMessage;

    @Override
    public void init(){
        testServo = hardwareMap.servo.get("testServo");
        testServo.setPosition(.5);
    }

    @Override
    public void loop(){
        servoButtonToggle2Stage(gamepad1.a, testServo.getPosition(), 1, 0, .5);
        testServo.setPosition(servoReturn);
    }

    public double servoButtonToggle2Stage(boolean Button, double servoPosition, double Stage1, double Stage2, double Home){
        boolean pressed = false;
        if(Button && !pressed){
            pressed = true;
            step++;
        }
        if(!Button && pressed){
            pressed = false;
        }
        switch(step){
            case 1:
                servoReturn = Stage1;
                servoMessage = "Stage1";
                break;
            case 2:
                servoReturn = Stage2;
                servoMessage = "Stage2";
                break;
            case 3:
                servoReturn = Home;
                servoMessage = "Home";
                break;
            case 4:
                step = 1;
                break;
            default:
                break;

        }
        telemetry.addData("01", "pos: " + servoMessage);
        return servoReturn;
    }
}
