package org.tullyfirst.FTC8863.lib.ResQLib;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.tullyfirst.FTC8863.lib.FTCLib.TeamDcMotor;

/**
 * Created by ball on 12/12/2015.
 */
public class ResQRobot {

//*********************************************************************************************
//           DATA FIELDS
//*********************************************************************************************

    //public TeamDcMotor popperMotor;

//*********************************************************************************************
//           CONSTRUCTOR
//*********************************************************************************************

    ResQRobot(HardwareMap hardwareMap) {
       // popperMotor = new TeamDcMotor("popperMotor", hardwareMap);
        //initPopperMotor();
    }


//*********************************************************************************************
//           METHODS
//*********************************************************************************************

/*    public void initPopperMotor() {
        popperMotor.setMotorType(TeamDcMotor.MotorType.ANDYMARK_40);
        popperMotor.setUnitsPerRev(360);
        popperMotor.setEncoderTolerance(5);
        popperMotor.setMotorMoveType(TeamDcMotor.MotorMoveType.RELATIVE);
        popperMotor.setMinMotorPower(-1);
        popperMotor.setMaxMotorPower(1);
        popperMotor.setDirection(DcMotor.Direction.REVERSE);

    }

    public void setPopperMotorHome() {
        //popperMotor.resetEncoder(true);
        popperMotor.rotateToDistance(.5, 0,TeamDcMotor.NextMotorState.HOLD );
    }

    public void goPopperMotorHome() {
        popperMotor.resetEncoder(true);
        popperMotor.rotateToDistance(.95, -80, TeamDcMotor.NextMotorState.HOLD);
    }

    public void goPopperMotorPop() {
        popperMotor.resetEncoder(true);
        popperMotor.rotateToDistance(.95, 80, TeamDcMotor.NextMotorState.HOLD);
    }*/

//*********************************************************************************************
//           NESTED CLASSES
//*********************************************************************************************

/*    public class ObjectNameMapping {


        /*//*********************************************************************************************
        //           DATA FIELDS
        /*//*********************************************************************************************
        protected String popperMotorName = "popperMotor";

        /*//*********************************************************************************************
        //           GETTERS AND SETTERS
        /*//*********************************************************************************************
        public String getPopperMotorName() {
            return popperMotorName;
        }
        /*//*********************************************************************************************
        //           CONSTRUCTOR
        /*//*********************************************************************************************

        ObjectNameMapping() {
        }

        /*//*********************************************************************************************
        //           METHODS
        /*//*********************************************************************************************

    }*/
}

