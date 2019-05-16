package com.yumo.nettyc.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class EchoClient {
    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        String host = "127.0.0.1";
        int port = 8080;
        if (args.length == 2) {
            host = args[0];
            port = Integer.parseInt(args[1]);
        }


        new EchoClient(host, port).start();
    }

    public void start() throws Exception {
        //创建EventLoop
        EventLoopGroup group = new NioEventLoopGroup(); //3
        try {
            //创建ServerBootstrap
            Bootstrap b = new Bootstrap();
            b.group(group)                                //4
                    .channel(NioSocketChannel.class)        // 指定NIO的传输Channel
                    .remoteAddress(new InetSocketAddress(host, port))    //指定socket地址使用的端口号
                    .handler(new ChannelInitializer<SocketChannel>() { //7 添加EchoServerHandler到Channel的ChannelPipeLine
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            ch.pipeline().addLast(
                                    new EchoClientHandler());
                        }
                    });

            ChannelFuture f = b.connect().sync();            //绑定服务器， sync等待服务器关闭
            System.out.println(EchoClient.class.getName());
            f.channel().closeFuture().sync();            // 关闭channel和块，知道它被关闭
        } finally {
            group.shutdownGracefully().sync();            //释放所有资源
        }
    }
}
