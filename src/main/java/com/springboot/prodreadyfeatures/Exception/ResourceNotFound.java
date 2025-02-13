package com.springboot.prodreadyfeatures.Exception;

import org.springframework.web.bind.annotation.RestController;

public class ResourceNotFound extends RuntimeException{
    public ResourceNotFound(String message) {
        super(message);
    }
}
