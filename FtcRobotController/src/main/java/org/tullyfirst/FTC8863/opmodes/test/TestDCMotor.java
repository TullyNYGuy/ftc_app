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
//import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.tullyfirst.FTC8863.lib.DcMotor;

/**
 * TestDCMotor is meant to provide a test platform for the FTC8863 extension to the DcMotor class.
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
public class TestDCMotor extends OpMode {

	/*
	 * Note: the configuration of the servos is such that
	 * as the arm servo approaches 0, the arm position moves up (away from the floor).
	 * Also, as the claw servo approaches 0, the claw opens up (drops the game element).
	 */
	// TETRIX VALUES.
	final static double REPEL_MIN  = 0.0;
	final static double REPEL_MAX  = 1.0;
    final static double JOYSTICK_DEADBAND_VALUE = .15;

	// position of the arm servo.
	double leftRepelServoPosition;

    // The declaration below does not work since it refers to the FTC provided DcMotor class
    // and not my extension of the DcMotor class
	//com.qualcomm.robotcore.hardware.DcMotor motorRight;

    // This declaration refers to my extension of the DcMotor class
    org.tullyfirst.FTC8863.lib.DcMotor motorRight;
	Servo leftRepelServo;

	/**
	 * Constructor
	 */
	public TestDCMotor() {

	}

	/*
	 * Code to run when the op mode is first enabled goes here
	 * 
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
	 */
	@Override
	public void init() {


		/*
		 * Use the hardwareMap to get the dc motors and servos by name. Note
		 * that the names of the devices must match the names used when you
		 * configured your robot and created the configuration file.
		 */
		
		/*
		 * For our demo bot we assume the following,
		 *   There are two motors "leftDriveMotor" and "rightDriveMotor"
		 *   "rightDriveMotor" is on the right side of the bot and reversed.
		 *   "leftDriveMotor" is on the left side of the bot.
		 *   
		 * We also assume that there is  one servo "leftRepelServo"
		 *    "leftRepelServo" controls the arm to hit the repel triggers on the left side
		 */

        // The next line throws a compile time error:
        // error: incompatible types
        // required: org.tullyfirst.FTC8863.lib.DcMotor
        // found: com.qualcomm.robotcore.hardware.DcMotor
        // Shouldn't these be the same type? Mine inherits from hardware.DcMotor.
		motorRight = hardwareMap.dcMotor.get("rightDriveMotor");

		motorRight.setDirection(DcMotor.Direction.REVERSE);

        // initialize the motor
        motorRight.setMotorType(DcMotor.MotorType.ANDYMARK_40);
        motorRight.setCountsPerRevForMotorType(DcMotor.MotorType.ANDYMARK_40);
        motorRight.setCmPerRev(10);
        motorRight.setEncoderTolerance(10);
        motorRight.setMotorMoveType(DcMotor.MotorMoveType.RELATIVE);

		leftRepelServo = hardwareMap.servo.get("leftRepelServo");

		// assign the starting position of the repel servo
		leftRepelServoPosition = 0.5;
	}

    @Override
    public void start() {

        leftRepelServo.setPosition(leftRepelServoPosition);
    }

	@Override
	public void loop() {

		//
        motorRight.rotateToEncoderCount(.5, 1120, DcMotor.NextMotorState.COAST );

		/*
		 * Send telemetry data back to driver station. Note that if we are using
		 * a legacy NXT-compatible motor controller, then the getPower() method
		 * will return a null value. The legacy NXT-compatible motor controllers
		 * are currently write only.
		 */
        telemetry.addData("arm", "leftRepel:  " + String.format("%.2f", leftRepelServoPosition));
        telemetry.addData("Encoder",  "Encoder: " + String.format("%.2f", motorRight.getCurrentPosition()));
        //telemetry.addData("left Y scaled", "joy Y: " + String.format("%.2f", left));
        if(deadBandOn) {
            telemetry.addData("Deadband:", "ON");
        } else {
            telemetry.addData("Deadband:", "OFF");
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

    public boolean toggleDeadBandValue () {

        boolean deadBandOn = true;

        if(driverDiffLeftJoyStickX.getDeadBand() == 0) {
            // deadband compensation is currently disabled, enable it
            driverDiffLeftJoyStickX.setDeadBand(JOYSTICK_DEADBAND_VALUE);
            driverDiffLeftJoyStickY.setDeadBand(JOYSTICK_DEADBAND_VALUE);
            driverTankLeftJoyStickY.setDeadBand(JOYSTICK_DEADBAND_VALUE);
            driverTankRightJoyStickY.setDeadBand(JOYSTICK_DEADBAND_VALUE);
            deadBandOn = true;
        } else {
            // deadband compensation is currenly endableo, disable it
            driverDiffLeftJoyStickX.setDeadBand(0);
            driverDiffLeftJoyStickY.setDeadBand(0);
            driverTankLeftJoyStickY.setDeadBand(0);
            driverTankRightJoyStickY.setDeadBand(0);
            deadBandOn = false;
        }
        return deadBandOn;
    }
}
