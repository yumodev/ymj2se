package com.yumodev.thread.threadpattern.thread_per_message;

/**
 * Created by yumodev on 18/3/5.
 */

public class TestThreadPerMessage {

    public static class Helper{
        public void handler(int count, char c){
            System.out.println(" hanlder("+count+","+ c+" ) begin");

            for (int i =0; i < count;i++){
                slowly();
                System.out.print(c);
            }

            System.out.println("");
            System.out.println(" hanlder("+count+","+ c+" ) end");
        }

        private void slowly(){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class Host{
        private final Helper helper = new Helper();
        public void request(final int count, final char c){
            System.out.println(" request("+count+","+ c+" ) begin");
            new Thread(){
                @Override
                public void run() {
                    helper.handler(count, c);
                }
            }.start();

            System.out.println(" request("+count+","+ c+" ) end");
        }
    }

    public static void main(String[] args){
        System.out.println("main Begin");
        Host host = new Host();
        host.request(10, 'A');
        host.request(10, 'B');
        host.request(10, 'C');
        System.out.println("main End");
    }
}
