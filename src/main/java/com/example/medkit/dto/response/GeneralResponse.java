package com.example.medkit.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

public class GeneralResponse {
    private int code;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String message;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Object data;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Object error;

    public GeneralResponse(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public GeneralResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public GeneralResponse(int code, String message, Object error) {
        this.code = code;
        this.message = message;
        this.error = error;
    }

    public static GeneralResponse success(String message) {
        return new GeneralResponse(200, message);
    }

    public static GeneralResponse success(Object data) {
        return new GeneralResponse(200, data);
    }

    public static GeneralResponse success() {
        return new GeneralResponse(200, "success");
    }

    public static GeneralResponse error(int code, String message, Object error) {
        return new GeneralResponse(code, message, error);
    }

    public static GeneralResponse error(int code, String message) {
        return new GeneralResponse(code, message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }
}
