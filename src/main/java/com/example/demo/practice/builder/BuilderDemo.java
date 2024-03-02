package com.example.demo.practice.builder;

public class BuilderDemo {
    public static void main(String[] args) {

        Builder lowBuilder = new LowComputerBuilder();
        Computer lowComputer = new Director(lowBuilder).build();
        System.out.println(lowComputer);

        Builder highBuilder = new HighComputerBuilder();
        Computer highComputer = new Director(highBuilder).build();
        System.out.println(highComputer);

    }
}
