package com.springboot.prodreadyfeatures.Advices;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class ApiResposne<T> {

//    @JsonFormat(pattern = "hh-mm-ss dd-MM-yyyy")
    private LocalDateTime timeStamps;
    private T data;
    private ApiError error;

    public ApiResposne() {
        this.timeStamps = LocalDateTime.now();
    }

    public ApiResposne(T data) {
        this();
        this.data = data;
    }

    public ApiResposne(ApiError error) {
        this();
        this.error = error;
    }
}
