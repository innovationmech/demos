package com.example.websocketclient.message;

public class Message {
    private String key;
    private String text;

    public String getText() {
        return text;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setText(String text) {
        this.text = text;
    }
}
