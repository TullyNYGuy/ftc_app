package org.tullyfirst.FTC8863.lib;

import com.qualcomm.robotcore.util.Range;

/**
 * Created by ball on 11/25/2015.
 */
public class JoyStick {
    final static double JOYSTICK_MIN = -1;
    final static double JOYSTICK_MAX = 1;
    final static double OUTPUT_MIN = 0;
    final static double OUTPUT_MAX = 1;

    // The joystick input will be a value from its min to its max.
    // In order to scale it to a motor power we need to scale it so that the joystick max
    // maps to the maximum motor power. For example if the max joystick is +127 and the max
    // motor power is +100, then we need to scale the joystick 127 down to 100:
    // power = joystick * (100/127).
    //
    // Sometimes the driver may want to cut the joystick command down. For example, they may
    // want to make it so full joystick only gives them 1/2 the max power. For that we can give
    // them a reductionFactor.

    // This function scales the joystick and applies any reduction factor. The output is a linear
    // function (a straight line if you graph output vs input. This may not be the best since
    // the driver will have less control at the lower joystick inputs.

    public static double scaleInputLinear(double joyStickValue, double reductionFactor) {
        // clip the joystick input to its min and max favlues
        joyStickValue = Range.clip(joyStickValue,JOYSTICK_MIN,JOYSTICK_MAX);
        // scale the joystick so that its max value maps to the max value of the motor power and
        // then apply the reduction factor
        joyStickValue = joyStickValue * OUTPUT_MAX/JOYSTICK_MAX * reductionFactor;
        // limit the output
        joyStickValue = Range.clip(joyStickValue,OUTPUT_MIN,OUTPUT_MAX);
        return joyStickValue;
    }

    // another version without the reduction factor (ie reduction factor = 1, full power).

    public static double scaleInputLinear(double joyStickValue) {
        // in this case the reduction factor = 1
        return scaleInputLinear(joyStickValue, 1);
    }

    // Use a square function to give more control at lower joystick values. Higher joystick values
    // have an output that is close to max.
    // Can remove deadband in the robot. This is when you give a joystick input but the robot
    // does not respond. The deadband is due to things like friction that has to be overcome before
    // movement can start. The deadband value has to be determined by experimenting with the robot.
    // The reductionFactor allows the driver to reduce the max output by a factor, like 1/2 power.
    // see https://ftc-team-3486.wikispaces.com/Joysticks for more details.
    public static double scaleInputSquared(double joyStickValue, double reductionFactor, double deadBandInPercent){
        double outputValue = 0;
        double maxSquaredTerm = 0;
        double deadBandTerm = 0;

        // in order to keep the square function from giving positive values for negative input
        // we need to make the output negative if the input is negative. And keep the output postive
        // if the input is positive. x / abs(x) will perform that trick for us.
        double signControl = joyStickValue / Math.abs(joyStickValue);

        // clip the joystick input to its min and max favlues
        joyStickValue = Range.clip(joyStickValue,JOYSTICK_MIN,JOYSTICK_MAX);

        // square the input and then adjust its sign
        // This results in a factor between -1 and 1.
        outputValue = Math.pow((joyStickValue / JOYSTICK_MAX), 2) * signControl;

        // The output will be made up of 2 terms (portions):
        // 1 - the squared portion
        // 2 - the deadBand portion (shifting the curve up)
        // The total of the 2 portions cannot add up to more than the max output.
        // So if the deadband is .2, and the max ouptut is 1, then the max
        // contribution that the squared part can be is .8.
        // 0.8 + 0.2 = 1

        // If you have taken Alegbra II, this is a translation of a squared function in the y
        // direction.

        // Figure out the deadband portion of the output.
        // This will get added to the squared term to shift the curve up.
        deadBandTerm = OUTPUT_MAX * deadBandInPercent / 100;

        // Figure out the max portion the squared term can be:
        maxSquaredTerm = OUTPUT_MAX - deadBandTerm;

        // Calculate the squared term and add the deadband term with its sign adjusted
        outputValue = outputValue * maxSquaredTerm + deadBandTerm * signControl;

        // Finally apply any reduction factor
        outputValue = outputValue * reductionFactor;

        // range clip the output - this should not be needed but just in case
        outputValue = Range.clip(outputValue,OUTPUT_MIN,OUTPUT_MAX);

        return outputValue;
    }

    public static double scaleInputSquared(double joyStickValue, double reductionFactor){
        // deadband percent = 0
        return scaleInputSquared(joyStickValue, 0, reductionFactor);
    }

    public static double scaleInputSquared(double joyStickValue){
        // deadband percent = 0
        // reduction factor = 1 (no reduction in power)
        return scaleInputSquared(joyStickValue, 0, 1);
    }

}
