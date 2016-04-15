package com.edward.app.Serial;

import gnu.io.NRSerialPort;

import javax.xml.bind.DatatypeConverter;
import java.io.*;

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

        String b2hex="";
        StringBuilder sb1 = new StringBuilder();

        byte [] bytes = new byte[1];
        int len= -1, i =0;
        char c,c2,c3,c4;
        byte [] bs = new byte[4];

        try
        {

//FET Temperature
            byte [] fetTempbs  = new byte[4];
            fetTempbs[0]= (byte)203; //11001011 CB
            fetTempbs[1]= (byte)0; //00000000 0
            fetTempbs[2]= (byte)0; //00000000 0
            fetTempbs[3]= (byte)2; //00000010 2

            String fetTempString = bytesToHex(fetTempbs);
            System.out.println("fet temp Hex: " + fetTempString);

//Battery Voltage
            byte [] vbattbs  = new byte[4];
            vbattbs[0]= (byte)202; //11001010 CA
            vbattbs[1]= (byte)0; //00000000 0
            vbattbs[2]= (byte)0; //00000000 0
            vbattbs[3]= (byte)3; //00000011 3

            String vbattString = bytesToHex(vbattbs);
            System.out.println("vBatt Hex: " + vbattString);

//absorb
            byte [] absorbbs  = new byte[4];
            absorbbs[0]= (byte)210; //11001010 D2
            absorbbs[1]= (byte)0; //00000000 0
            absorbbs[2]= (byte)0; //00000000 0
            absorbbs[3]= (byte)0; //00000011 1

            String absorbString = bytesToHex(absorbbs);
            System.out.println("vBatt Hex: " + absorbString);

for(int j=0;j<4;j++) {
    b2hex = fetchValue(absorbbs, ins, outs);

    System.out.println(b2hex);
}

//send requests
//            outs.write( fetTempbs);
//            Thread.sleep(1000);
//            outs.write( fetTempbs);
//            Thread.sleep(2000);
//            outs.write( fetTempbs);
//            outs.write( fetTempbs);
//            outs.write( fetTempbs);
//            Thread.sleep(3000);
//            i = ins.read(bs);
//            System.out.println(" number of bytes read: "+ i);
//            System.out.print(" bytes read: " );
//            b2hex = bytesToHex(bs);
//            System.out.println(b2hex);
//
//            outs.write( fetTempbs);
//            outs.write( fetTempbs);
//            outs.write( fetTempbs);
//            Thread.sleep(1000);
//            outs.write( fetTempbs);
//            Thread.sleep(2000);
//            outs.write( fetTempbs);
//            Thread.sleep(3000);
//            i = ins.read(bs);
//            System.out.println(" number of bytes read: "+ i);
//            System.out.print(" bytes read: " );
//            b2hex = bytesToHex(bs);
//            System.out.println(b2hex);
//
//            outs.write( fetTempbs);
//            outs.write( fetTempbs);
//            outs.write( fetTempbs);
//            Thread.sleep(1000);
//            outs.write( fetTempbs);
//            Thread.sleep(2000);
//            outs.write( fetTempbs);
//            Thread.sleep(3000);
//            i = ins.read(bs);
//            System.out.println(" number of bytes read: "+ i);
//            System.out.print(" bytes read: " );
//            b2hex = bytesToHex(bs);
//            System.out.println(b2hex);

//            outs.write( vbattbs);
//            outs.write( vbattbs);
//            outs.write( vbattbs);
//            Thread.sleep(1000);
//            outs.write( vbattbs);
//            Thread.sleep(2000);
//            outs.write( vbattbs);
//            Thread.sleep(3000);
//            i = ins.read(bs);
//            System.out.println(" number of bytes read: "+ i);
//            System.out.print(" bytes read: " );
//            b2hex = bytesToHex(bs);
//            System.out.println(b2hex);
//
//            outs.write( vbattbs);
//            outs.write( vbattbs);
//            outs.write( vbattbs);
//            Thread.sleep(1000);
//            outs.write( vbattbs);
//            Thread.sleep(2000);
//            outs.write( vbattbs);
//            Thread.sleep(3000);
//            i = ins.read(bs);
//            System.out.println(" number of bytes read: "+ i);
//            System.out.print(" bytes read: " );
//            b2hex = bytesToHex(bs);
//            System.out.println(b2hex);
//
//            outs.write( vbattbs);
//            outs.write( vbattbs);
//            outs.write( vbattbs);
//            Thread.sleep(1000);
//            outs.write( vbattbs);
//            Thread.sleep(2000);
//            outs.write( vbattbs);
//            Thread.sleep(3000);
//            i = ins.read(bs);
//            System.out.println(" number of bytes read: "+ i);
//            System.out.print(" bytes read: " );
//            b2hex = bytesToHex(bs);
//            System.out.println(b2hex);
//
//            outs.write( vbattbs);
//            outs.write( vbattbs);
//            outs.write( vbattbs);
//            Thread.sleep(1000);
//            outs.write( vbattbs);
//            Thread.sleep(2000);
//            outs.write( vbattbs);
//            Thread.sleep(3000);
//            i = ins.read(bs);
//            System.out.println(" number of bytes read: "+ i);
//            System.out.print(" bytes read: " );
//            b2hex = bytesToHex(bs);
//            System.out.println(b2hex);
//
//            outs.write( vbattbs);
//            outs.write( vbattbs);
//            outs.write( vbattbs);
//            Thread.sleep(1000);
//            outs.write( vbattbs);
//            Thread.sleep(2000);
//            outs.write( vbattbs);
//            Thread.sleep(3000);
//            i = ins.read(bs);
//            System.out.println(" number of bytes read: "+ i);
//            System.out.print(" bytes read: " );
//            b2hex = bytesToHex(bs);
//            System.out.println(b2hex);
//
//            outs.write( vbattbs);
//            outs.write( vbattbs);
//            outs.write( vbattbs);
//            Thread.sleep(1000);
//            outs.write( vbattbs);
//            Thread.sleep(2000);
//            outs.write( vbattbs);
//            Thread.sleep(3000);
//            i = ins.read(bs);
//            System.out.println(" number of bytes read: "+ i);
//            System.out.print(" bytes read: " );
//            b2hex = bytesToHex(bs);
//            System.out.println(b2hex);
//
//            outs.write( vbattbs);
//            outs.write( vbattbs);
//            outs.write( vbattbs);
//            Thread.sleep(1000);
//            outs.write( vbattbs);
//            Thread.sleep(2000);
//            outs.write( vbattbs);
//            Thread.sleep(3000);
//            i = ins.read(bs);
//            System.out.println(" number of bytes read: "+ i);
//            System.out.print(" bytes read: " );
//            b2hex = bytesToHex(bs);
//            System.out.println(b2hex);


//            outs.write( absorbbs);
//            outs.write( absorbbs);
//            outs.write( absorbbs);
//            Thread.sleep(1000);
//            outs.write( absorbbs);
//            Thread.sleep(2000);
//            outs.write( absorbbs);
//            Thread.sleep(3000);
//            i = ins.read(bs);
//            System.out.println(" number of bytes read: "+ i);
//            System.out.print(" bytes read: " );
//            b2hex = bytesToHex(bs);
//            System.out.println(b2hex);
//
//            outs.write( absorbbs);
//            outs.write( absorbbs);
//            outs.write( absorbbs);
//            Thread.sleep(1000);
//            outs.write( absorbbs);
//            Thread.sleep(2000);
//            outs.write( absorbbs);
//            Thread.sleep(3000);
//            i = ins.read(bs);
//            System.out.println(" number of bytes read: "+ i);
//            System.out.print(" bytes read: " );
//            b2hex = bytesToHex(bs);
//            System.out.println(b2hex);
//
//            outs.write( absorbbs);
//            outs.write( absorbbs);
//            outs.write( absorbbs);
//            Thread.sleep(1000);
//            outs.write( absorbbs);
//            Thread.sleep(2000);
//            outs.write( absorbbs);
//            Thread.sleep(3000);
//            i = ins.read(bs);
//            System.out.println(" number of bytes read: "+ i);
//            System.out.print(" bytes read: " );
//            b2hex = bytesToHex(bs);
//            System.out.println(b2hex);
//
//            outs.write( absorbbs);
//            outs.write( absorbbs);
//            outs.write( absorbbs);
//            Thread.sleep(1000);
//            outs.write( absorbbs);
//            Thread.sleep(2000);
//            outs.write( absorbbs);
//            Thread.sleep(3000);
//            i = ins.read(bs);
//            System.out.println(" number of bytes read: "+ i);
//            System.out.print(" bytes read: " );
//            b2hex = bytesToHex(bs);
//            System.out.println(b2hex);




            serial.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static String fetchValue(byte [] bs, DataInputStream ins, DataOutputStream outs)throws IOException, InterruptedException{

        String b2hex = "";
        int i;

        outs.write( bs);
            Thread.sleep(1000);
            outs.write( bs);
            Thread.sleep(2000);
            outs.write( bs);
            outs.write( bs);
            outs.write( bs);
            Thread.sleep(3000);
            i = ins.read(bs);
            System.out.println(" number of bytes read: "+ i);
            System.out.print(" bytes read: " );
            b2hex = bytesToHex(bs);
            System.out.println(b2hex);

        return b2hex;
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
