package com.yumodev.base;

import junit.framework.TestCase;

/**
 * Created by yumodev on 17/9/17.
 * 字符串测试
 */

public class StringTest extends TestCase {

    public void testNew(){
        String nameA = "a";
        String nameB = nameA;

        equals(nameA == nameB);
    }
}
