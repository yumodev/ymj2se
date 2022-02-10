package com.yumodev.patten.proxy;

import junit.framework.TestCase;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

/**
 *  * [你真的完全了解Java动态代理吗？看这篇就够了](https://www.jianshu.com/p/95970b089360)
 */
public class ProxyTest extends TestCase {



    public void test1(){
        Object[] elements = new Object[1000];
        for (int i = 0; i < elements.length; i++){
            int value = i + 1;
            InvocationHandler handler = new TraceHandler(value);
            Object proxy = Proxy.newProxyInstance(null, new Class[]{Comparable.class}, handler);
            elements[i] = proxy;
        }

        Integer key = new Random().nextInt(elements.length)+1;
        int result = Arrays.binarySearch(elements, key);
        if (result >= 0) System.out.println(elements[result]);
    }

    class TraceHandler implements InvocationHandler{

        Object mTarget;

        public TraceHandler(Object t){

            mTarget = t;
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            StringBuilder sb = new StringBuilder();
            sb.append(mTarget.toString());
            sb.append(".").append(method.getName()).append("C");
            if (args != null){
                for (Object o : args){
                    sb.append(o.toString());
                }
            }
            sb.append(")");
            return method.invoke(mTarget, args);
        }
    }


    public interface ILawsuit{
        void submit();//提交申请
        void burden();//举证
        void defend();//辩护
        void finish();//诉讼完成
    }

    public class XM implements ILawsuit{

        @Override
        public void submit() {
            System.out.println("submit");
        }

        @Override
        public void burden() {
            System.out.println("burden");
        }

        @Override
        public void defend() {
            System.out.println("defend");
        }

        @Override
        public void finish() {
            System.out.println("finish");
        }
    }

    public void testLawSuit(){
        XM xmLaw = new XM();
        ILawsuit lawyer = (ILawsuit)Proxy.newProxyInstance(xmLaw.getClass().getClassLoader(), new Class[]{ILawsuit.class}, new InvocationHandler(){
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if ("submit".equals(method.getName())){
                    System.out.println("proxy:"+method.getName());
                }
                return method.invoke(xmLaw, args);
            }
        });
        lawyer.submit();
        lawyer.burden();
        lawyer.defend();
        lawyer.finish();
    }
}
