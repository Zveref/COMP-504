package edu.rice.comp504.model.util;

import java.util.ArrayList;

public class SpeechDetector {
    private static ArrayList<String> badWords = new ArrayList<String>();



    public static boolean detectDir(String string) {
        boolean indicator = false;
        badWords.add("fuck");
        badWords.add("shit");
        badWords.add("dumb");
        badWords.add("ass");
        String src = string.toLowerCase();
        for(String temp: badWords){
            if(string.contains(temp)){
                string.replace(temp, "**DWords**");
                indicator = true;
            }
        }
        return indicator;
    }

    public static ArrayList<String> getRules(){
            return badWords;
        }
}
