package com.example.websocketserver.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

public class ClusterConnectManager {

    private final static Logger logger = LoggerFactory.getLogger(ClusterConnectManager.class);

    // 集群中所有websocket连接
    public static ConcurrentHashMap<String, String> clusterMap = new ConcurrentHashMap<>();

    public static void add(String server, String client) {
        logger.info("server:{}和client:{}建立的websocket连接", server, client);
        clusterMap.put(server, client);
    }


}
