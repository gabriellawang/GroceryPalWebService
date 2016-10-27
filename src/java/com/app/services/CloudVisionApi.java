/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import org.apache.commons.codec.binary.Base64;





/**
 *
 * @author Ivan
 */
public class CloudVisionApi {
    
    
    public static void main(String[] args) {
        String dir = System.getProperty("user.dir");
        File f = new File(dir,"demo.jpg");
        Path p = f.toPath();
        

    }
    // Takes in path to image (Using File.toPath())
    // returns result from GoogleCloudVisionAPI as String in JSON format
    // KEY uses Ivan's account. Do not exceed 1000 calls/mth
    public static String callCloudVision(Path imagePath) {
        
        String API_KEY = "AIzaSyAO54O7qFW7jdQVacFrXPgXfOXTbWi-2vs";
        String results = "";
	try {

            URL url = new URL("https://vision.googleapis.com/v1/images:annotate?key="+API_KEY);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            byte[] data = Files.readAllBytes(imagePath);
            String base64String = Base64.encodeBase64String(data);
            
            String input = "{\n" +
                            "  \"requests\": \n" +
                            "  [\n" +
                            "    {\n" +
                            "      \"features\": \n" +
                            "      [\n" +
                            "        {\n" +
                            "          \"type\": \"LABEL_DETECTION\"\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"type\": \"LABEL_DETECTION\"\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"type\": \"TEXT_DETECTION\"\n" +
                            "        }\n" +
                            "      ],\n" +
                            "      \"image\": \n" +
                            "      {\n" +
                            "        \"content\": \""+base64String+"\"\n" +
                            "      }\n" +
                            "    }\n" +
                            "  ]\n" +
                            "}";
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();
 
           if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String output;
            //System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                    results += output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
	}
        return results;
    }

    // Given an image, returns an ArrayList<String> labels
    public static ArrayList<String> getLabels(Path imagePath) {
        ArrayList<String> labels = new ArrayList<String>();
        String jsonInput = callCloudVision(imagePath);
        try{
            JSONObject jb = new JSONObject(jsonInput);
            JSONObject jbResponse = jb.getJSONArray("responses").getJSONObject(0);

            boolean hasLabelAnnotations = jbResponse.has("labelAnnotations");

            if(hasLabelAnnotations){
                JSONArray jbArray_labelAnnotations = jbResponse.getJSONArray("labelAnnotations"); 
                for(int i = 0 ; i < jbArray_labelAnnotations.length() ; i++){
                    JSONObject jbLabel = jbArray_labelAnnotations.getJSONObject(i);
                    String label = jbLabel.getString("description");
                    labels.add(label);
                }
            } 
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        
        return labels;
    }

}
