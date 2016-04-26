package com.edward.app.Serial;

import gnu.io.NRSerialPort;

import java.io.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by edwardsalcido on 4/9/16.
 */
public class SerialTest1callEfficientLoop {

    public static void main(String [] args) {

        long start = System.currentTimeMillis();
        String port = "/dev/ttyUSB0";
        int baudRate = 57600;

        NRSerialPort serial = new NRSerialPort(port, baudRate);

        File file = new File("/home/edwardsalcido/Desktop/test.txt");

        serial.connect();

        DataInputStream ins = new DataInputStream(serial.getInputStream());
        DataOutputStream outs = new DataOutputStream(serial.getOutputStream());

        FileOutputStream fos ;
        FileWriter fw;

        String b2hex="";

        try
        {
            fw=new FileWriter(file.getAbsoluteFile(),true);

            Timestamp ts1=null;
            //get timestamp
            Date date1 = new Date();
            long time1 = date1.getTime();
            ts1 = new Timestamp(time1);

            String varName1 = "vPV";
            byte [] vPv = new byte[4];
            byte [] vBatt = new byte[4];
            byte [] request = new byte[4];
            byte [] response = new byte[4];

            //==============================================================
            vPv = new byte[]{(byte)0b11001001,0,0,0};//vPv
            vBatt = new byte[]{(byte)0b11001010,0,0,3};

            Request req = new Request("vPv",0b11001001,0,0,0);
            Request reqVbatt = new Request(vBatt);

            response = getResponse(req.getRequest(),ins,outs);

            b2hex = bytesToHex(response);

            //get data from byte
            int data1 = response[1];
            double data2 = response[2];
            double data = data2/10;

            //System.out.println(ts1+ " " + varName1+ " "+ b2hex);
            //fw.write(ts1+ "\t" + varName1+ "\t"+ b2hex+"\n");
            System.out.println(ts1+ " " + varName1+ " "+ data );
            fw.write(ts1+ "\t" + varName1+ "\t"+ b2hex+"\n");


            //===============================================================

            response = getResponse(reqVbatt.getRequest(),ins,outs);

            b2hex = bytesToHex(response);
            varName1 = "vBatt";

             data = response[2]/10;
//            System.out.println(ts1+ " " + varName1+ " "+ b2hex);
//            fw.write(ts1+ "\t" + varName1+ "\t"+ b2hex+"\n");
            System.out.println(ts1+ " " + varName1+ " " +data);
            fw.write(ts1+ "\t" + varName1+ "\t"+ b2hex+"\n");




            fw.close();
            serial.disconnect();

            long end = System.currentTimeMillis();
            long diff = end - start;
            System.out.println("Total time: "+diff+ " ms");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static byte [] getResponse(byte[] request, DataInputStream ins, DataOutputStream outs)throws IOException,InterruptedException{
        byte [] response = new byte[4];
        int varNumRequest,varNumResponse ;
        varNumRequest = request[0] << 2;
        varNumResponse = response[0] << 2;

        while( (byte)varNumRequest != (byte)varNumResponse) {

            outs.write(request);
            Thread.sleep(1000);
            //System.out.println("variable num from request: " + (byte) varNumRequest + " \n" + "var num from response: " + (byte) varNumResponse);

            ins.read(response);

            varNumResponse = response[0] << 2;

            if ((byte)varNumRequest != (byte)varNumResponse) {
                Thread.sleep(1000);
                outs.write(request);
            }
        }
        //System.out.println("var num from response: " + (byte)varNumResponse);
        return response;
    }

}