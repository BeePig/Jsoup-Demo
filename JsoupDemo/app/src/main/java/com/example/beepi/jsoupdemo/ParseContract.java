package com.example.beepi.jsoupdemo;

/**
 * Created by beepi on 17/01/2017.
 */

public interface ParseContract {
    public interface View{
        public void showContent(String url);
    }
    public interface Presenter{
        public String getContent();
        public Boolean checkAndRequestPermission();
    }
}
