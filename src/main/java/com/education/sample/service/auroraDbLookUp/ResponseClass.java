package com.education.sample.service.auroraDbLookUp;

import java.util.List;
import java.util.Map;

public class ResponseClass {

    public List<Map<String, String>> results;

    public ResponseClass() {}

    public ResponseClass(List<Map<String, String>> results) {
        this.results = results;
    }

    public List<Map<String, String>> getResults() {
        return results;
    }

    public void setResults(List<Map<String, String>> results) {
        this.results = results;
    }
}
