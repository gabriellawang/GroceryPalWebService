/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.services;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Ivan
 */
class DiffMartsCrawler {
    
  public static void main(String[] args) {
      String sampleSearchString = "rittersport nut";
      String output = getFirstResultFor(sampleSearchString);
      System.out.println(output);
  }
  // Given a search string separated by spaces, this method searches on diffmarts.com
  // and returns the string of the first result
  public static String getFirstResultFor(String searchString) {
      String output = "";
      searchString = searchString.replaceAll(" ", "+");
      try {
          String url = "https://www.diffmarts.com/search/";
          url += searchString;
          System.out.println(url);
          Document doc = Jsoup.connect(url).get();
          Element content = doc.getElementById("search-result");
          String firstResult = doc.select("[class=item-box]").first().select("[class=name]").toString();
          output = firstResult.substring(firstResult.indexOf('>')+1,firstResult.indexOf('<', 1));
      } catch (IOException e) {
          e.printStackTrace();
      }
      return output;
  }
}

