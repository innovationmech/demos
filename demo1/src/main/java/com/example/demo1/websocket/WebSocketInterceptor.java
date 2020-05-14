package com.example.demo1.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Component
public class WebSocketInterceptor extends HttpSessionHandshakeInterceptor {
    /**
     * 配置日志
     */
    private final static Logger logger = LoggerFactory.getLogger(WebSocketInterceptor.class);

    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse seHttpResponse,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        logger.info("握手之前");
        //从request里面获取对象，存放attributes
        if (serverHttpRequest instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest request = (ServletServerHttpRequest) serverHttpRequest;
            String userName = request.getServletRequest().getParameter("name");
            attributes.put("userName",userName);
            HttpSession session = request.getServletRequest().getSession(false);
            if (session != null) {
                //使用拼接ID区分WebSocketHandler，以便定向发送消息
                String sessionId = session.getId();  //一般直接保存user实体
                if (sessionId!=null) {

                    attributes.put("sessionId",sessionId);
                }

            }
        }

        return super.beforeHandshake(serverHttpRequest, seHttpResponse, wsHandler, attributes);
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                               Exception ex) {
        logger.info("握手之后");
        super.afterHandshake(request, response, wsHandler, ex);
    }
}


