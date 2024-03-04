package com.example.demo.practice.adapter;

/**
 * 媒体播放器
 *
 * @author DX
 */
public interface MediaPlayer {
    /**
     * 播放
     *
     * @param type 文件类型
     * @param name 文件名
     */
    void play(String type, String name);
}
