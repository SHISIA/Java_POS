package com.jpos.java_pos.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JSONReader {
    public JSONObject read(String fileName,String server)
    {
        JSONObject object2 = new JSONObject();
        JSONParser jsonParser = new JSONParser();
        try
        {
            //Read JSON file
            FileReader reader = new FileReader(fileName);
            Object obj = jsonParser.parse(reader);
            JSONArray serverList = (JSONArray) obj;

            for (Object object: serverList){
                JSONObject object1=(JSONObject) object;
                object2= (JSONObject) object1.get(server);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        System.out.println(object2.get("username"));
        System.out.println(object2.get("password"));

        return object2;
    }
}
