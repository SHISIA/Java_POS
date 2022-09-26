package com.jpos.java_pos.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class JSON {
    public void writeJSON(String jsonFileName,String server,String key,String value,String key1,String value1) {
        //First server
        JSONObject serverDetails = new JSONObject();
        serverDetails.put(key, value);
        serverDetails.put(key1, value1);
        JSONObject object = new JSONObject();
        object.put(server, serverDetails);
        //Add server to list
        JSONArray serverList = new JSONArray();
        serverList.add(object);
        //Write JSON file
        try  {
            FileWriter file = new FileWriter(jsonFileName);
            //We can write any JSONArray or JSONObject instance to the file
            file.write(serverList.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
