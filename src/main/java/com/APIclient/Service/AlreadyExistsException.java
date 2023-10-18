package com.APIclient.Service;

public class AlreadyExistsException extends Exception{
    public AlreadyExistsException(String message) {
        super(message);
    }
}
