package com.edward.app.Serial;

import gnu.io.NRSerialPort;

import java.io.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by edwardsalcido on 4/9/16.
 */
public class SerialTest1callEfficientDowhile {

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

            byte [][] requests = new byte[6][];

            //requests[0] = new byte[4];//first command
            //requests[1] = new byte[4];

             String [] names = {"PV(V)","Battery(V)","FET Temperature","Load State","Battery Stage","Absorb(V)"};

            requests[0] = hexStringToByteArray("C9000000");//vPv
            requests[1] = hexStringToByteArray("CA000003");//vBatt
            requests[2] = hexStringToByteArray("CB000002");//fet temp
            requests[3] = hexStringToByteArray("CC000000");//loadstate
            requests[4] = hexStringToByteArray("D0000002");//batteryStage
            requests[5] = hexStringToByteArray("D2000000");//AbsorbV
            String varName1;

            //start for loop here
            for(int i=0;i<requests.length;i++){

                //String varName1 = "tetst";
                 varName1= names[i];
                int data1=0;
                double data=0,data2=0;
                byte [] response = new byte[4];

                //==============================================================

                byte [] request = requests[i];
                int varNumRequest,varNumResponse ;
                varNumRequest = request[0] << 2;
                varNumResponse = response[0] << 2;

                do {
                    outs.write(request);
                    Thread.sleep(1000);

                    ins.read(response);

                    varNumResponse = response[0] << 2;

                    if ((byte)varNumRequest != (byte)varNumResponse) {
                        Thread.sleep(1000);
                        outs.write(request);

                    }
                }while( (byte)varNumRequest != (byte)varNumResponse);

                b2hex = bytesToHex(response);

                //get data from byte
                data1 = response[1];
                data2 = response[2];
                //convert to string then convert hex to
                String dataString =Double.toString(data2);
                data2 = hex2decimal(dataString);
                //if variable is absorbV floatV  eqV vBATT  vPV
                if(names[i].equals("PV(V)") || names[i].equals("Absorb(V)")) {

                    data = data2 / 10;
                }

                //Timestamp
                Timestamp ts1=null;
                //get timestamp
                Date date1 = new Date();
                long time1 = date1.getTime();
                ts1 = new Timestamp(time1);

  //              System.out.println(ts1+ " " + varName1+ " "+ b2hex);
//                fw.write(ts1+ "\t" + varName1+ "\t"+ b2hex+"\n");
                System.out.println(ts1+ " " + varName1+ " "+ data);
                //fw.write(ts1+ "\t" + varName1+ "\t"+ data+"\n");

            }//end for loop


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

    public static byte[] hexStringToByteArray(String s){
        int len = s.length();
        byte[] data = new byte[len/2];
        for(int i=0;i<len;i+=2){
            data[i/2] = (byte)((Character.digit(s.charAt(i),16)<<4) + Character.digit(s.charAt(i+1),16));
        }
        return data;
    }

    public static int hex2decimal(String s) {
        String digits = "0123456789ABCDEF";
        s = s.toUpperCase();
        int val = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int d = digits.indexOf(c);
            val = 16*val + d;
        }
        return val;
    }


}