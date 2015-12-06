/* Copyright (c) 2014 Qualcomm Technologies Inc

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Qualcomm Technologies Inc nor the names of its contributors
may be used to endorse or promote products derived from this software without
specific prior written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. */

package org.tullyfirst.FTC8863.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import org.tullyfirst.FTC8863.lib.TeamServo;

/**
 * TestJoyStick is meant to provide the driver wtih a way to test the various joystick control
 * methods.
 * Differential drive - one joystick for both speed and direction - use gamepad2 left joystick
 * Tank drive - two joysticks, one for left motor speed and one for right - use gamepad2 left
 *  and right joystick
 * Turn deadband compensation on or off (toggle) - gamepad2 left bumper
 *
 * It also allows the driver to control a repel servo
 *  gamepad2 a button = down
 *  gamepad2 y button = up
 *  gamepad2 x button = .5 (halfway)
 * <p>
 */
public class TestTeamServo extends OpMode {
    boolean leftRepelServoActive = true;
    double upPosition = .8;
    double downPosition = .2;
    double homePosition = .8;
    double lowerRepelPosition = .25;
    double middleRepelPosition = .1;
    double upperRepelPosition = .1;
    TeamServo leftRepelServo;
    TeamServo rightRepelServo;
	/**
	 * Constructor
	 */
	public TestTeamServo() {

	}

	/*
	 * Code to run when the op mode is first enabled goes here
	 * 
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
	 */
	@Override
	public void init() {
        leftRepelServo = new TeamServo("leftRepelServo", hardwareMap,homePosition, upPosition, downPosition, Servo.Direction.REVERSE);
        rightRepelServo =new TeamServo("rightRepelServo",hardwareMap,homePosition, upPosition, downPosition, Servo.Direction.FORWARD);
        leftRepelServo.setPositionOne(lowerRepelPosition);
        leftRepelServo.setPositionTwo(middleRepelPosition);
        leftRepelServo.setPositionThree(upperRepelPosition);
        rightRepelServo.setPositionOne(lowerRepelPosition);
        rightRepelServo.setPositionTwo(middleRepelPosition);
        rightRepelServo.setPositionThree(upperRepelPosition);
	}

    @Override
    public void start() {
        leftRepelServo.goHome();
        rightRepelServo.goHome();
    }

	@Override
	public void loop() {

        if (gamepad2.left_bumper) {
            leftRepelServoActive = true;
        }

        if (gamepad2.right_bumper) {
            leftRepelServoActive = false;
        }

        if (gamepad2.a) {
            // if the A button is pushed on gamepad2, increment the position of
            // the arm servo.
            if (leftRepelServoActive) {
                leftRepelServo.goPositionOne();
                rightRepelServo.goHome();
                telemetry.addData("leftRepel", "is lower");
                telemetry.addData("rightRepel", "home");
            } else {
                rightRepelServo.goPositionOne();
                leftRepelServo.goHome();
                telemetry.addData("rightRepel", "is lower");
                telemetry.addData("leftRepel", "home");
            }
        }

        if (gamepad2.b) {
            // if the B button is pushed on gamepad2, increment the position of
            // the arm servo.
            leftRepelServo.goHome();
            rightRepelServo.goHome();
            telemetry.addData("leftRepel", "home");
            telemetry.addData("rightRepel", "home");
        }

        if (gamepad2.x) {
            // if the X button is pushed on gamepad2, decrease the position of
            // the arm servo.
            if (leftRepelServoActive) {
                leftRepelServo.goPositionTwo();
                rightRepelServo.goHome();
                telemetry.addData("leftRepel", "is middle");
                telemetry.addData("rightRepel", "home");
            } else {
                rightRepelServo.goPositionTwo();
                leftRepelServo.goHome();
                telemetry.addData("rightRepel", "is middle");
                telemetry.addData("leftRepel", "home");
            }
        }

        if (gamepad2.y) {
            // if the Y button is pushed on gamepad2, decrease the position of
            // the arm servo.
            if (leftRepelServoActive) {
                leftRepelServo.goPositionThree();
                rightRepelServo.goHome();
                telemetry.addData("leftRepel", "is upper");
                telemetry.addData("rightRepel", "home");
            } else {
                rightRepelServo.goPositionThree();
                leftRepelServo.goHome();
                telemetry.addData("rightRepel", "is upper");
                telemetry.addData("leftRepel", "home");
            }
        }
	}

	/*
	 * Code to run when the op mode is first disabled goes here
	 * 
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#stop()
	 */
	@Override
	public void stop() {
        leftRepelServo.goHome();
        rightRepelServo.goHome();
    }
}