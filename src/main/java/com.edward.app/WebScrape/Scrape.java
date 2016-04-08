package com.edward.app.WebScrape;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by edwardsalcido on 4/7/16.
 */
public class Scrape {

    public static void main(String [] args) throws IOException{

        Document doc = Jsoup.connect("http://weather.com/").get();
        org.jsoup.select.Elements links = doc.select("div");

        for(Element e : links){
            System.out.println(e.attr("class=weather-phrase"));
        }


    }



}
