package org.tullyfirst.FTC8863.opmodes.ResQ;

import org.tullyfirst.FTC8863.lib.ResQLib.Auto_8863_hardware;


public class Auto_8863 extends Auto_8863_hardware{

    @Override
    public void start(){
        super.start();
        motorEncoderReset();
    }//start

    @Override
    public void loop(){
        switch(step) {
            case 0:
                t_message = "case 0";

                motorEncoderReset();
                step++;
                break;

            case 1:
                t_message = "case 1";

                driveUsingEncoders(1, 1, 1120, 1120);
                if (driveUsingEncoders(1, 1, 1120, 1120)) {
                    step++;
                }
                break;

            case 2:
                t_message = "case 2";

                if (ifEncodersReset()) {
                    step++;
                }
                break;

            case 3:
                t_message = "case 3";

                rightZipServoArm.setPosition(s_position(.25));
                leftZipServoArm.setPosition(s_position(.25));
                step++;
                break;

            case 4:
                t_message = "case 4";

                driveUsingEncoders(1,-1,2000,-2000);
                if(driveUsingEncoders(1,-1,2000,-2000)){
                    step++;
                }
                break;

            case 5:
                t_message = "case 5";
                if(ifEncodersReset()){
                    step++;
                }
                break;

            case 6:
                t_message = "case 6";
                leftZipServoArm.setPosition(s_position(.8));
                rightZipServoArm.setPosition(s_position(.75));
                break;

            /*case 7:
                break;

            case 8:
                break;*/


            default:

                break;
        }
        telemetry.addData("01", t_message);
    }

    @Override
    public void stop(){
        super.stop();
    }//stop

    private int step = 0;
}
