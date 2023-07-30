package com.ade.tinder.config;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {

    SUCCESS(true, 1000, "request success"),
    NO_SUCH_USER(false, 2000, "no such user");

    private boolean isSuccess;
    private int code;
    private String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }


}
