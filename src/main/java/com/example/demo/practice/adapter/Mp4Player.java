package com.example.demo.practice.adapter;

public class Mp4Player implements AdvancedMediaPlayer{
    @Override
    public void playVlc(String name) {
        // do nothing
    }

    @Override
    public void playMp4(String name) {
        System.out.println("Playing mp4 file. Name: "+ name);
    }
}
