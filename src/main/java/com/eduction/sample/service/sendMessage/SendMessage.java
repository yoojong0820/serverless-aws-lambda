package com.eduction.sample.service.sendMessage;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class SendMessage implements RequestHandler<RequestClass, ResponseClass> {

    @Override
    public ResponseClass handleRequest(RequestClass requestClass, Context context) {
        return new ResponseClass(
                String.format("User: %s | clicked the button!", requestClass.getUserName()),
                200,
                "SUCCESS"
        );
    }
}
