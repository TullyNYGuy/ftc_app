package org.tullyfirst.FTC8863.opmodes;

import org.tullyfirst.FTC8863.lib.ResQ.Auto_8863_hardware;


public class Auto_8863 extends Auto_8863_hardware{

    @Override
    public void start(){
        super.start();
        motor_encoder_reset();
    }//start

    @Override
    public void loop(){
        switch(step){
            case 0:
                motor_encoder_reset();
                step++;
                t_message = "case 0";
                break;

            case 1:
                drive_using_encoders(1,1,1120,1120);
                if(drive_using_encoders(1,1,1120,1120)){
                    step++;
                    t_message = "case 1";
                }
                break;

            case 2:
                if(if_encoders_reset()){
                    step++;
                    t_message = "case 2";
                }
                break;

            case 3:
                rightZipServoArm.setPosition(s_position(.25));
                leftZipServoArm.setPosition(s_position(.25));
                step++;
                t_message = "case 3";
                break;

            /*case 4:
                drive_using_encoders(1,-1,2000,-2000);
                if(drive_using_encoders(1,-1,2000,-2000)){
                    step++;
                    t_message = "case 4";
                }
                break;

            case 5:
                if(if_encoders_reset()){
                    step++;
                }
                break;

            case 6:
                leftZipServoArm.setPosition(s_position(.85));
                rightZipServoArm.setPosition(s_position(.75));
                break;

            /*case 7:
                step++;
                break;

            case 8:
                if(if_encoders_reset()){
                    step++;
                }
                break;*/


            default:

                break;
        }
    }

    @Override
    public void stop(){

    }//stop

    private int step = 0;
}
