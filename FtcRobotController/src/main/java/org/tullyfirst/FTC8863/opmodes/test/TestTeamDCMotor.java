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
// only need this import to get access to enums
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

import org.tullyfirst.FTC8863.lib.FTCLib.TeamDcMotor;


/**
 * TestTeamDCMotor is meant to provide a test platform for the FTC8863 extension to the DcMotor class.
 * <p>
 */
public class TestTeamDCMotor extends OpMode {

    // This declaration refers to my TeamDcMotor class
    TeamDcMotor motorRight;

	/**
	 * Constructor
	 */
	public TestTeamDCMotor() {
	}

	/*
	 * Code to run when the op mode is first enabled goes here
	 * 
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
	 */
	@Override
	public void init() {

        // Instantiate and initialize a motor
        motorRight = new TeamDcMotor("rightDriveMotor", hardwareMap);
        motorRight.setDirection(DcMotor.Direction.REVERSE);
        motorRight.setMotorType(TeamDcMotor.MotorType.ANDYMARK_40);
        motorRight.setUnitsPerRev(360);
        motorRight.setEncoderTolerance(5);
        motorRight.setMotorMoveType(TeamDcMotor.MotorMoveType.RELATIVE);
        motorRight.setMinMotorPower(-1);
        motorRight.setMaxMotorPower(1);

        // TeamDcMotor contains a DcMotor object: FTCDcMotor. That object give us access to the
        // motor.
/*		motorRight.FTCDcMotor.setDirection(DcMotor.Direction.REVERSE);
        motorRight.setMotorType(TeamDcMotor.MotorType.ANDYMARK_40);
        motorRight.setCountsPerRevForMotorType(TeamDcMotor.MotorType.ANDYMARK_40);
        motorRight.setCmPerRev(10);
        motorRight.setEncoderTolerance(10);
        motorRight.setMotorMoveType(TeamDcMotor.MotorMoveType.RELATIVE);*/

	}

    @Override
    public void start() {
    }

	@Override
	public void loop() {

		//
		double motorPower = .5;

		// rotate the motor one revolution and then coast
        //motorRight.rotateToEncoderCount(motorPower, motorRight.getCountsPerRev(), TeamDcMotor.NextMotorState.COAST);
        if (gamepad1.b) {

//            motorRight.setMode(DcMotorController.RunMode.RESET_ENCODERS);
//            motorRight.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
//            motorRight.setPower(motorPower);

            motorRight.rotateToDistance(motorPower, -360, TeamDcMotor.NextMotorState.HOLD);
        }
        if (gamepad1.a) {
            // if the A button is pushed on gamepad1, the motor stops
            //motorRight.setPowerFloat();
            //motorRight.setMode(DcMotorController.RunMode.RESET_ENCODERS);
            motorRight.resetEncoder(true);

        }


		/*
		 * Send telemetry data back to driver station. Note that if we are using
		 * a legacy NXT-compatible motor controller, then the getPower() method
		 * will return a null value. The legacy NXT-compatible motor controllers
		 * are currently write only.
		 */
        if (motorRight.isRotationComplete()){
            telemetry.addData("Status",  "rotation complete");
        } else {
            telemetry.addData("Status",  "still going");
        }
        telemetry.addData("Encoder",  "Encoder: " + String.format("%d", motorRight.getCurrentPosition()));
        //telemetry.addData("left Y scaled", "joy Y: " + String.format("%.2f", left));


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
