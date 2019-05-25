package com.jaeyeonling.boilerplate.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Error {

    private int code;
    private String message;
}
