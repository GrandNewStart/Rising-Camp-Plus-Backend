package com.ade.tinder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BaseResponse<T> {
    private int status;
    private String message;
    private String info;
    private T data;
}
