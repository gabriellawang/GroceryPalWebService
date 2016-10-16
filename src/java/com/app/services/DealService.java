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
    public static String retrieveDiffMartsResult(String jsonInput){
        String diffMartsResult = "";
        
        try{
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
            if(hasLogoAnnotations){
                JSONArray jbArray_logoAnnotations = jbResponse.getJSONArray("logoAnnotations");
                double score = jbArray_logoAnnotations.getJSONObject(0).getDouble("score");
                if(score >= 0.5){
                    logoName = jbArray_logoAnnotations.getJSONObject(0).getString("description");
                    hasValidLogo = true;
                }
            }

            
            if(!hasValidLogo){
                //If logo is not found or invalid, and the json input includes Label annotation
                //add labels, which score larger than 70, to labels arraylist.
                //Otherwise, noResult is ture. 
                if(hasLabelAnnotations){
                    JSONArray jbArray_labelAnnotations = jbResponse.getJSONArray("labelAnnotations"); 
                    for(int i = 0 ; i < jbArray_labelAnnotations.length() ; i++){
                        JSONObject jbLabel = jbArray_labelAnnotations.getJSONObject(i);
                        double score = jbLabel.getDouble("score");
                        if(score >= 0.7){
                            String label = jbLabel.getString("description");
                            labels.add(label);
                        }
                    }
                } 
                if(labels.isEmpty()){
                    noResult = true;
                }
            } else {
                //If the logo is found and valid, and the json input includes Text annotation
                //add all texts to texts arraylist (there is no score for each text)
                //Otherwise, noResult is true.
                if(hasTextAnnotations){
                    JSONObject jb_textAnnotations = jbResponse.getJSONArray("textAnnotations").getJSONObject(0);
                    String textString = jb_textAnnotations.getString("description");
                    String[] tempArr = textString.split("\n");
                    for(String t : tempArr){
                        texts.add(t);
                    }
                } else {
                    noResult = true;
                }
            }

            if(noResult){

            } else if(hasValidLogo){
                // Search logo & text in diffMarts
                String textStr = "";
                for(String t : texts){
                    textStr = textStr + " " + t;
                }
                diffMartsResult = DiffMartsCrawler.getFirstResultFor(logoName + textStr);
                
            } else {
                // Search labels in diffMarts
                String textStr = "";
                for(String t : labels){
                    textStr += t + " ";
                }
                diffMartsResult = DiffMartsCrawler.getFirstResultFor(logoName + textStr.trim());
            }
            
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        
        return diffMartsResult;
    }
    
    //Given diffmarts result, this method will retrieve relevant deals in local database
    //Output is in Json format
    public static String retrieveDeals(String diffMartsResult){
        
        return null;
    }
    
    public static void main(String [] args){
        String jsonInput = "{\"responses\":[{\"logoAnnotations\":[{\"mid\":\"/m/045c7b\",\"description\":\"pokka\",\"score\":0.85000956,\"boundingPoly\":{\"vertices\":[{\"x\":158,\"y\":50},{\"x\":515,\"y\":50},{\"x\":515,\"y\":156},{\"x\":158,\"y\":156}]}}],\"labelAnnotations\":[{\"mid\":\"/m/021sdg\",\"description\":\"pokka\",\"score\":0.87143095},{\"mid\":\"/m/0dgsmq8\",\"description\":\"artwork\",\"score\":0.86358012},{\"mid\":\"/m/0dwx7\",\"description\":\"logo\",\"score\":0.31318793},{\"mid\":\"/m/01mf0\",\"description\":\"software\",\"score\":0.23124418},{\"mid\":\"/m/03g09t\",\"description\":\"clip art\",\"score\":0.20368107},{\"mid\":\"/m/02ngh\",\"description\":\"emoticon\",\"score\":0.19831011},{\"mid\":\"/m/0h8npc5\",\"description\":\"digital content software\",\"score\":0.1769385},{\"mid\":\"/m/03tqj\",\"description\":\"icon\",\"score\":0.097528793},{\"mid\":\"/m/0hr95w1\",\"description\":\"pointer\",\"score\":0.03663468},{\"mid\":\"/m/0n0j\",\"description\":\"area\",\"score\":0.033584446}],\"textAnnotations\":[{\"locale\":\"en\",\"description\":\"Google\\n\",\"boundingPoly\":{\"vertices\":[{\"x\":61,\"y\":26},{\"x\":598,\"y\":26},{\"x\":598,\"y\":227},{\"x\":61,\"y\":227}]}}]}]}";
        retrieveDiffMartsResult(jsonInput);
    }
}
