package com.zt.opinion;

import com.zt.opinion.utils.DateUtils;
import org.junit.Test;

import java.util.Date;
import java.util.stream.IntStream;

/**
 * Created by zhangtong on 2017/7/11.
 */
public class test {

    @Test
    public void test(){
        IntStream.rangeClosed(1, 10).forEach(a-> {
            IntStream.rangeClosed(1, 10).forEach(b -> {
                Date addDay = DateUtils.currentDateAddDay(a);
                Date currentDate = DateUtils.currentDateAddMinute(b, addDay);
                System.out.println("currentDate = " + DateUtils.formatDate(currentDate,DateUtils.DATE_SECOND_FORMAT));
            });
        });
    }
}
