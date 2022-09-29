package com.jpos.java_pos.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JSON implements JSONWriter {

    @Override
    public void writeJSON(String jsonFileName,String server,String key,String value,String key1,String value1) {
        //First server
            JSONArray jsonArray=new JSONReader().reader(jsonFileName);
            JSONObject serverDetails = new JSONObject();
            serverDetails.put(key, value);
            serverDetails.put(key1, value1);
            JSONObject object = new JSONObject();
            for (Object object1:jsonArray){
                JSONObject object2=(JSONObject) object1;
                object.put(server,object2);
            }
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

    @Override
    public void writeJSON(String jsonFileName,String server,String key,int value,String key1,String value1) {
        JSONObject serverDetails = new JSONObject();
        JSONArray saver1=new JSONReader().reader(jsonFileName);
        serverDetails.put(key, value);
        serverDetails.put(key1, value1);
        JSONObject object = new JSONObject();
        object.put(server, serverDetails);
        JSONArray serverList = new JSONArray();
        for (Object object1:saver1){
            JSONObject object2=(JSONObject) object1;
            serverList.add(object2);
        }
        serverList.add(object);
        //Write JSON file
        try  {
            FileWriter file = new FileWriter(jsonFileName);
            file.write(serverList.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void appendJSON(String jsonFileName, String server, String key, String value, String key1, String value1) {
        JSONObject serverDetails = new JSONObject();
        JSONArray saver1=new JSONReader().reader(jsonFileName);
        serverDetails.put(key, value);
        serverDetails.put(key1, value1);
        JSONObject object = new JSONObject();
        object.put(server, serverDetails);
        JSONArray serverList = new JSONArray();
        for (Object object1:saver1){
            JSONObject object2=(JSONObject) object1;
            serverList.add(object2);
        }
        serverList.add(object);
        //Write JSON file
        try  {
            FileWriter file = new FileWriter(jsonFileName);
            file.write(serverList.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateJSON(String jsonFileName, String key) {
        JSONReader reader=new JSONReader();
        JSONArray object = reader.reader(jsonFileName);
        ArrayList<JSONObject> objectArrayList = new ArrayList<>();
        for (Object object1 : object) {
            JSONObject object2 = (JSONObject) object1;
            JSONObject dbObject = (JSONObject) object2.get("Tickets");
            objectArrayList.add(dbObject);
        }
        for (JSONObject object1 : objectArrayList) {
            String content = (String) object1.get("Data");
            if (content==key){
                objectArrayList.remove(object1);
            }
        }
        //Write JSON file
        try  {
            FileWriter file = new FileWriter(jsonFileName);
            file.write(objectArrayList.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
