package org.tullyfirst.FTC8863.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class ToggleButtonTest extends OpMode {

    Servo testServo;
    double servoWiggleReturn;


    @Override
    public void init(){
        testServo = hardwareMap.servo.get("testServo");
    }

    @Override
    public void start(){
        testServo.setPosition(0);
    }

    @Override
    public void loop(){
        servoWiggle(gamepad1.y, 1, 0, .7, .5);
        testServo.setPosition(servoWiggleReturn);
    }

    ElapsedTime Time;

    public double servoWiggle(boolean Button, float timeDif,double Home, double pos1, double pos2){
        if(Button){
            servoWiggleReturn = pos1;
            if(Time.time() > timeDif){
                servoWiggleReturn = pos2;
                Time.reset();
            }
        }
        if(!Button){
            servoWiggleReturn = Home;
        }
        return servoWiggleReturn;
    }
}
