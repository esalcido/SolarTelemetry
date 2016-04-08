package com.edward.app.Battery;

/**
 * Created by edwardsalcido on 4/5/16.
 */
public class Battery {

    public Battery(){

    }

    public static String batteryStage(int register_data){
        String st = "";

        if(register_data == 0){
            st = "Resting";
        }
        else if(register_data == 3){
            st = "Absorb";
        }
        else if(register_data == 4){
            st = "BulkMPPT";
        }
        else if(register_data == 5){
            st = "Float";
        }
        else if(register_data == 6){
            st = "FloatMPPT";
        }

        return st;
    }

}
