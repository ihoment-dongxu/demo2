package com.example.demo.practice.other;

public class SingleObjectDemo {
    public static void main(String[] args) {
        // 默认方法实现单例
        SingleObject.getInstance().showMessage();
        // 懒汉式
        SingleObject.getLazyInstance().showMessage();
        // 双重校验锁
        SingleObject.getDoubleLockInstance().showMessage();
        // 静态方法
        SingleObject.getStaticSingle().showMessage();
        // 枚举实现
        SingleObjectEnum.INSTANCE.showMessage();
    }
}
