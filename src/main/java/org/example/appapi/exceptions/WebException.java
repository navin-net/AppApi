package org.example.appapi.exceptions;


import lombok.Getter;

@Getter
public class WebException extends RuntimeException {

    private String message;
    private String messageKh;
    private String code;


    public WebException() {
        super();
    }

    public WebException(String message,  String messageKh, String code) {
        super(message);
        this.message = message;
        this.messageKh = messageKh;
        this.code = code;
    }





}
