package com.edward.app.WebScrape;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by edwardsalcido on 4/7/16.
 */
public class Scrapev2 {

    public static void main(String [] args) throws IOException{

        //get timestamp for wunderground
        Timestamp ts=null;
        Timestamp ts2=null;

        Date date = new Date();
        long time = date.getTime();
        ts = new Timestamp(time);

        Date date2 = new Date();
        long time2 = date2.getTime();
        ts2= new Timestamp(time);

        String userHome = System.getProperty("user.home");

        String htmlfileDirectory = userHome+"/scrape/htmldata/"+ts+"-wunderground-html.txt";
        String intellicastDirectory = userHome+"/scrape/htmldata/"+ts+"-intellicast-html.txt";


        //files
        File wundergroundhtml = new File(htmlfileDirectory);
        File intellicasthtml = new File(intellicastDirectory);

        FileWriter fw = new FileWriter(wundergroundhtml,true);
        FileWriter fwIntellicast = new FileWriter(intellicasthtml,true);

        //bufferedwriters
        BufferedWriter bw = new BufferedWriter(fw);
        if(!wundergroundhtml.exists() ){
            wundergroundhtml.createNewFile();
        }
        BufferedWriter bwIntellicast = new BufferedWriter(fwIntellicast);
        if(!intellicasthtml.exists() ){
            intellicasthtml.createNewFile();
        }

        //grab data from web and save to a file
        String urlWunder = "https://www.wunderground.com/history/airport/KVNY/2016/4/22/DailyHistory.html?req_city=Valley+Glen&req_state=CA&req_statename=&reqdb.zip=91401&reqdb.magic=3&reqdb.wmo=99999";
        writeToFile(urlWunder, bw);

        //grab data from web and save to a file
        String urlIntellicast = "http://www.intellicast.com/";
        writeToFile(urlIntellicast, bwIntellicast);

        //=========================================================================================================================================
        //data
        File dataF = new File(userHome+"/scrape/data/weatherdata.txt");
        FileWriter datawriter = new FileWriter(dataF,true);
        BufferedWriter dataBuffer = new BufferedWriter(datawriter);
        if(!wundergroundhtml.exists() ){
            wundergroundhtml.createNewFile();
        }

        //extract data from the saved file
        File wundergroundinput = wundergroundhtml;
        FileReader fr = new FileReader(wundergroundinput);
        BufferedReader br = new BufferedReader(fr);
        String line="", patternString=".*Los Angeles.*";

        Pattern pattern = Pattern.compile(patternString);

        while( (line = br.readLine() )!=null ){

            Matcher matcher = pattern.matcher(line);
            Boolean matches=matcher.matches();
            if(matches) {
                System.out.println(line);
            }
        }

        //==================================================================================

        //extract data from the saved file intellicast


        //close files
        dataBuffer.close();
        //databw.close();
        bw.close();
        bwIntellicast.close();

    }


    public static void writeToFile(String url, BufferedWriter bw)throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements temp = doc.select("html");

        String tempS = String.valueOf(temp);
        //System.out.println(temp);
        bw.write(tempS);
    }



    public static void scrapeHTML(BufferedWriter bw) throws IOException{



    }


}
