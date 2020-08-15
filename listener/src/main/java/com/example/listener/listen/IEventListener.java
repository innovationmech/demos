package com.example.listener.listen;

import com.example.listener.event.IEvent;

/**
 * 事件监听器，调用事件处理器
 */
public interface IEventListener {

    void doEvent(IEvent arg);
}
