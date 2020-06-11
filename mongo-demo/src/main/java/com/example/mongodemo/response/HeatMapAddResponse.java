package com.example.mongodemo.response;

import lombok.Data;

@Data
public class HeatMapAddResponse {

    /**
     * 状态码
     */
    private int code;

    /**
     * 消息；
     */
    private String message;
}
