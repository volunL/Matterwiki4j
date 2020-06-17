package com.brainboom.matterwiki4jboot.services.exception;

public class DefaultTopicNotDeleteException extends Exception {


    public DefaultTopicNotDeleteException() {
        super("Can not delete default topic!");
    }

}
