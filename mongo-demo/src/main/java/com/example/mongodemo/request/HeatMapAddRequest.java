package com.example.mongodemo.request;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "heat_map")
public class HeatMapAddRequest {

    @Field("imageId")
    private String imageId;

    // 图片的字符串编码
    @Field("image")
    private String image;
}
