package com.edward.app.Serial;

/**
 * Created by edwardsalcido on 4/26/16.
 */
public class Request {
    String varName;
    byte[] request;

    public Request(String varName,int b1, int b2, int b3, int b4){
        this.varName = varName;
        request[0]= (byte)b1;
        request[1]= (byte)b2;
        request[2]= (byte)b3;
        request[3]= (byte)b4;

    }

    public Request(byte [] r){
        request = r;
    }

    public byte[] getRequest() {
        return request;
    }

    public void setRequest(byte[] request) {
        this.request = request;
    }


}
