package com.xiyuan.orange.Common;

public class NotCreatedUserDetailException extends Exception{
    public NotCreatedUserDetailException(){
        super();
    }
    public NotCreatedUserDetailException(String info){
        super(info);
    }
}
