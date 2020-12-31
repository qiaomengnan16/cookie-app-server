package com.app.server.util;

import lombok.Data;

@Data
public class ServiceException extends RuntimeException {

    private int status = 500;

    public ServiceException(String message) {
        super(message);
    }

}
