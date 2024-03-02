package com.example.demo.practice.builder;

public abstract class Builder {
    protected Computer computer =new Computer();

    public void buildMemory(){

    }

    public void buildCpu(){

    }

    public void buildDisk(){

    }

    public Computer build(){
        return computer;
    }

}
