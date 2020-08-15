package com.example.listener.event;

import com.example.listener.listen.IEventListener;

/**
 * 事件
 */
public interface IEvent {

    void setEventListener(IEventListener arg);

    boolean clickButton();

    boolean moveMouse();
}
