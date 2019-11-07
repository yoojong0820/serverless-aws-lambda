package com.education.sample.service.dynamoDbLookUp;

public class RequestClass {

    public String table;

    public String key;

    public String value;

    public RequestClass() {}

    public RequestClass(String table, String key, String value) {
        this.table = table;
        this.key = key;
        this.value = value;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
