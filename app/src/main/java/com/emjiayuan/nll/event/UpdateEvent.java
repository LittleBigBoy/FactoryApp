package com.emjiayuan.nll.event;

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
