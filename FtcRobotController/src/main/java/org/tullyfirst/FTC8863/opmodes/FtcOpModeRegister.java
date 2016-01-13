package org.tullyfirst.FTC8863.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegister;

import org.tullyfirst.FTC8863.opmodes.ResQ.TeleOP;
import org.tullyfirst.FTC8863.opmodes.test.IMU8863test;
import org.tullyfirst.FTC8863.opmodes.test.IMUtest;
import org.tullyfirst.FTC8863.opmodes.test.SweeperTest;
import org.tullyfirst.FTC8863.opmodes.test.TestLinearSlide;
import org.tullyfirst.FTC8863.opmodes.test.TestServo8863;
import org.tullyfirst.FTC8863.opmodes.test.TestTrapDoor;
import org.tullyfirst.FTC8863.opmodes.test.ToggleButtonTest;

/**
 * Register Op Modes
 */
public class FtcOpModeRegister implements OpModeRegister {
  public void register(OpModeManager manager) {
    // test routines
    manager.register("SweeperTest", SweeperTest.class);
    manager.register("IMUtest", IMUtest.class);
    manager.register("IMU8863test", IMU8863test.class);
    manager.register("TestLinearSlide", TestLinearSlide.class);
    manager.register("TestTrapDoor", TestTrapDoor.class);
    manager.register("TestServo8863", TestServo8863.class);

    /** ed young's */
    manager.register("TeleOP", TeleOP.class);
    manager.register("testing", ToggleButtonTest.class);
  }
}
