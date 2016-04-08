<%@ page import="gnu.io.NRSerialPort" %>
<%@ page import="com.edward.app.Battery.Battery" %>
<%@ page import="java.io.*" %>




<html>
<head>
<title>Solar Telemetry</title>
</head>
<body>
<h2>Data from Kid</h2>
<br>

<%

    String port = "/dev/ttyUSB0";
    int baudRate = 57600;

    NRSerialPort serial = new NRSerialPort(port,  baudRate);

    serial.connect();

    DataInputStream ins = new DataInputStream(serial.getInputStream() );
    DataOutputStream outs = new DataOutputStream(serial.getOutputStream() );

    byte [] bytes = new byte[4];
    int len= -1;

    //masks
    int PK_TYPE_SET_mask = 0b10000000;
    int PK_TYPE_GET_mask= 0b11000000;
    int register_mask =  0b00111111;
    int register_name =0;
    int register_data = 0;
    String batteryTemp= "";

try{

            while( (len =ins.read( bytes) ) > -1 ) {

                StringBuilder sb = new StringBuilder();

                int val = bytes[0] & PK_TYPE_SET_mask ;
                if(val==128) {

                    for(byte b:bytes){
                        //for(int i=0;i<bytes.length;i++){
                        sb.append(String.format("%02X ", b));
                        //System.out.println(sb);
                    }

                    register_name = bytes[0] & register_mask;
                    register_data = bytes[1] << 8 ;
                    register_data = register_data | bytes[2];


                    switch(register_name){
                        case 22: //COMM_batteryTemp (deg. C)
                            //System.out.println( "COMM_batteryTemp (deg. C)");
                            //System.out.println( register_data);
                            batteryTemp =  "COMM_batteryTemp (deg. C)" + register_data;
                            break;

                    }

                    //System.out.println(sb.toString());
                }
            }

%>

piece of data: <%=batteryTemp %>
<%
   } catch (Exception e) {
               e.printStackTrace();
           }

           serial.disconnect();



%>





</body>
</html>
