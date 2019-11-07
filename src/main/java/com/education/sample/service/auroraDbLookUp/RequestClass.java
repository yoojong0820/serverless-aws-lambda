package com.education.sample.service.auroraDbLookUp;

public class RequestClass {

    public String schema;

    public RequestClass() {}

    public RequestClass(String schema) {
        this.schema = schema;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }
}
