package com.zt.opinion.controller;


/**
 * Created by zhangtong on 2017/7/4.
 */

class Factory {
    private int i = 0;
    private boolean created = false;
    public synchronized void create()
    {
        while (created)
        {
            try
            {
                wait();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        } i
            = i + 1;
        System.out.println(Thread.currentThread().getName() + "-create-" + i);
        this.created = true;
        notifyAll();
    }

    public synchronized void consume() {
        while (created)
        {
            System.out.println(Thread.currentThread().getName() + "-consume-"
                    + i);
            this.created = false;
            notifyAll();
        }
        try
        {
            wait();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

    }
}

/**
 * 生产者与消费者的基类
 */
abstract class AbsFactory implements Runnable {
    protected Factory factory = null;

    public AbsFactory(Factory factory) {
        this.factory = factory;
    }

    abstract protected void execute();

    public void run() {
        while (true) {
            execute();
        }
    }
}

class Producer extends AbsFactory {
    public Producer(Factory factory) {
        super(factory);
    }

    @Override
    protected void execute() {
        factory.create();
    }
}

class Consumer extends AbsFactory {
    public Consumer(Factory factory) {
        super(factory);
    }

    @Override
    protected void execute() {
        factory.consume();
    }
}


public class ProducerCustomer {

    public static void main(String[] args) {
//        if (args.length == 0) {
//            System.out.println("Usage:java ProducerCustomer number");
//            System.out.println("Please restart again....");
//            System.exit(0);
//        }
//        int count = 0;
//        try {
//            count = Integer.parseInt(args[0]);
//        } catch (Throwable t) {
//            System.out.println("Please enter a integer type number...");
//            System.exit(0);
//        }


        Factory factory = new Factory();
        new Thread(new Producer(factory)).start();
        new Thread(new Consumer(factory)).start();
    }
}



