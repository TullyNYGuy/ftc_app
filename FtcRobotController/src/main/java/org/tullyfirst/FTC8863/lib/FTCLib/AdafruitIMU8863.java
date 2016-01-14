package org.tullyfirst.FTC8863.lib.FTCLib;

import android.util.Log;

import com.qualcomm.robotcore.exception.RobotCoreException;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Extends the AdafruitIMU class created by Alan Gilkes.
 * Extensions simplify user interface and provide a factory method for creating an instance of
 * the IMU
 * Created by ball on 12/30/2015.
 */
public class AdafruitIMU8863 extends AdafruitIMU{
    /**
     * Yaw angle from IMU in Euler form. See https://en.wikipedia.org/wiki/Euler_angles
     */
    private double yawAngleEuler;

    /**
     * Yaw angle from IMU in Tait-Bryan form. See https://en.wikipedia.org/wiki/Euler_angles
     */
    private double yawAngleTaitBryan;

    /**
     * Pitch angle from IMU in Euler form. See https://en.wikipedia.org/wiki/Euler_angles
     */
    private double pitchAngleEuler;

    /**
     * Pitch angle from IMU in Tait-Bryan form. See https://en.wikipedia.org/wiki/Euler_angles
     */
    private double pitchAngleTaitBryan;

    /**
     * Roll angle from IMU in Euler form. See https://en.wikipedia.org/wiki/Euler_angles
     */
    private double rollAngleEuler;

    /**
     * Roll angle from IMU in Tait-Bryan form. See https://en.wikipedia.org/wiki/Euler_angles
     */
    private double rollAngleTaitBryan;

    //The following arrays contain both the Euler angles reported by the IMU (indices = 0) AND the
    // Tait-Bryan angles calculated from the 4 components of the quaternion vector (indices = 1)

    /**
     * Roll angle in array 0=Euler, 1=Tait-Bryan
     */
    volatile double[] rollAngle = new double[2];

    /**
     * Pitch angle in array 0=Euler, 1=Tait-Bryan
     */
    volatile double[] pitchAngle = new double[2];

    /**
     * Yaw angle in array 0=Euler, 1=Tait-Bryan
     */
    volatile double[] yawAngle = new double[2];

    public double getRollAngleTaitBryan() {
        return rollAngleTaitBryan;
    }

    public double getRollAngleEuler() {
        return rollAngleEuler;
    }

    public double getPitchAngleTaitBryan() {
        return pitchAngleTaitBryan;
    }

    public double getPitchAngleEuler() {
        return pitchAngleEuler;
    }

    public double getYawAngleTaitBryan() {
        return yawAngleTaitBryan;
    }

    public double getYawAngleEuler() {
        return yawAngleEuler;
    }

    /**
     * Constructor - do not use this directly. Use create() instead.
     * @param currentHWmap Hardware map
     * @param configuredIMUname Name of the IMU from the configuration on the phone
     * @param baseAddress I2C address for the IMU
     * @param operMode Operation mode of IMU
     * @throws RobotCoreException
     */
    public AdafruitIMU8863 (HardwareMap currentHWmap, String configuredIMUname,  byte baseAddress, byte operMode) throws RobotCoreException {
        super (currentHWmap, configuredIMUname, baseAddress, operMode);
        //ADDRESS_B is the "standard" I2C bus address for the Bosch BNO055 (IMU data sheet, p. 90).
        //BUT DAVID PIERCE, MENTOR OF TEAM 8886, HAS EXAMINED THE SCHEMATIC FOR THE ADAFRUIT BOARD ON
        //WHICH THE IMU CHIP IS MOUNTED. SINCE THE SCHEMATIC SHOWS THAT THE COM3 PIN IS PULLED LOW,
        //ADDRESS_A IS THE IMU'S OPERATIVE I2C BUS ADDRESS
        //IMU is an appropriate operational mode for FTC competitions. (See the IMU datasheet, Table
        // 3-3, p.20 and Table 3-5, p.21.
    }

    /**
     * Factory method to return an instance of the IMU. Use this instead of the constructor.
     * @param currentHWmap Hardware map
     * @param configuredIMUname Name of the IMU from the configuration on the phone
     * @return IMU instance
     */
    public static AdafruitIMU8863 create(HardwareMap currentHWmap, String configuredIMUname){
        AdafruitIMU8863 instance;
        long systemTime;

        systemTime = System.nanoTime();
        try {
            instance = new AdafruitIMU8863(currentHWmap, configuredIMUname,
                    (byte)(AdafruitIMU.BNO055_ADDRESS_A * 2),//By convention the FTC SDK always does 8-bit I2C bus addressing
                    (byte)AdafruitIMU.OPERATION_MODE_IMU);
        } catch (RobotCoreException e){
            Log.i("FtcRobotController", "Exception: " + e.getMessage());
            instance = null;
        }
        Log.i("FtcRobotController", "IMU Init method finished in: "
                + (-(systemTime - (systemTime = System.nanoTime()))) + " ns.");
        return instance;
    }

    /**
     * Update the angles from the IMU. Call this method each time through the loop() in an opmode
     */
    public void update() {
        super.getIMUGyroAngles(rollAngle, pitchAngle, yawAngle);
        rollAngleEuler = rollAngle [0];
        rollAngleTaitBryan = rollAngle [1];
        pitchAngleEuler = pitchAngle [0];
        pitchAngleTaitBryan = pitchAngle [1];
        yawAngleEuler = yawAngle [0];
        yawAngleTaitBryan = yawAngle [1];
    }

    /**
     * Start the IMU. Record the time required to do that.
     */
    @Override
    public void startIMU() {
        long systemTime;
        systemTime = System.nanoTime();
        super.startIMU();//Set up the IMU as needed for a continual stream of I2C reads.
        Log.i("FtcRobotController", "IMU Start method finished in: "
                + (-(systemTime - (systemTime = System.nanoTime()))) + " ns.");
    }
}
