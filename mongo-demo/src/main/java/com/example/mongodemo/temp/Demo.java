package com.example.mongodemo.temp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import javafx.util.Pair;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Demo {
    public static void main(String[] args) {
        String coOrdinate = "[{\"x\":58.14,\"y\":58.14},{\"x\":232.56,\"y\":58.14},{\"x\":232.56,\"y\":174.42},{\"x\":58.14,\"y\":174.42}]";

        List<Pair<Double, Double>> list = generateRandomPoint(coOrdinate, 7);
        for (Pair<Double, Double> pair : list) {
            System.out.println(pair.getKey() + "-" + pair.getValue());
        }


    }
    public static List<Pair<Double, Double>> generateRandomPoint(String coOrdinate, int num) {
        JSONArray jsonArray = JSON.parseArray(coOrdinate);
        List<Pair<Double, Double>> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            double x1 = ((BigDecimal)(jsonArray.getJSONObject(0).get("x"))).doubleValue();
            double y1 = ((BigDecimal)(jsonArray.getJSONObject(0).get("y"))).doubleValue();
            double x2 = ((BigDecimal)(jsonArray.getJSONObject(1).get("x"))).doubleValue();
            double y2 = ((BigDecimal)(jsonArray.getJSONObject(1).get("y"))).doubleValue();
            double x3 = ((BigDecimal)(jsonArray.getJSONObject(2).get("x"))).doubleValue();
            double y3 = ((BigDecimal)(jsonArray.getJSONObject(2).get("y"))).doubleValue();
            double x4 = ((BigDecimal)(jsonArray.getJSONObject(3).get("x"))).doubleValue();
            double y4 = ((BigDecimal)(jsonArray.getJSONObject(3).get("y"))).doubleValue();

            double x = x1 + (x2 - x1) * Math.random();
            double y = y3 + (y3 - y1) * Math.random();

            Pair<Double, Double> pair = new Pair<>(x, y);
            list.add(pair);
        }
        return list;
    }
}
