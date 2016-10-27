/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.services;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author lvbowen
 */
public class DealService {

    //Given the String json input, this method searches either LOGO, Text, Label on Diffmarts
    //Return the first result found in diffmarts, otherwise return a empty string.
    public static String retrieveDiffMartsResult(String jsonInput) {
        String diffMartsResult = "";

        try {
            JSONObject jb = new JSONObject(jsonInput);
            JSONObject jbResponse = jb.getJSONArray("responses").getJSONObject(0);

            boolean hasLogoAnnotations = jbResponse.has("logoAnnotations");
            boolean hasTextAnnotations = jbResponse.has("textAnnotations");
            boolean hasLabelAnnotations = jbResponse.has("labelAnnotations");
            boolean hasValidLogo = false;
            boolean noResult = false;
            String logoName = "";
            ArrayList<String> labels = new ArrayList<String>();
            ArrayList<String> texts = new ArrayList<String>();

            //If the json input includes LOGO annotation and the score larger than 50,
            //assigns the logo name to logoName variable, and hasValidLogo is ture.
            //Otherwise, logoName variable is empty,and hasValidLogo is false;
            if (hasLogoAnnotations) {
                JSONArray jbArray_logoAnnotations = jbResponse.getJSONArray("logoAnnotations");
                double score = jbArray_logoAnnotations.getJSONObject(0).getDouble("score");
                if (score >= 0.5) {
                    logoName = jbArray_logoAnnotations.getJSONObject(0).getString("description");
                    hasValidLogo = true;
                }
            }

            if (!hasValidLogo) {
                //If logo is not found or invalid, and the json input includes Label annotation
                //add labels, which score larger than 70, to labels arraylist.
                //Otherwise, noResult is ture. 
                if (hasLabelAnnotations) {
                    JSONArray jbArray_labelAnnotations = jbResponse.getJSONArray("labelAnnotations");
                    for (int i = 0; i < jbArray_labelAnnotations.length(); i++) {
                        JSONObject jbLabel = jbArray_labelAnnotations.getJSONObject(i);
                        double score = jbLabel.getDouble("score");
                        if (score >= 0.7) {
                            String label = jbLabel.getString("description");
                            labels.add(label);
                        }
                    }
                }
                if (labels.isEmpty()) {
                    noResult = true;
                }
            } else {
                //If the logo is found and valid, and the json input includes Text annotation
                //add all texts to texts arraylist (there is no score for each text)
                //Otherwise, noResult is true.
                if (hasTextAnnotations) {
                    JSONObject jb_textAnnotations = jbResponse.getJSONArray("textAnnotations").getJSONObject(0);
                    String textString = jb_textAnnotations.getString("description");
                    String[] tempArr = textString.split("\n");
                    for (String t : tempArr) {
                        texts.add(t);
                    }
                } else {
                    noResult = true;
                }
            }

            if (noResult) {

            } else if (hasValidLogo) {
                // Search logo & text in diffMarts
                String textStr = "";
                for (String t : texts) {
                    textStr = textStr + " " + t;
                }
                diffMartsResult = DiffMartsCrawler.getFirstResultFor(logoName + textStr);

            } else {
                // Search labels in diffMarts
                String textStr = "";
                for (String t : labels) {
                    textStr += t + " ";
                }
                diffMartsResult = DiffMartsCrawler.getFirstResultFor(logoName + textStr.trim());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return diffMartsResult;
    }

    //Given diffmarts result, this method will retrieve relevant deals in local database
    //Output is in Json format
    public static String retrieveDeals(String diffMartsResult) {

        return null;
    }

}
