package com.yumodev.collect.list;

import com.yumodev.utils.YmTestUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yumo on 7/30/16
 * 测试Java列表
 */
public class TestList{
    private static final String LOG_TAG = "TestList";

    public static void testArrayList(){
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++){
         list.add(i+"");
        }

        YmTestUtil.printListThroughIterator(list, LOG_TAG);
    }

    public static void main(String args[]){
        testArrayList();
    }
}
