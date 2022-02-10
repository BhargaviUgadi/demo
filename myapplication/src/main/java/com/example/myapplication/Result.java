package com.example.myapplication;

public class Result {
    public static Result mInstance;

    public static Result getInstance(){
        if(mInstance==null){
            mInstance=new Result();
        }
        return mInstance;
    }
}
