package com.education.sample.service.dynamoDbLookUp;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.HashMap;
import java.util.Map;

public class DynamoDbLookUp implements RequestHandler<RequestClass, ResponseClass> {

    @Override
    public ResponseClass handleRequest(RequestClass requestClass, Context context) {
        ResponseClass responseClass = new ResponseClass();

        Map<String, AttributeValue> key = new HashMap<>();
        key.put(requestClass.getKey(), new AttributeValue(requestClass.getValue()));

        GetItemRequest request = new GetItemRequest()
                .withTableName(requestClass.getTable())
                .withKey(key);

        final AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.defaultClient();

        try {
            Map<String, AttributeValue> returnedItem = ddb.getItem(request).getItem();
            if (returnedItem != null) {
                responseClass = new ResponseClass(returnedItem);
            } else {
                System.out.format("No item found with the key %s!\n", requestClass.getKey());
            }
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        }
        return responseClass;
    }
}