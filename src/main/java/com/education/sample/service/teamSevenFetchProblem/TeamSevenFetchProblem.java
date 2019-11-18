package com.education.sample.service.teamSevenFetchProblem;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.education.sample.utils.AuroraProcessor;

import java.util.List;
import java.util.Map;

public class TeamSevenFetchProblem implements RequestHandler<RequestClass, ResponseClass> {

    @Override
    public ResponseClass handleRequest(RequestClass requestClass, Context context) {
        AuroraProcessor processor = new AuroraProcessor(requestClass.getSchema());
        String query = "SELECT * FROM team_seven.Problem;";
        List<Map<String, String>> results = processor.execute(query);
        return new ResponseClass(results);
    }
}
