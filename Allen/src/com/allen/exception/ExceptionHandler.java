package com.allen.exception;

public class ExceptionHandler {

    public static void handleException(Exception ex) {
        System.err.println("An error occurred " + ex.getMessage());
    }
}
