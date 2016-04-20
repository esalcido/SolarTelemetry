package com.edward.app.Serial;

import gnu.io.NRSerialPort;

import java.io.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.ThreadFactory;

//import java.security.Timestamp;

/**
 * Created by edwardsalcido on 4/9/16.
 */
public class SerialTest1 {

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

        String b2hex1="";
        String b2hex3="";

        int len= -1, i =0;
        byte [] bs = new byte[4];
        int pk_type__set_vpv = 0b11001001;
        int pk_type_get_vpv = 0b10001001;


        try
        {

            String b2hex="";

            fos = new FileOutputStream(file.getAbsoluteFile(),true );
            fw=new FileWriter(file.getAbsoluteFile(),true);


            //=====================================================================first command
            String varName1 = "vPV";
            byte [] varBs1  = new byte[4];
            createByte2Send(varName1, varBs1,201,0,0,0);

            String pktype="";
            Timestamp ts1=null;

                //get timestamp
                Date date1 = new Date();
                long time1 = date1.getTime();
                ts1 = new Timestamp(time1);


            outs.write(varBs1);
            outs.write(varBs1);
            outs.write(varBs1);
            Thread.sleep(4000);
            outs.write(varBs1);
            outs.write(varBs1);
            outs.write(varBs1);
            outs.write(varBs1);
            ins.read(varBs1);
            b2hex = bytesToHex(varBs1);

           b2hex = checkValidHeader(b2hex,"89",varBs1,1000,outs,ins);

//            while(!b2hex.substring(0,2).equals("89")){
//
//                Thread.sleep(1000);
//                outs.write(varBs1);
//
//                ins.read(varBs1);
//                b2hex = bytesToHex(varBs1);
//            }

            System.out.println(ts1+ " " + varName1+ " "+ b2hex);

            //Thread.sleep(4000);

//==================================================================================second command
            //Load state
            String varName3 = "loadState";
            byte [] varBs3  = new byte[4];
            createByte2Send(varName3, varBs3,204,0,0,0);


            outs.write(varBs3);
            outs.write(varBs3);
            Thread.sleep(4000);
            outs.write(varBs3);
            outs.write(varBs3);
            outs.write(varBs3);
            outs.write(varBs3);
            outs.write(varBs3);

            ins.read(varBs3);
            b2hex = bytesToHex(varBs3);

            b2hex = checkValidHeader(b2hex,"8C",varBs1,1000,outs,ins);

//            while(!b2hex.substring(0,2).equals("8C")){
//
//                outs.write(varBs1);
//                ins.read(varBs1);
//                b2hex = bytesToHex(varBs1);
//                System.out.println("b2hex: in while loop "+ b2hex);
//            }
            System.out.println(ts1+ " " + varName3+ " "+ b2hex);


            fw.flush();
            fos.flush();
            fos.close();
            fw.close();
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


    public static String fetchValue(byte [] bs, DataInputStream ins, DataOutputStream outs, String val)throws IOException, InterruptedException{

        String b2hex ="   ";
        int i;

        Thread.sleep(1000);
        outs.write(bs);
        outs.write(bs);
        outs.write(bs);
        outs.write(bs);
        outs.write(bs);
        outs.write(bs);
        outs.write(bs);
        outs.write(bs);
        outs.write(bs);
        outs.write(bs);
        Thread.sleep(1000);
        outs.write(bs);
        outs.write(bs);
        outs.write(bs);
        outs.write(bs);
        outs.write(bs);
        outs.write(bs);
        outs.write(bs);
        outs.write(bs);
        outs.write(bs);
        outs.write(bs);
        outs.write(bs);
        Thread.sleep(1000);
        outs.write(bs);
        outs.write(bs);
        outs.write(bs);
        i = ins.read(bs);

        //System.out.println((byte)bs[0]);

        //while(b2Hex.substring(0,2).equals(val)){


       // }

        //System.out.println(" number of bytes read: "+ i);
        //System.out.print(" bytes read: " );

        b2hex = bytesToHex(bs);
        //if(b2hex.substring(0,2).equals(val)){

            //System.out.println("it went through");
            return b2hex;
//        }else {
//            //System.out.println("it did not go through");
//            b2hex="false";
//            return b2hex;
//        }
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
