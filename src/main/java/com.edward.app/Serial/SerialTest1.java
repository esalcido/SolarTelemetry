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
            String varName1 = "vPV";
            byte [] varBs1  = new byte[4];
            createByte2Send(varName1, varBs1,201,0,0,0);

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
            System.out.println(ts1+ " " + varName1+ " "+ b2hex);
            fw.write(ts1+ "\t" + varName1+ "\t"+ b2hex+"\n");
            //Thread.sleep(4000);

            //===================================================================== command
            String varName2 = "Battery(V)";
            byte [] varBs2  = new byte[4];
            createByte2Send(varName2, varBs2,202,0,0,3);

            Timestamp ts2=null;
            //get timestamp
            Date date2 = new Date();
            long time2 = date2.getTime();
            ts2 = new Timestamp(time2);

            outs.write(varBs2);
            outs.write(varBs2);
            outs.write(varBs2);
            Thread.sleep(4000);
            outs.write(varBs2);
            outs.write(varBs2);
            outs.write(varBs2);
            outs.write(varBs2);
            ins.read(varBs2);
            b2hex = bytesToHex(varBs2);
            b2hex = checkValidHeader(b2hex,"8A",varBs2,1000,outs,ins);
            System.out.println(ts2+ " " + varName2+ " "+ b2hex);
            fw.write(ts2+ "\t" + varName2+ "\t"+ b2hex+"\n");

//================================================================================== command
            String varName3 = "FET Temp";
            byte [] varBs3  = new byte[4];
            createByte2Send(varName3, varBs3,203,0,0,2);

            Timestamp ts3=null;
            //get timestamp
            Date date3 = new Date();
            long time3 = date3.getTime();
            ts3 = new Timestamp(time3);

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
            b2hex = checkValidHeader(b2hex,"8B",varBs3,1000,outs,ins);
            System.out.println(ts3+ " " + varName3+ " "+ b2hex);
            fw.write(ts3+ "\t" + varName3+ "\t"+ b2hex+"\n");


//================================================================================== command
            String varName4 = "Load State";
            byte [] varBs4  = new byte[4];
            createByte2Send(varName4, varBs4,204,0,0,0);

            Timestamp ts4=null;
            //get timestamp
            Date date4 = new Date();
            long time4 = date4.getTime();
            ts4 = new Timestamp(time4);

            outs.write(varBs4);
            outs.write(varBs4);
            Thread.sleep(4000);
            outs.write(varBs4);
            outs.write(varBs4);
            outs.write(varBs4);
            outs.write(varBs4);
            outs.write(varBs4);
            ins.read(varBs4);
            b2hex = bytesToHex(varBs4);
            b2hex = checkValidHeader(b2hex,"8C",varBs4,1000,outs,ins);
            System.out.println(ts4+ " " + varName4+ " "+ b2hex);
            fw.write(ts4+ "\t" + varName4+ "\t"+ b2hex+"\n");

            //================================================================================== command
//            String varName5 = "KwH";
//            byte [] varBs5  = new byte[4];
//            createByte2Send(varName5, varBs5,205,0,0,1);
//
//            Timestamp ts5=null;
//            //get timestamp
//            Date date5 = new Date();
//            long time5 = date5.getTime();
//            ts5 = new Timestamp(time5);
//
//            outs.write(varBs5);
//            outs.write(varBs5);
//            Thread.sleep(4000);
//            outs.write(varBs5);
//            outs.write(varBs5);
//            outs.write(varBs5);
//            outs.write(varBs5);
//            outs.write(varBs5);
//
//            ins.read(varBs5);
//            b2hex = bytesToHex(varBs5);
//            b2hex = checkValidHeader(b2hex,"8D",varBs5,1000,outs,ins);
//            System.out.println(ts5+ " " + varName5+ " "+ b2hex);
//            fw.write(ts5+ "\t" + varName5+ "\t"+ b2hex+"\n");

