package com.example.demo.practice.builder;

public class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public Computer build(){
        builder.buildCpu();
        builder.buildDisk();
        builder.buildMemory();
        return builder.build();
    }
}
