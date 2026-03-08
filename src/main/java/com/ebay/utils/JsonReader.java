package com.ebay.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JsonReader {


        public static String getKeyword(String keyword) throws Exception {

            String path = "src/main/resources/testdata/search-data.csv";
            JSONParser parser = new JSONParser();

            JSONObject json = (JSONObject) parser.parse(new FileReader(path));

            return json.get(keyword).toString();
        }



}
