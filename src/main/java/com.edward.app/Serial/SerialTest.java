package com.edward.app.Serial;

import gnu.io.NRSerialPort;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
//import java.security.Timestamp;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.ThreadFactory;

/**
 * Created by edwardsalcido on 4/9/16.
 */
public class SerialTest {

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
        String b2hex1="";
        String b2hex3="";

        int len= -1, i =0;
        byte [] bs = new byte[4];
        int pk_type_set_mask = 0b10000000;
        int pk_type_get_mask = 0b11000000;

        try
        {

            //calculate the first byte after converting it from it's first
            int val = 22;
            fos = new FileOutputStream(file.getAbsoluteFile(),true );
            fw=new FileWriter(file.getAbsoluteFile(),true);
            int newval = val ^ pk_type_get_mask;


//            System.out.println("dec: " + val);
//            System.out.println("new dec: " + newval);

            //compute xor
//            byte valByte = (byte) val;
//            int lastDigitMask = (byte) (valByte >> 2);
//                System.out.println("byte: " + valByte);

//================================================================================================

            //=============================================================
            String varName1 = "vPV";
            byte [] varBs1  = new byte[4];
            createByte2Send(varName1, varBs1,201,0,0,0);

            String pktype="";
            Timestamp ts1=null;
            //for(int j=0;j<8;j++) {
                //get timestamp
                Date date1 = new Date();
                long time1 = date1.getTime();
                ts1 = new Timestamp(time1);


                //send requests
                b2hex = fetchValue(varBs1, ins, outs, "89");
                b2hex = fetchValue(varBs1, ins, outs, "89");
                b2hex = fetchValue(varBs1, ins, outs, "89");
                if(!b2hex.equals("   ") ) {

                    String output = ts1 + "\t" + varName1 + "\t" + b2hex + "";
                    System.out.println(output + "");
                }
                else{
                    System.out.println(ts1 + "\t" + varName1 + "\t Error" +b2hex1);
                    fw.write(ts1 + "\t" + varName1 + "\t Error \n");
                }


            //fetTemp create the byte array *ERROR
//            String varName = "fetTempbs";
//            byte [] varBs  = new byte[4];
//            createByte2Send(varName, varBs,203,0,0,2);
            //Thread.sleep(2000);
//            //=============================================================

            //outs.write(varBs1);

            //Thread.sleep(10000);

            //Load state
            String varName3 = "loadState";
            byte [] varBs3  = new byte[4];
            createByte2Send(varName3, varBs3,204,0,0,0);

            //get timestamp
            Date date3 = new Date();
            long time3 = date3.getTime();
            Timestamp ts3 = new Timestamp(time3);
            //for(int j=0;j<7;j++) {

            //send requests
                b2hex1 = fetchValue(varBs3, ins, outs,"8C");
                b2hex1 = fetchValue(varBs3, ins, outs,"8C");



            if(!b2hex1.equals("false") ) {
                String output = ts3 + "\t" + varName3 + "\t" + b2hex1 + "";
                System.out.println(output);
                fw.write(output);
            }else{
                System.out.println(ts3 + "\t" + varName3 + "\t Error"+ b2hex1);
                fw.write(ts3 + "\t" + varName3 + "\t Error \n");
            }


//            Thread.sleep(1000);
            //===============================================================
//            //kwh *ERROR
            //=============================================================
//            String varName = "kwhbs";
//            byte [] varBs  = new byte[4];
//            createByte2Send(varName, varBs,205,0,0,1);


            //=============================================================
            String varName5 = "battStagebs";
            byte [] varBs5  = new byte[4];
            createByte2Send(varName5, varBs5,208,0,0,2);

            //get timestamp
            Date date5 = new Date();
            long time5 = date5.getTime();
            Timestamp ts5 = new Timestamp(time5);
            //send requests
            b2hex3 = fetchValue(varBs5, ins, outs,"90");
            b2hex3 = fetchValue(varBs5, ins, outs,"90");
            System.out.println(ts5 +" "+ varName5 +" " + b2hex3);
//
//            Thread.sleep(1000);
//            //=============================================================
//            String varName6 = "absorbbs";
//            byte [] varBs6  = new byte[4];
//            createByte2Send(varName6, varBs6,210,0,0,0);
//            //get timestamp
//            Date date6 = new Date();
//            long time6 = date6.getTime();
//            Timestamp ts6 = new Timestamp(time6);
//            //send requests
//            b2hex = fetchValue(varBs6, ins, outs);
//            System.out.println(ts6 +" "+ varName6 +" " + b2hex);
//
//            Thread.sleep(1000);
//            //=============================================================
//            String varName7 = "floatbs";
//            byte [] varBs7  = new byte[4];
//            createByte2Send(varName7, varBs7,211,0,0,1);
//
//            //get timestamp
//            Date date7 = new Date();
//            long time7 = date7.getTime();
//            Timestamp ts7 = new Timestamp(time7);
//            //send requests
//            b2hex = fetchValue(varBs7, ins, outs);
//            System.out.println(ts7 +" "+ varName7 +" " + b2hex);
//
//            Thread.sleep(1000);
//            //=============================================================
//            String varName8 = "vbattbs";
//            byte [] varBs8  = new byte[4];
//            createByte2Send(varName8, varBs8,202,0,0,3);
//
//            //get timestamp
//            Date date8 = new Date();
//            long time8 = date8.getTime();
//            Timestamp ts8 = new Timestamp(time8);
//            //send requests
//            b2hex = fetchValue(varBs8, ins, outs);
//            System.out.println(ts8 +" "+ varName8 +" " + b2hex);
//
//            Thread.sleep(1000);
//            //=============================================================
//            String varName9 = "eqVbs";
//            byte [] varBs9  = new byte[4];
//            createByte2Send(varName9, varBs9,212,0,0,3);
//
//            //get timestamp
//            Date date9 = new Date();
//            long time9 = date9.getTime();
//            Timestamp ts9 = new Timestamp(time9);
//            //send requests
//            b2hex = fetchValue(varBs9, ins, outs);
//            System.out.println(ts9 +" "+ varName9 +" " + b2hex);
//
//            Thread.sleep(1000);
//            //=============================================================
//            String varName10 = "absorbTbs";
//            byte [] varBs10  = new byte[4];
//            createByte2Send(varName10, varBs10,213,0,0,2);
//
//            //get timestamp
//            Date date10 = new Date();
//            long time10 = date10.getTime();
//            Timestamp ts10 = new Timestamp(time10);
//            //send requests
//            b2hex = fetchValue(varBs10, ins, outs);
//            System.out.println(ts10 +" "+ varName10 +" " + b2hex);
//
//            Thread.sleep(1000);
            //=============================================================
            //*ERROR
//            String varName = "battTempbs";
//            byte [] varBs  = new byte[4];
//            createByte2Send(varName, varBs,214,0,0,1);

            //=============================================================
            //*ERROR
//            String varName = "TempCompVbs";
//            byte [] varBs  = new byte[4];
//            createByte2Send(varName, varBs,215,0,0,1);

            //=============================================================
//            String varName12 = "battNominalbs";
//            byte [] varBs12  = new byte[4];
//            createByte2Send(varName12, varBs12,217,0,0,1);
//
//            //send requests
//                //get timestamp
//                Date date12 = new Date();
//                long time12 = date12.getTime();
//                Timestamp ts12 = new Timestamp(time12);
//                //send requests
//                b2hex = fetchValue(varBs12, ins, outs);
//                System.out.println(ts12 +" "+ varName12 +" " + b2hex);
//
//            Thread.sleep(1000);
//            //=============================================================
//            String varName13 = "eqtbs";
//            byte [] varBs13  = new byte[4];
//            createByte2Send(varName13, varBs13,218,0,0,2);
//
//
//           //for(int j=0;j<8;j++) {
//               //get timestamp
//               Date date13 = new Date();
//               long time13 = date13.getTime();
//               Timestamp ts13 = new Timestamp(time13);
//                //send requests
//                b2hex = fetchValue(varBs13, ins, outs);
//                System.out.println(ts13 +" "+ varName13 +" " + b2hex);
//
//           //}


            fw.flush();
            fos.flush();
            fos.close();
            fw.close();
            serial.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }


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
        //Thread.sleep(1000);
        outs.write( bs);
        outs.write( bs);
        //Thread.sleep(1000);
        outs.write( bs);
        outs.write( bs);
        //Thread.sleep(1000);
        outs.write( bs);
        //Thread.sleep(1000);
        outs.write( bs);
        outs.write( bs);
        //Thread.sleep(1000);
        outs.write( bs);
        outs.write( bs);
       // Thread.sleep(1000);
        outs.write( bs);
        //Thread.sleep(1000);
        outs.write( bs);
        //Thread.sleep(1000);
        outs.write( bs);
        //Thread.sleep(1000);
        outs.write( bs);
        //Thread.sleep(1000);
        outs.write( bs);
        outs.write( bs);
        outs.write( bs);
        outs.write( bs);
        //Thread.sleep(1000);
        outs.write( bs);
        Thread.sleep(1000);
        i = ins.read(bs);
        outs.write( bs);
        outs.write( bs);
        outs.write( bs);
        outs.write( bs);
        outs.write( bs);
        outs.write( bs);
        outs.write( bs);
        outs.write( bs);
        outs.write( bs);
        outs.write( bs);
        outs.write( bs);
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
