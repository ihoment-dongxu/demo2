package com.example.demo.practice.adapter;

/**
 * 适配器
 * 这里为 AdvancedMediaPlayer 做适配操作
 *
 * @author DX
 */
public class MediaAdapter implements MediaPlayer {

    private AdvancedMediaPlayer advancedMediaPlayer;

    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedMediaPlayer = new VlcPlayer();
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMediaPlayer = new Mp4Player();
        }
    }

    @Override
    public void play(String type, String name) {
        if(type.equalsIgnoreCase("vlc")){
            advancedMediaPlayer.playVlc(name);
        }else if(type.equalsIgnoreCase("mp4")){
            advancedMediaPlayer.playMp4(name);
        }
    }
}
