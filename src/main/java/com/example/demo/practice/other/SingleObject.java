package com.example.demo.practice.other;

public class SingleObject {
    private static SingleObject singleObject = new SingleObject();
    // 懒汉式
    private static SingleObject lazySingleObject;
    // 双重校验锁
    private volatile static SingleObject doubleLockSingleObject;
    // 静态内部类
    private static final SingleObject STATIC_SINGLE = new SingleObject();

    // 构造方法私有
    private SingleObject() {

    }

    //获取唯一可用的对象
    public static SingleObject getInstance() {
        return singleObject;
    }

    /**
     * 懒汉式 同步
     *
     * @return
     */
    public static synchronized SingleObject getLazyInstance() {
        if (lazySingleObject == null) {
            lazySingleObject = new SingleObject();
        }
        return lazySingleObject;
    }

    /**
     * 双检锁/双重校验锁
     * 这种方式采用双锁机制，安全且在多线程情况下能保持高性能。
     */
    public static SingleObject getDoubleLockInstance() {
        if (doubleLockSingleObject == null) {
            synchronized (SingleObject.class) {
                if (doubleLockSingleObject == null) {
                    doubleLockSingleObject = new SingleObject();
                }
            }
        }
        return doubleLockSingleObject;

    }

    public static SingleObject getStaticSingle() {

        return STATIC_SINGLE;

    }

    public void showMessage() {
        System.out.println("Hello World!");
    }

}
