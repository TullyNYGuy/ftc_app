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
import com.qualcomm.robotcore.util.Range;
import org.tullyfirst.FTC8863.lib.TeamServo;

/**
 * TestJoyStick is meant to provide the driver wtih a way to test the various joystick control
 * methods.
 * Differential drive - one joystick for both speed and direction - use gamepad1 left joystick
 * Tank drive - two joysticks, one for left motor speed and one for right - use gamepad2 left
 *  and right joystick
 * Turn deadband compensation on or off (toggle) - gamepad1 left bumper
 *
 * It also allows the driver to control a repel servo
 *  gamepad1 a button = down
 *  gamepad1 y button = up
 *  gamepad1 x button = .5 (halfway)
 * <p>
 */
public class TestTeamServo extends OpMode {
    double upPosition = .8;
    double downPosition = .2;
    double homePosition = .5;
    TeamServo leftRepelServo;
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
        leftRepelServo = new TeamServo("leftRepelServo", hardwareMap,homePosition, upPosition, downPosition);
	}

    @Override
    public void start() {
        leftRepelServo.getHomePosition();
    }

	@Override
	public void loop() {
        if (gamepad1.a) {
            // if the A button is pushed on gamepad1, increment the position of
            // the arm servo.
            leftRepelServo.goDown();
            telemetry.addData("leftRepel", "down");
        }

        if (gamepad1.y) {
            // if the Y button is pushed on gamepad1, decrease the position of
            // the arm servo.
            leftRepelServo.goUp();
            telemetry.addData("leftRepel", "up");
        }

        // update the position of the left repel servo.
        if (gamepad1.x) {
            // if the A button is pushed on gamepad1, increment the position of
            // the arm servo.
            leftRepelServo.goHome();
            telemetry.addData("leftRepel", "home");
        }
	}

	/*
	 * Code to run when the op mode is first disabled goes here
	 * 
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#stop()
	 */
	@Override
	public void stop() {

    }
}
