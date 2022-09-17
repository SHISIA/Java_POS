package com.jpos.java_pos.Model;

public class DBManager {

    public DBManager(String name, String path) {
        this.dbName = name;
        this.dbPath = path;

    }
    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbPath() {
        return dbPath;
    }

    public void setDbPath(String dbPath) {
        this.dbPath = dbPath;
    }

    String dbName;
    String dbPath;
}
