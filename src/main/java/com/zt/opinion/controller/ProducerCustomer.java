package com.zt.opinion.controller;


import com.google.common.collect.Maps;
import com.zt.opinion.activeMQDemo.Comsumer;
import com.zt.opinion.activeMQDemo.Producter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangtong on 2017/7/4.
 */

class Factory {
    private int i = 0;
    private boolean created = false;
    private Map<String, Object> map = Maps.newHashMap();
    private List<String> filePathList;
    private Operation operation;
    private Producter producter = new Producter();
    private Comsumer comsumer = new Comsumer();

    public Factory() {
    }

    public Factory(List<String> filaPathList, Operation operation) {
        this.filePathList = filaPathList;
        this.operation = operation;
        producter.init();
        comsumer.init();
    }

    public synchronized void create() {
        while (created) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        File file = new File(filePathList.get(i));
        String fileName = file.getName();
        if (!map.containsKey(fileName)) {
            if (i != (filePathList.size() - 1)) {
                i = i + 1;
                InputStream is = null;
                try {
                    is = new FileInputStream(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                map.put(fileName, null);
//                Workbook book = null;
//                try {
//                    book = new XSSFWorkbook(is);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("book = " + book);

//                producter.sendMessage("zt-MQ",fileName);
//                List<Article> listArticle = operation.getReadExcel(fileName,is);
//                System.out.println("listArticle.size() = " + listArticle.size());
            }
        }

        this.created = true;
        notifyAll();
    }

    public synchronized void consume() {
        while (created) {
//                comsumer.getMessage("zt-MQ");
                this.created = false;
                notifyAll();
        }
        try {
            wait();
        } catch (InterruptedException e) {
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

        List<String> filePathList = Arrays.asList("E:\\采集数据\\供应链金融语科\\供应链金融语科0701\\供应链金融语料样本-搜狗0701.xlsx",
                "E:\\采集数据\\供应链金融语科\\供应链金融语科0701\\供应链金融语料样本-百度0701.xlsx",
                "E:\\采集数据\\供应链金融语科\\供应链金融语科0702\\供应链金融语料样本-搜狗0702.xlsx",
                "E:\\采集数据\\供应链金融语科\\供应链金融语科0702\\供应链金融语料样本-百度0702.xlsx",
                "E:\\采集数据\\供应链金融语科\\供应链金融语科0703\\供应链金融语料样本-搜狗0703.xlsx",
                "E:\\采集数据\\供应链金融语科\\供应链金融语科0703\\供应链金融语料样本-百度0703.xlsx",
                "E:\\采集数据\\供应链金融语科\\供应链金融语科6月29日\\供应链金融语料样本-搜狗_0629.xlsx",
                "E:\\采集数据\\供应链金融语科\\供应链金融语科6月29日\\供应链金融语料样本-百度_0629.xlsx",
                "E:\\采集数据\\供应链金融语科\\供应链金融语科6月30日\\供应链金融语料样本-搜狗.xlsx",
                "E:\\采集数据\\供应链金融语科\\供应链金融语科6月30日\\供应链金融语料样本-百度.xlsx");
        Factory factory = new Factory(filePathList, null);
        new Thread(new Producer(factory)).start();
        new Thread(new Consumer(factory)).start();
    }
}



