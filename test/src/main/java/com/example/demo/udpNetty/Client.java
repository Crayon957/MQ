package com.example.demo.udpNetty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author menghm.
 * @description
 * @date 2019/9/23.
 */
@Log4j2
@Component

public class Client {
    public void run(int port) throws Exception{

        EventLoopGroup group  = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group);
            bootstrap.channel(NioDatagramChannel.class);
            bootstrap.option(ChannelOption.SO_BROADCAST, true);
            bootstrap.handler(new ClientHandler());
            Channel ch = bootstrap.bind(0).sync().channel();
            //向网段内的所有机器广播UDP消息。
            Scanner s=null;
            while(true){
                s=new Scanner(System.in);
                System.out.println("输入向服务器发送的信息(enter 'exit' for close client)");
                String line=s.nextLine();
                if("exit".equals(line)) {
                    //关闭连接
                    ch.writeAndFlush(Unpooled.copiedBuffer(line.getBytes("UTF-8")))
                            .addListener(ChannelFutureListener.CLOSE);
                }
                ch.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(line, CharsetUtil.UTF_8), new InetSocketAddress("255.255.255.255",port))).sync();
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (Exception e) {
            group.shutdownGracefully();
        }
    }
   public static void main(String [] args) throws Exception{
        int port = 8087;
        new Client().run(port);
    }
}
