/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.services;

import com.app.DAO.DealDAO;
import com.app.model.Deal;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author lvbowen
 */
public class DealService {

    //Given the String json input, this method searches either LOGO, Text, Label on Diffmarts, then use the diffMarts result to search in database
    //Return the most relevant product name retrieved from crowd databse, otherwise return a empty string.
    public static ArrayList<Deal> retrieveProductNameByImage(String jsonInput, String udid) {
        //String resultToReturn = "";
        ArrayList<Deal> resultList = new ArrayList<>();

        try {
            JSONObject jb = new JSONObject(jsonInput);
            JSONObject jbResponse = jb.getJSONArray("responses").getJSONObject(0);
            
            //Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
            //System.out.println(gson.toJson(jb));
            
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
                    //System.out.println("Have valid LOGO!!!!!" + logoName);
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
                    textString = GoogleAutocorrect.correctThis(textString);
                    String[] tempArr = textString.split("\n");
                    for (String t : tempArr) {
                        texts.add(t);
                    }
                } else {
                    noResult = true;
                }
            }
            
            HashMap<Integer, Deal> idDealMap = new HashMap<>();
            if (noResult) {
                //System.out.println("There is no result!");
                // do nothing
            } else if (hasValidLogo) {
                //System.out.println("There is result!");
                // Search logo & text in diffMarts
                String textStr = "";
                for (String t : texts) {
                    textStr = textStr + " " + t;
                }
                String diffMartResult = DiffMartsCrawler.getFirstResultFor(logoName + textStr);
                String[] diffMartResultArr = diffMartResult.split(" ");

                //Todo  search product name elements in database
                ArrayList<Deal> deals = new ArrayList<Deal>();
                ArrayList<Integer> ids = new ArrayList<Integer>();
                for (String t : diffMartResultArr) {
                    deals = DealDAO.retrieveDealsByNameElement(udid, t);
                    for (Deal d : deals) {
                        ids.add(d.getDealId());
                        idDealMap.put(d.getDealId(), d);
                    }
                }
                Object[] idArr = ids.toArray();
                String[] idStrArr = new String[idArr.length];
                for (int i = 0; i < idArr.length; i++) {
                    int num = (Integer) idArr[i];
                    idStrArr[i] = "" + num;
                }

                //Get most relevant product from crowd database (search name elements) step 1 put into hashmap  key:product Id. value: how many relevant found
                Map<String, Integer> map = new HashMap<String, Integer>();
                for (int i = 0; i < idStrArr.length; i++) {
                    if (null != map.get(idStrArr[i])) {
                        map.put(idStrArr[i], map.get(idStrArr[i - 1]) + 1); //value+1  
                    } else {
                        map.put(idStrArr[i], 1);
                    }
                }
                //step 2 sort entries
                Set<Entry<String, Integer>> set = map.entrySet();
                List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(
                        set);
                Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

                    public int compare(Entry<String, Integer> o1,
                            Entry<String, Integer> o2) {
                        return o2.getValue().compareTo(o1.getValue());
                    }

                });
                //step 3 . get top 5 product or all top product(if less than 5)
                ArrayList<Integer> idsArrList = new ArrayList<Integer>();
                if (list.size() >= 5) {
                    for (int i = 0; i < 5; i++) {
                        Integer j = Integer.parseInt(list.get(i).getKey());
                        idsArrList.add(j);
                    }
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        Integer j = Integer.parseInt(list.get(i).getKey());
                        idsArrList.add(j);
                    }
                }
                //step 4 . retrieve deal by id
                for (Integer i : idsArrList) {
                    Deal d = idDealMap.get(i);
                    resultList.add(d);
                }

            } else {
                // Search labels in database
                //System.out.println("hello?????");
                ArrayList<Deal> deals = new ArrayList<Deal>();
                ArrayList<Integer> ids = new ArrayList<Integer>();
                for (String t : labels) {
                    deals = DealDAO.retrieveDealsByProperty(udid, t);
                    System.out.println("DealService.java " + udid);
                    for (Deal d : deals) {
                        ids.add(d.getDealId());
                        idDealMap.put(d.getDealId(), d);
                    }
                }
                //System.out.println(deals.size());
                Object[] idArr = ids.toArray();
                String[] idStrArr = new String[idArr.length];
                for (int i = 0; i < idArr.length; i++) {
                    int num = (Integer) idArr[i];
                    idStrArr[i] = "" + num;
                }

                //Get most relevant product (search labels) step 1 put into hashmap  key:product Id. value: how many relevant found
                Map<String, Integer> map = new HashMap<String, Integer>();
                for (int i = 0; i < idStrArr.length; i++) {
                    if (null != map.get(idStrArr[i])) {
                        map.put(idStrArr[i], map.get(idStrArr[i - 1]) + 1); //value+1  
                    } else {
                        map.put(idStrArr[i], 1);
                    }
                }
                //step 2 sort entries
                Set<Entry<String, Integer>> set = map.entrySet();
                List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(
                        set);
                Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

                    public int compare(Entry<String, Integer> o1,
                            Entry<String, Integer> o2) {

                        return o2.getValue().compareTo(o1.getValue());
                    }

                });
                //step 3 . get top 5 product or all top product(if less than 5)
                ArrayList<Integer> idsArrList = new ArrayList<Integer>();
                if (list.size() >= 5) {
                    for (int i = 0; i < 5; i++) {
                        Integer j = Integer.parseInt(list.get(i).getKey());
                        idsArrList.add(j);
                    }
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        Integer j = Integer.parseInt(list.get(i).getKey());
                        idsArrList.add(j);
                    }
                }
                //step 4 . retrieve deal by id
                for (Integer i : idsArrList) {
                    Deal d = idDealMap.get(i);
                    resultList.add(d);
                }

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return resultList;
    }

    //This method allows user to input a product name(split by whitespace)
    //Return the most relevant product name retrieve from  crowd database. If not found any result it will return empty string
    public static ArrayList<Deal> retrieveProductNameByText(String udid, String text) {
        //String resultToReturn = "";
        ArrayList<Deal> resultList = new ArrayList<>();
        HashMap<Integer, Deal> idDealMap = new HashMap<>();
        //Todo  search product name elements in database
        ArrayList<Deal> deals = new ArrayList<Deal>();
        ArrayList<Integer> ids = new ArrayList<Integer>();
        String[] textArr = text.split(" ");
        for (String t : textArr) {
            deals = DealDAO.retrieveDealsByNameElement(udid, t);
            for (Deal d : deals) {
                ids.add(d.getDealId());
                idDealMap.put(d.getDealId(), d);
            }
        }
        Object[] idArr = ids.toArray();
        String[] idStrArr = new String[idArr.length];
        for (int i = 0; i < idArr.length; i++) {
            int num = (Integer) idArr[i];
            idStrArr[i] = "" + num;
        }

        //Get most relevant product (search name elements)   step 1 put into hashmap  key:product Id. value: how many relevant found
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < idStrArr.length; i++) {
            if (null != map.get(idStrArr[i])) {
                map.put(idStrArr[i], map.get(idStrArr[i - 1]) + 1); //value+1  
            } else {
                map.put(idStrArr[i], 1);
            }
        }
        //step 2 sort entries
        Set<Entry<String, Integer>> set = map.entrySet();
        List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(
                set);
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

            public int compare(Entry<String, Integer> o1,
                    Entry<String, Integer> o2) {

                return o2.getValue().compareTo(o1.getValue());
            }

        });
        //step 3 . get top 5 product or all top product(if less than 5)
        ArrayList<Integer> idsArrList = new ArrayList<Integer>();
        if (list.size() >= 5) {
            for (int i = 0; i < 5; i++) {
                Integer j = Integer.parseInt(list.get(i).getKey());
                idsArrList.add(j);
            }
        } else {
            for (int i = 0; i < list.size(); i++) {
                Integer j = Integer.parseInt(list.get(i).getKey());
                idsArrList.add(j);
            }
        }
        //step 4 . retrieve deal by id
        for (Integer i : idsArrList) {
            Deal d = idDealMap.get(i);
            resultList.add(d);
        }

        return resultList;
    }

}
