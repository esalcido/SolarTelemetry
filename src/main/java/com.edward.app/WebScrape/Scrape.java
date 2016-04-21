package com.edward.app.WebScrape;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by edwardsalcido on 4/7/16.
 */
public class Scrape {

    public static void main(String [] args) throws IOException{

        File html = new File("/home/edwardsalcido/Desktop/wunderground-html.txt");
        FileWriter fw = new FileWriter(html,true);
        BufferedWriter bw = new BufferedWriter(fw);
        if(!html.exists() ){
            html.createNewFile();
        }


        //grab data from web and save to a file
        writeToFile("https://www.wunderground.com/history/airport/KVNY/2016/04/21/DailyHistory.html?req_city=Valley%20Glen&req_state=CA&reqdb.zip=91401&reqdb.magic=3&reqdb.wmo=99999&MR=1", bw);


        //extract data from the saved file
        File input = html;
        Document doc = Jsoup.parse(input,"UTF-8");

        Element content = doc.getElementById("historyTable");
        Elements td = content.getElementsByTag("td");

        for(Element elmnt: td ){
           String temperature = elmnt.text();
            if(temperature.equals("Mean Temperature")){
                System.out.println(temperature);
               temperature =  elmnt.nextElementSibling().text();
                System.out.println(temperature);
                break;
            }

        }

        //System.out.println(td);





        bw.close();


    }


    public static void writeToFile(String url, BufferedWriter bw)throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements temp = doc.select("html");

        String tempS = String.valueOf(temp);
        //System.out.println(temp);
        bw.write(tempS);
    }



    public static void scrapeHTML(BufferedWriter bw) throws IOException{
        Document doc = Jsoup.connect("https://www.wunderground.com/history/airport/KVNY/2016/04/21/DailyHistory.html?req_city=Valley%20Glen&req_state=CA&reqdb.zip=91401&reqdb.magic=3&reqdb.wmo=99999&MR=1").get();
        Elements temp = doc.select("html");

        String tempS = String.valueOf(temp);
        System.out.println(temp);
        bw.write(tempS);
    }


}
