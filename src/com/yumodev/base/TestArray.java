package com.yumodev.base;

import junit.framework.TestCase;
import org.junit.Test;

public class TestArray extends TestCase {

    @Test
    public void testArray(){
       int[] a  = new int[2];
       a[0] = 1;
       a[1] = 2;

       Object obj = a;
        if (obj instanceof int[]){
            System.out.println("obj is int[]");
            int[] b = (int[])obj;
            for (int i = 0; i < b.length; i++){
                System.out.println(i+" "+b[i]);

            }
        }else{
            System.out.println("obj is not int[]");
        }

    }
}
