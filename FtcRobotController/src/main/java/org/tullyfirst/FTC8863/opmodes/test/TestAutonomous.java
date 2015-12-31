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
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * TeleOp Mode
 * <p>
 * Enables control of the robot via the gamepad
 */
/*public class TestAutonomous extends OpMode {
	*//**
	 * Constructor
	 *//*
	public TestAutonomous() {

	}

	public enum AutoState {
		READY, MOVE300, TURN45, MOVE1000, STOP
	}

	public AutoState currentState = AutoState.READY;

    public ElapsedTime stateTimer;


	*//*
	 * Code to run when the op mode is first enabled goes here
	 * 
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
	 *//*
	@Override
	public void init() {

	}

    public void start() {

    }

	*//*
	 * This method will be called repeatedly in a loop
	 * 
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#run()
	 *//*
	@Override
	public void loop() {

        switch (currentState) {
			case READY:
				//if we get a start then we will go to move300 state
				//else we stay in this state
				if (start) {
					currentState = AutoState.MOVE300;
					driveTrain.moveDistance(FORWARD, 300, 70);
				}
				break;

			case MOVE300:
				//if the 300cm movement is finished then go to turn 45
				//else stay in this state
				if (driveTrain.update()== driveTrain.Completed) {
					currentState = AutoState.TURN45;
					driveTrain.spinTurn(RIGHT, 45, 60);
				}
				break;

			case TURN45:
                //if the turn is complete then go forward 1000cm
				//else stay in this state
				if (driveTrain.update()== driveTrain.Completed) {
					currentState = AutoState.MOVE1000;
					driveTrain.moveDistance(FORWARD, 1000, 70);
				}
				break;

			case MOVE1000:
                //if the movement is finished then stop
				//else stay in this state
				if (driveTrain.update()== driveTrain.Completed) {
					currentState = AutoState.STOP;
					driveTrain.stop();
				}
				break;

			case STOP:
				//stop motors
				break;

		}



		*//*
		 * Send telemetry data back to driver station. Note that if we are using
		 * a legacy NXT-compatible motor controller, then the getPower() method
		 * will return a null value. The legacy NXT-compatible motor controllers
		 * are currently write only.
		 *//*
        telemetry.addData("Current", "State: " + currentState.toString());
		telemetry.addData("right", "Encoder: " + String.format("%1$d", motorSweeper.getCurrentPosition()));
        //telemetry.addData("left", "Encoder: " + String.format("%1$d", motorLeft.getCurrentPosition()));

	}

	*//*
	 * Code to run when the op mode is first disabled goes here
	 * 
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#stop()
	 *//*
	@Override
	public void stop() {

	}

	}

}*/
