package com.example.demo.decorator;

public class VideoPlayDecorator implements VideoPlay{

    private final VideoPlay videoPlay;

    public VideoPlayDecorator(VideoPlay videoPlay) {
        this.videoPlay = videoPlay;
    }

    @Override
    public void play() {
        videoPlay.play();
    }
}
