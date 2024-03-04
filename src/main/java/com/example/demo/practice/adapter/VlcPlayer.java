package com.example.demo.practice.adapter;

/**
 * Vlc播放者
 *
 * @author DX
 */
public class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String name) {
        System.out.println("Playing vlc file. Name: " + name);
    }

    @Override
    public void playMp4(String name) {
        //什么也不做
    }
}
