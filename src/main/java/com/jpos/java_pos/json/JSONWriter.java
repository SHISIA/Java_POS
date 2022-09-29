package com.jpos.java_pos.json;

public interface JSONWriter {
    void writeJSON(String jsonFileName,String server,String key,String value,String key1,String value1);
    void  writeJSON(String jsonFileName,String server,String key,int value,String key1,String value1);
    void appendJSON(String jsonFileName,String server,String key,String value,String key1,String value1);
}
