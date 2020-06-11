package com.example.mongodemo.controller;

import com.example.mongodemo.request.HeatMapAddRequest;
import com.example.mongodemo.request.HeatMapDelRequest;
import com.example.mongodemo.response.HeatMapAddResponse;
import com.example.mongodemo.response.HeatMapDelResponse;
import com.example.mongodemo.response.HeatMapQueryResponse;
import com.example.mongodemo.service.HeatMapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/heat")
@Slf4j
public class HeatMapController {

    @Autowired
    private HeatMapService heatMapService;

    @PostMapping("/add")
    public HeatMapAddResponse add(@RequestBody HeatMapAddRequest request) {

        if (request.getImageId() != null && request.getImage() != null) {
            return heatMapService.addPic(request);
        } else {
            HeatMapAddResponse response = new HeatMapAddResponse();
            response.setCode(-1);
            response.setMessage("参数不符合要求");
            return response;
        }
    }

    @PostMapping("del")
    public HeatMapDelResponse del(@RequestBody HeatMapDelRequest request) {

        if (request.getImageId() != null) {
            return heatMapService.delPic(request);
        } else {
            HeatMapDelResponse response = new HeatMapDelResponse();
            response.setCode(-1);
            response.setMessage("参数不符合要求");
            return response;
        }
    }

    @GetMapping("/pics")
    public HeatMapQueryResponse query() {
        return heatMapService.getPics();
    }
}
