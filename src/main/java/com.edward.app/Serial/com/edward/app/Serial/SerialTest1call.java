package com.edward.app.Serial.com.edward.app.Serial;

import gnu.io.NRSerialPort;

import java.io.*;
import java.sql.Timestamp;
import java.util.Date;

//import java.security.Timestamp;

/**
 * Created by edwardsalcido on 4/9/16.
 */
public class SerialTest1call {

    public static void main(String [] args) {

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

        int len= -1, i =0;
        byte [] bs = new byte[4];
        int pk_type__set_vpv = 0b11001001;
        int pk_type_get_vpv = 0b10001001;


        try
        {

            //fos = new FileOutputStream(file.getAbsoluteFile(),true );
            fw=new FileWriter(file.getAbsoluteFile(),true);


            //  byte [] varBs, String setResponse,


            //===================================================================== command
//            String varName1 = "vPV";
//            byte [] varBs1  = new byte[4];
//            createByte2Send(varName1, varBs1,201,0,0,0);
//
//            Timestamp ts1=null;
//            //get timestamp
//            Date date1 = new Date();
//            long time1 = date1.getTime();
//            ts1 = new Timestamp(time1);
//
//            outs.write(varBs1);
//            outs.write(varBs1);
//            outs.write(varBs1);
//            Thread.sleep(4000);
//            outs.write(varBs1);
//            outs.write(varBs1);
//            outs.write(varBs1);
//            outs.write(varBs1);
//            ins.read(varBs1);
//            b2hex = bytesToHex(varBs1);
//            b2hex = checkValidHeader(b2hex,"89",varBs1,1000,outs,ins);
//            System.out.println(ts1+ " " + varName1+ " "+ b2hex);
//            fw.write(ts1+ "\t" + varName1+ "\t"+ b2hex+"\n");
//
//



            //================================================================================== command
            String varName5 = "KwH";
            byte [] varBs5  = new byte[4];
            createByte2Send(varName5, varBs5,205,0,0,1);

            Timestamp ts5=null;
            //get timestamp
            Date date5 = new Date();
            long time5 = date5.getTime();
            ts5 = new Timestamp(time5);

            outs.write(varBs5);
            outs.write(varBs5);
            Thread.sleep(4000);
            outs.write(varBs5);
            outs.write(varBs5);
            outs.write(varBs5);
            outs.write(varBs5);
            outs.write(varBs5);

            ins.read(varBs5);
            b2hex = bytesToHex(varBs5);
            b2hex = checkValidHeader(b2hex,"8D",varBs5,1000,outs,ins);
            System.out.println(ts5+ " " + varName5+ " "+ b2hex);
            fw.write(ts5+ "\t" + varName5+ "\t"+ b2hex+"\n");



            fw.flush();
            fw.close();
            //fos.flush();
            //fos.close();

            serial.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static String checkValidHeader(String b2hex, String header,byte[] varBs1,int delay,DataOutputStream outs ,DataInputStream ins)throws IOException,InterruptedException{
        while(!b2hex.substring(0,2).equals(header)){

            //Thread.sleep(delay);
            outs.write(varBs1);

            ins.read(varBs1);
            b2hex = bytesToHex(varBs1);
            System.out.println("in while loop: "+ b2hex);
        }
        return b2hex;

    }

    public static void createByte2Send(String varName, byte []  byteArr, int b1, int b2, int b3, int b4){
        //byte [] fetTempbs  = new byte[4];
        byteArr[0]= (byte)b1;
        byteArr[1]= (byte)b2;
        byteArr[2]= (byte)b3;
        byteArr[3]= (byte)b4;

        String fetTempString = bytesToHex(byteArr);
        //System.out.println(varName+ ": " + fetTempString);


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



}
