package com.brainboom.matterwiki4jboot.error;


import lombok.Data;


/**
 *
 * error class
 * 错误类
 * @author volun
 * @version 1
 * */

@Data
public class Error {

    private String message;
    private boolean error;

    public Error(String message, boolean error) {
        this.message = message;
        this.error = error;
    }
}
