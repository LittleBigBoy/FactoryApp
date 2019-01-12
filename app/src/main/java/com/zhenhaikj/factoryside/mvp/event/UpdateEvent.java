package com.zhenhaikj.factoryside.mvp.event;

public class UpdateEvent {
    private String message;

    public UpdateEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
