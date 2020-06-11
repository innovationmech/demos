package com.example.mongodemo.response;

import com.example.mongodemo.request.HeatMapAddRequest;
import lombok.Data;

import java.util.List;

@Data
public class HeatMapQueryResponse {

    private int code;

    private String message;

    private List<HeatMapAddRequest> pics;
}
