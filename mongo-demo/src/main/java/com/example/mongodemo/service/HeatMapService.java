package com.example.mongodemo.service;

import com.example.mongodemo.request.HeatMapAddRequest;
import com.example.mongodemo.request.HeatMapDelRequest;
import com.example.mongodemo.response.HeatMapAddResponse;
import com.example.mongodemo.response.HeatMapDelResponse;
import com.example.mongodemo.response.HeatMapQueryResponse;

import java.util.List;

public interface HeatMapService {

    /**
     * 添加图片
     * @param request request
     * @return response
     */
    HeatMapAddResponse addPic(HeatMapAddRequest request);

    /**
     * 删除图片
     * @param request request
     * @return  response
     */
    HeatMapDelResponse delPic(HeatMapDelRequest request);

    /**
     * 图片列表
     * @return list
     */
    HeatMapQueryResponse getPics();

}
