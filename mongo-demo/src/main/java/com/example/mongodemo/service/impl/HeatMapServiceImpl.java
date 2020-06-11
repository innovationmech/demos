package com.example.mongodemo.service.impl;

import com.example.mongodemo.request.HeatMapAddRequest;
import com.example.mongodemo.request.HeatMapDelRequest;
import com.example.mongodemo.response.HeatMapAddResponse;
import com.example.mongodemo.response.HeatMapDelResponse;
import com.example.mongodemo.response.HeatMapQueryResponse;
import com.example.mongodemo.service.HeatMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HeatMapServiceImpl implements HeatMapService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public HeatMapAddResponse addPic(HeatMapAddRequest request) {

        List<HeatMapAddRequest> requests = new ArrayList<>();
        requests.add(request);
        mongoTemplate.insert(requests, HeatMapAddRequest.class);
        HeatMapAddResponse response = new HeatMapAddResponse();
        response.setCode(0);
        response.setMessage("success");
        return response;
    }

    @Override
    public HeatMapDelResponse delPic(HeatMapDelRequest request) {
        String imageId = request.getImageId();
        Query query = Query.query(Criteria.where("imageId").is(imageId));
        long num = mongoTemplate.remove(query, "heat_map").getDeletedCount();
        HeatMapDelResponse response = new HeatMapDelResponse();
        if (num != 0) {
            response.setCode(0);
            response.setMessage("success");
        } else {
            response.setCode(-1);
            response.setMessage("fail");
        }
        return response;
    }

    @Override
    public HeatMapQueryResponse getPics() {

        List<HeatMapAddRequest> pics = mongoTemplate.findAll(HeatMapAddRequest.class);
        HeatMapQueryResponse response = new HeatMapQueryResponse();
        if (!pics.isEmpty()) {
            response.setCode(0);
            response.setMessage("success");
            response.setPics(pics);
        } else {
            response.setCode(-1);
            response.setMessage("fail");
        }
        return response;
    }
}
