package com.mftplus.shop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductGroupNotFoundException extends RuntimeException{
    public ProductGroupNotFoundException(String message){
        super(message);
    }
}
