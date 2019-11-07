package com.education.sample.service.dynamoDbLookUp;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.Map;

public class ResponseClass {

    public Map<String, AttributeValue> output;

    public ResponseClass() {}

    public ResponseClass(Map<String, AttributeValue> output) {
        this.output = output;
    }

    public Map<String, AttributeValue> getOutput() {
        return output;
    }

    public void setOutput(Map<String, AttributeValue> output) {
        this.output = output;
    }
}