//================================================================================== command

            String varName6 = "Battery Stage";
            byte [] varBs6  = new byte[4];
            createByte2Send(varName6, varBs6,208,0,0,2);

            Timestamp ts6=null;
            //get timestamp
            Date date6 = new Date();
            long time6 = date6.getTime();
            ts6 = new Timestamp(time6);

            outs.write(varBs6);
            outs.write(varBs6);
            Thread.sleep(4000);
            outs.write(varBs6);
            outs.write(varBs6);
            outs.write(varBs6);
            outs.write(varBs6);
            outs.write(varBs6);
            ins.read(varBs6);
            b2hex = bytesToHex(varBs6);
            b2hex = checkValidHeader(b2hex,"90",varBs6,1000,outs,ins);
            System.out.println(ts6+ " " + varName6+ " "+ b2hex);
            fw.write(ts6+ "\t" + varName6+ "\t"+ b2hex+"\n");


            //================================================================================== command

            String varName7 = "Absorb(V)";
            byte [] varBs7  = new byte[4];
            createByte2Send(varName7, varBs7,210,0,0,0);

            Timestamp ts7=null;
            //get timestamp
            Date date7 = new Date();
            long time7 = date7.getTime();
            ts7 = new Timestamp(time7);

            outs.write(varBs7);
            outs.write(varBs7);
            Thread.sleep(4000);
            outs.write(varBs7);
            outs.write(varBs7);
            outs.write(varBs7);
            outs.write(varBs7);
            outs.write(varBs7);
            ins.read(varBs7);
            b2hex = bytesToHex(varBs7);
            b2hex = checkValidHeader(b2hex,"92",varBs7,1000,outs,ins);
            System.out.println(ts7+ " " + varName7+ " "+ b2hex);
            fw.write(ts7+ "\t" + varName7+ "\t"+ b2hex+"\n");


            //================================================================================== command

            String varName8 = "Float(V)";
            byte [] varBs8  = new byte[4];
            createByte2Send(varName8, varBs8,210,0,0,0);

            Timestamp ts8=null;
            //get timestamp
            Date date8 = new Date();
            long time8 = date8.getTime();
            ts8 = new Timestamp(time8);

            outs.write(varBs8);
            outs.write(varBs8);
            Thread.sleep(4000);
            outs.write(varBs8);
            outs.write(varBs8);
            outs.write(varBs8);
            outs.write(varBs8);
            outs.write(varBs8);
            ins.read(varBs8);
            b2hex = bytesToHex(varBs8);
            b2hex = checkValidHeader(b2hex,"93",varBs8,1000,outs,ins);
            System.out.println(ts8+ " " + varName8+ " "+ b2hex);
            fw.write(ts8+ "\t" + varName8+ "\t"+ b2hex+"\n");

            //================================================================================== command

            String varName9 = "eq(V)";
            byte [] varBs9  = new byte[4];
            createByte2Send(varName9, varBs9,212,0,0,3);

            Timestamp ts9=null;
            //get timestamp
            Date date9 = new Date();
            long time9 = date9.getTime();
            ts9 = new Timestamp(time9);

            outs.write(varBs9);
            outs.write(varBs9);
            Thread.sleep(4000);
            outs.write(varBs9);
            outs.write(varBs9);
            outs.write(varBs9);
            outs.write(varBs9);
            outs.write(varBs9);
            ins.read(varBs9);
            b2hex = bytesToHex(varBs9);
            b2hex = checkValidHeader(b2hex,"94",varBs9,1000,outs,ins);
            System.out.println(ts9+ " " + varName9+ " "+ b2hex);
            fw.write(ts9+ "\t" + varName9+ "\t"+ b2hex+"\n");

            //================================================================================== command

            String varName10 = "Absorb Time";
            byte [] varBs10  = new byte[4];
            createByte2Send(varName10, varBs10,213,0,0,2);

            Timestamp ts10=null;
            //get timestamp
            Date date10 = new Date();
            long time10 = date10.getTime();
            ts10 = new Timestamp(time10);

            outs.write(varBs10);
            outs.write(varBs10);
            Thread.sleep(4000);
            outs.write(varBs10);
            outs.write(varBs10);
            outs.write(varBs10);
            outs.write(varBs10);
            outs.write(varBs10);
            ins.read(varBs10);
            b2hex = bytesToHex(varBs10);
            b2hex = checkValidHeader(b2hex,"92",varBs10,1000,outs,ins);
            System.out.println(ts10+ " " + varName10+ " "+ b2hex);
            fw.write(ts10+ "\t" + varName10+ "\t"+ b2hex+"\n");

            //================================================================================== command

            String varName11 = "Battery Temperature";
            byte [] varBs11  = new byte[4];
            createByte2Send(varName11, varBs11,214,0,0,1);

            Timestamp ts11=null;
            //get timestamp
            Date date11 = new Date();
            long time11 = date11.getTime();
            ts11 = new Timestamp(time11);

            outs.write(varBs11);
            outs.write(varBs11);
            Thread.sleep(4000);
            outs.write(varBs11);
            outs.write(varBs11);
            outs.write(varBs11);
            outs.write(varBs11);
            outs.write(varBs11);
            ins.read(varBs11);
            b2hex = bytesToHex(varBs11);
            b2hex = checkValidHeader(b2hex,"96",varBs11,1000,outs,ins);
            System.out.println(ts11+ " " + varName11+ " "+ b2hex);
            fw.write(ts11+ "\t" + varName11+ "\t"+ b2hex+"\n");


            //================================================================================== command

            String varName12 = "Temp Comp(V)";
            byte [] varBs12  = new byte[4];
            createByte2Send(varName12, varBs12,215,0,0,1);

            Timestamp ts12=null;
            //get timestamp
            Date date12 = new Date();
            long time12 = date12.getTime();
            ts12 = new Timestamp(time12);

            outs.write(varBs12);
            outs.write(varBs12);
            Thread.sleep(4000);
            outs.write(varBs12);
            outs.write(varBs12);
            outs.write(varBs12);
            outs.write(varBs12);
            outs.write(varBs12);
            ins.read(varBs12);
            b2hex = bytesToHex(varBs12);
            b2hex = checkValidHeader(b2hex,"97",varBs12,1000,outs,ins);
            System.out.println(ts12+ " " + varName12+ " "+ b2hex);
            fw.write(ts12+ "\t" + varName12+ "\t"+ b2hex+"\n");

