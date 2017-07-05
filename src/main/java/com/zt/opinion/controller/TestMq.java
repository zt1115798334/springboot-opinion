package com.zt.opinion.controller;

;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zhangtong on 2017/7/5.
 */
public class TestMq {
    public static void main(String[] args){
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
    }
}
