package com.xiyuan.orange.Common;

public class NoLoginException extends Exception{
    public NoLoginException() {
        super();
    }

    public NoLoginException(String message) {
        super(message);
    }
}
