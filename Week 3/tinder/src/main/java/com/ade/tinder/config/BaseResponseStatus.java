package com.ade.tinder.config;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {

    SUCCESS(true, 1000, "request success"),
    NO_SUCH_ITEM(false, 2000, "no such item"),
    INVALID_PARAMETERS(false, 3000, "invalid parameters"),
    INVALID_REQUEST(false, 3001, "invalid request");

    private boolean isSuccess;
    private int code;
    private String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }


}
