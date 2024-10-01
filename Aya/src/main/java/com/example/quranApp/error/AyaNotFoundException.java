package com.example.quranApp.error;

public class AyaNotFoundException extends Exception {

    public AyaNotFoundException() {
        super();
    }

    public AyaNotFoundException(String message) {
        super(message);
    }

    public AyaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AyaNotFoundException(Throwable cause) {
        super(cause);
    }

}
