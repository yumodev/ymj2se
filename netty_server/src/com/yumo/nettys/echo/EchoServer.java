package com.yumo.nettys.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }
    public static void main(String[] args) throws Exception {
        int port = 8080;
        if (args.length == 1) {
            port = Integer.parseInt(args[0]);        //1
        }
        new EchoServer(port).start();                //2
    }

    public void start() throws Exception {
        //创建EventLoop
        NioEventLoopGroup group = new NioEventLoopGroup(); //3
        try {
            //创建ServerBootstrap
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)                                //4
                    .channel(NioServerSocketChannel.class)        // 指定NIO的传输Channel
                    .localAddress(new InetSocketAddress(port))    //指定socket地址使用的端口号
                    .childHandler(new ChannelInitializer<SocketChannel>() { //7 添加EchoServerHandler到Channel的ChannelPipeLine
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            ch.pipeline().addLast(
                                    new EchoServerHandler());
                        }
                    });

            ChannelFuture f = b.bind().sync();            //绑定服务器， sync等待服务器关闭
            System.out.println(EchoServer.class.getName() + " started and listen on " + f.channel().localAddress());
            f.channel().closeFuture().sync();            // 关闭channel和块，知道它被关闭
        } finally {
            group.shutdownGracefully().sync();            //释放所有资源
        }
    }
}
