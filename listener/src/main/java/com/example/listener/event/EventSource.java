package com.example.listener.event;

import com.example.listener.listen.IEventListener;

public class EventSource implements IEvent {

    private IEventListener mEventListener;
    private boolean button;
    private boolean mouse;

    // 注册监听器
    @Override
    public void setEventListener(IEventListener arg) {
        mEventListener = arg;
    }

    public void mouseEventHappened() {
        mouse = true;
        mEventListener.doEvent(this);
    }

    @Override
    public boolean clickButton() {
        return button;
    }

    @Override
    public boolean moveMouse() {
        return mouse;
    }
}
