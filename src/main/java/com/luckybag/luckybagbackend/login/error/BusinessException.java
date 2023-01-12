package com.luckybag.luckybagbackend.login.error;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{

    int status;
    public BusinessException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.status = errorCode.getStatus();
    }

}