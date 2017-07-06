package com.zt.opinion.mq;

import org.apache.activemq.ActiveMQConnection;

/**
 * Created by zhangtong on 2017/7/5.
 */
public class TestProducter {
    public static void main(String[] args){
        System.out.println("ActiveMQConnection.DEFAULT_BROKER_URL = " + ActiveMQConnection.DEFAULT_BROKER_URL);
        Producter producter = new Producter();
        producter.init();
        TestProducter testMq = new TestProducter();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //Thread 1
        new Thread(testMq.new ProductorMq(producter)).start();
        //Thread 2
        new Thread(testMq.new ProductorMq(producter)).start();
        //Thread 3
        new Thread(testMq.new ProductorMq(producter)).start();
        //Thread 4
        new Thread(testMq.new ProductorMq(producter)).start();
        //Thread 5
        new Thread(testMq.new ProductorMq(producter)).start();

    }

    private class ProductorMq implements Runnable{
        Producter producter;
        public ProductorMq(Producter producter){
            this.producter = producter;
        }

        @Override
        public void run() {
            while(true){
                try {
                    producter.sendMessage("myeq1","开始测试");
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
