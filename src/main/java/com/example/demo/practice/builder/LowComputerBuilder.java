package com.example.demo.practice.builder;

public class LowComputerBuilder extends Builder{
    @Override
    public void buildMemory() {
        System.out.println("16G");
        computer.setMemory("16G");
    }

    @Override
    public void buildCpu() {
        System.out.println("4核");
        computer.setCpu("4核");
    }

    @Override
    public void buildDisk() {
        System.out.println("机械");
        computer.setDisk("机械");
    }
}