//================================================================================== command

            String varName13 = "Battery Nominal";
            byte [] varBs13  = new byte[4];
            createByte2Send(varName13, varBs13,217,0,0,1);

            Timestamp ts13=null;
            //get timestamp
            Date date13 = new Date();
            long time13 = date13.getTime();
            ts13 = new Timestamp(time13);

            outs.write(varBs13);
            outs.write(varBs13);
            Thread.sleep(4000);
            outs.write(varBs13);
            outs.write(varBs13);
            outs.write(varBs13);
            outs.write(varBs13);
            outs.write(varBs13);
            ins.read(varBs13);
            b2hex = bytesToHex(varBs13);
            b2hex = checkValidHeader(b2hex,"99",varBs13,1000,outs,ins);
            System.out.println(ts13+ " " + varName13+ " "+ b2hex);
            fw.write(ts13+ "\t" + varName13+ "\t"+ b2hex+"\n");

//================================================================================== command

            String varName14 = "equal time";
            byte [] varBs14  = new byte[4];
            createByte2Send(varName14, varBs14,218,0,0,2);

            Timestamp ts14=null;
            //get timestamp
            Date date14 = new Date();
            long time14 = date14.getTime();
            ts14 = new Timestamp(time14);

            outs.write(varBs14);
            outs.write(varBs14);
            Thread.sleep(4000);
            outs.write(varBs14);
            outs.write(varBs14);
            outs.write(varBs14);
            outs.write(varBs14);
            outs.write(varBs14);
            ins.read(varBs14);
            b2hex = bytesToHex(varBs14);
            b2hex = checkValidHeader(b2hex,"9A",varBs14,1000,outs,ins);
            System.out.println(ts14+ " " + varName14+ " "+ b2hex);
            fw.write(ts14+ "\t" + varName14+ "\t"+ b2hex+"\n");



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
            //System.out.println("in while loop: "+ b2hex);
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
