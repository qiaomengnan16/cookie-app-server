package com.app.server.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Result<T> {

    private Integer code = 200;

    private String message;

    private T data;

    public Result() {

    }

    public Result(T data) {
        this.data = data;
    }

}
