package com.example.demo.practice.adapter;

/**
 * @author DX
 */
public class AdapterPatternDemo {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.play("mp3", "最后的约定.mp3");
        audioPlayer.play("mp4", "说好不哭.mp4");
        audioPlayer.play("vlc", "baby.vlc");
        audioPlayer.play("avi", "mind me.avi");
    }
}
