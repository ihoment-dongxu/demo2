package com.example.demo.practice.builder;

public class HighComputerBuilder extends Builder {

    @Override
    public void buildMemory() {
        System.out.println("32G");
        computer.setMemory("32G");
    }

    @Override
    public void buildCpu() {
        System.out.println("8核");
        computer.setCpu("8核");
    }

    @Override
    public void buildDisk() {
        System.out.println("固态");
        computer.setDisk("固态");
    }
}
