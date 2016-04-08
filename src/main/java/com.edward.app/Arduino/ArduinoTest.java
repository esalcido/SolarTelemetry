package com.edward.app.Arduino;

import gnu.io.NRSerialPort;

import java.io.*;


/**
 * Created by edward on 02/20/16.
 */
public class ArduinoTest {

    public static void main(String [] args) {

        String port = "/dev/ttyUSB0";
        int baudRate = 57600;

        NRSerialPort serial = new NRSerialPort(port, baudRate);

        File file = new File("/home/edwardsalcido/Desktop/test.txt");

        serial.connect();

        DataInputStream ins = new DataInputStream(serial.getInputStream());
        DataOutputStream outs = new DataOutputStream(serial.getOutputStream());

        byte [] bytes = new byte[4];
        int len= -1;

        try
        {


            FileOutputStream fos = handleFile(file);

            while( (len =ins.read( bytes) ) > -1 ) {

                readSerial(fos, bytes, len );


            }
            fos.flush();
            fos.close();
            serial.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    public static FileOutputStream handleFile(File file)throws IOException{
        //Handle file
        //if file does not exist, create one
        if(!file.exists()){
            file.createNewFile();
        }
        //FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
        //BufferedWriter bw = new BufferedWriter(fw);
        FileOutputStream fos = new FileOutputStream(file.getAbsoluteFile(),true );
        return fos;
    }

    public static String batteryStage(long register_data){
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

    public static void printValues(long register_name, long register_data) {
        switch ((int) register_name) {
            case 22: //COMM_batteryTemp (deg. C)
                System.out.print("COMM_batteryTemp (deg. C): ");
                System.out.println(register_data);
                break;
            case 16: //COMM_batteryStage
                System.out.println("COMM_batteryStage");
                String battStage = batteryStage(register_data);
                System.out.println("battStage= " + battStage);
                break;
            case 18: //COMM_absorbV (V)
                System.out.println("COMM_absorbV (V)");
                System.out.println((double) (register_data / 10));
                break;
            case 19: //COMM_floatV (V)
                System.out.println("COMM_floatV (V)");
                System.out.println((double) (register_data / 10));
                break;
            case 20:
                System.out.println("COMM_eqV (V)");
                System.out.println((double) (register_data / 10));
                break;
            case 23:
                System.out.println("COMM_TempCompV (mV)");
                System.out.println(register_data);
                break;
            case 25:
                System.out.println("COMM_BattNominal (V)");
                System.out.println(register_data);
            case 10:
                System.out.println("COMM_vBATT (V)");
                System.out.println((double) (register_data / 10));
                break;
            case 21:
                System.out.println("COMM_absorbT (minutes)");
                System.out.println(register_data);
                break;
            case 26:
                System.out.println("COMM_EqT (minutes)");
                System.out.println(register_data);
                break;
            case 9:
                System.out.println("COMM_vPV");
                System.out.println((double) (register_data / 10));
                break;
        }
    }

    public static void readSerial(FileOutputStream fos, byte[] bytes, int len)throws IOException {

        //masks
        long PK_TYPE_SET_mask = 0b10000000;
        long PK_TYPE_GET_mask= 0b11000000;
        long register_mask =  0b00111111;
        long register_name =0;
        long register_data = 0;

        StringBuilder sb = new StringBuilder();
        long val = bytes[0] & PK_TYPE_SET_mask;

        //Print when a PK_TYPE_SET is found
        if (val == 128) {

            for (byte b : bytes) {
                //for(int i=0;i<bytes.length;i++){
                sb.append(String.format("%02X ", b));
                //sb.append(String.format("%16s ", Integer.toBinaryString(b).replace(' ','0')));
                fos.write(b);
            }
            System.out.println(sb.toString() );

            register_name = bytes[0] & register_mask;
            register_data = bytes[1] << 8;
            register_data = register_data | bytes[2];

            //print the Values coming in from the kid
            //printValues(register_name,register_data);

        }

    }

    public static int sizeof(Class dataType){
        if (dataType == null) throw new NullPointerException();

        if (dataType == int.class    || dataType == Integer.class)   return 4;
        if (dataType == short.class  || dataType == Short.class)     return 2;
        if (dataType == byte.class   || dataType == Byte.class)      return 1;
        if (dataType == char.class   || dataType == Character.class) return 2;
        if (dataType == long.class   || dataType == Long.class)      return 8;
        if (dataType == float.class  || dataType == Float.class)     return 4;
        if (dataType == double.class || dataType == Double.class)    return 8;

        return 4; // 32-bit memory pointer...


    }

}

