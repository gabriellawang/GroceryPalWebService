/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.services;

import java.io.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
/**
 *
 * @author Ivan
 */
public class GoogleAutocorrect {
  /*
  public static void main(String[] args) {
      String sampleSearchString = "pokka tea";
      System.out.println("\""+sampleSearchString+"\" corrected to:");
      String output = correctThis(sampleSearchString);
      System.out.println(output);
  }
    */
  // Given a search string, this method uses Google to autocorrect. If no autocorrect required, the original string will be returned
  public static String correctThis(String searchString) {
      String output = searchString;

      try {
          String url = "https://www.google.com.sg/search?q=";
          url += searchString;
          Document doc = Jsoup.connect(url).userAgent("Mozilla").get();
          Element content = doc.getElementById("center_col");
          String autocorrect = "";
          try {
            autocorrect = doc.select("[id=_FQd]").select("a").first().text().toString();
          } catch (Exception e) {
              
          }
          if (!autocorrect.equals("")) {
              output = autocorrect;
          }
      } catch (IOException e) {
          e.printStackTrace();
      }
      return output;
  }
}