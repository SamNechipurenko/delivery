package com.delivery.exception;

// информация об ошибке
public class CostumedException extends Exception{

    // код ошибки и сообщение
    String message;
    int code;

    public CostumedException(String message, int code) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
