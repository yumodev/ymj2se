package com.yumodev.base;

import org.junit.Test;

public class TestBase {

    @Test
    public void testFinalReturn(){
        System.out.println(getData());
    }


    private int getData(){
        try {
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return 2;
        }
    }
}
