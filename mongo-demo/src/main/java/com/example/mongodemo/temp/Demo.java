package com.example.mongodemo.temp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import javafx.util.Pair;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        String coOrdinate = "[{\"x\":58,\"y\":58.14},{\"x\":232.56,\"y\":58.14},{\"x\":232.56,\"y\":174.42},{\"x\":58.14,\"y\":174.42}]";

        List<Pair<Double, Double>> list = generateRandomPoint(coOrdinate, 7);
        for (Pair<Double, Double> pair : list) {
            System.out.println(pair.getKey() + "-" + pair.getValue());
        }
        System.out.println(Math.random());


    }
    public static List<Pair<Double, Double>> generateRandomPoint(String coOrdinate, int num) {
        JSONArray jsonArray = JSON.parseArray(coOrdinate);
        List<Pair<Double, Double>> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            double x1 = Double.parseDouble(jsonArray.getJSONObject(0).get("x").toString());

            double y1 = Double.parseDouble(jsonArray.getJSONObject(0).get("y").toString());
            double x2 = Double.parseDouble(jsonArray.getJSONObject(1).get("x").toString());
            double y3 = Double.parseDouble(jsonArray.getJSONObject(2).get("y").toString());

            double x = x1 + (x2 - x1) * Math.random();
            double y = y1 + (y3 - y1) * Math.random();
            BigDecimal a = new BigDecimal(x);
            BigDecimal b = new BigDecimal(y);
            x = a.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            y = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

            Pair<Double, Double> pair = new Pair<>(x, y);
            list.add(pair);
        }
        return list;
    }
}
