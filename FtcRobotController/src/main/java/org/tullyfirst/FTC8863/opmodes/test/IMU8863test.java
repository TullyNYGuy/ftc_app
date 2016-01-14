package org.tullyfirst.FTC8863.opmodes.test;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.exception.RobotCoreException;

import org.tullyfirst.FTC8863.lib.FTCLib.AdafruitIMU;
import org.tullyfirst.FTC8863.lib.FTCLib.AdafruitIMU8863;

/**
 * Created by Owner on 8/31/2015.
 */
public class IMU8863test extends OpMode {

  AdafruitIMU8863 IMU;

  @Override
  public void init() {
    IMU = AdafruitIMU8863.create(hardwareMap, "IMU");
  }

  /************************************************************************************************
   * Code to run when the op mode is first enabled goes here
   * @see OpMode#start()
   */
  @Override
  public void start() {
    IMU.startIMU();
  }

  /***********************************************************************************************
   * This method will be called repeatedly in a loop
   * @see OpMode#loop()
   * NOTE: BECAUSE THIS "loop" METHOD IS PART OF THE OVERALL OpMode/EventLoop/ReadWriteRunnable
   * MECHANISM, ALL THAT THIS METHOD WILL BE USED FOR, IN AUTONOMOUS MODE, IS TO:
   * 1. READ SENSORS AND ENCODERS AND STORE THEIR VALUES IN SHARED VARIABLES
   * 2. WRITE MOTOR POWER AND CONTROL VALUES STORED IN SHARED VARIABLES BY "WORKER" THREADS, AND
   * 3. SEND TELELMETRY DATA TO THE DRIVER STATION
   * THIS "loop" METHOD IS THE ONLY ONE THAT "TOUCHES" ANY SENSOR OR MOTOR HARDWARE.
   */
  @Override
  public void loop() {

    IMU.update();

      //telemetry.addData("Text", "*** Robot Data***");
      telemetry.addData("Headings(yaw): ",
              String.format("Euler= %4.5f, Quaternion calculated= %4.5f", IMU.getYawAngleEuler(), IMU.getYawAngleTaitBryan()));
      telemetry.addData("Pitches: ",
              String.format("Euler= %4.5f, Quaternion calculated= %4.5f",IMU.getPitchAngleEuler(), IMU.getPitchAngleTaitBryan()));
      telemetry.addData("Rolls: ",
              String.format("Euler= %4.5f, Quaternion calculated= %4.5f",IMU.getRollAngleEuler(), IMU.getRollAngleTaitBryan()));
      telemetry.addData("Max I2C read interval: ",
              String.format("%4.4f ms. Average interval: %4.4f ms.", IMU.maxReadInterval, IMU.avgReadInterval));
  }

  /*
  * Code to run when the op mode is first disabled goes here
  * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#stop()
  */
  @Override
  public void stop() {
  }
}
