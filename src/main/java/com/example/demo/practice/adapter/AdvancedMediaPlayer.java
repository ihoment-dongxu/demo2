package com.example.demo.practice.adapter;

/**
 * 高级播放器
 *
 * @author DX
 */
public interface AdvancedMediaPlayer {
    /**
     * 播放vlc文件
     *
     * @param name 文件名
     */
    void playVlc(String name);

    /**
     * 播放Mp4文件
     *
     * @param name 文件名
     */
    void playMp4(String name);
}
