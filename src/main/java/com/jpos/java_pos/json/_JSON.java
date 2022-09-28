package com.jpos.java_pos.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface _JSON {
    JSONArray reader(String fileName);

    JSONObject read(String fileName,String server);
}
