package com.gulsun.BookStore.exception;

public class ConflictException extends RuntimeException{

    //message paremetreli constructorunu oluştur

    public ConflictException(String message) {
        super(message);
    }
}
