package com.wpg.netty_tcp.tcpNetty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Server {

    @Autowired
    private ServerHandle serverHandle;

    /**
     * 配置服务器
     * */
    public void run(int port) throws Exception{
        //创建监听线程组，监听客户端的请求
        EventLoopGroup acceptorGroup = new NioEventLoopGroup();
        //创建处理客户端相关操作的的线程组，负责处理与客户端的数据tongx
        EventLoopGroup clientGroup = new NioEventLoopGroup();
        //服务器启动相关配置信息
        ServerBootstrap bootstrap = new ServerBootstrap();

        //绑定线程组
        bootstrap.group(acceptorGroup, clientGroup);
        //设定通讯模式为NIO，同步非阻塞
        bootstrap.channel(NioServerSocketChannel.class);
        //设定缓存区大小，缓存区的单位是字节
        bootstrap.option(ChannelOption.SO_BACKLOG,1024);
        //SO_SNDBUF 发送缓冲区（写数据），SO_RCVBUF(接受缓存区）
        bootstrap.option(ChannelOption.SO_RCVBUF,16*1024)
                .option(ChannelOption.SO_RCVBUF, 16*1024)
                .option(ChannelOption.SO_KEEPALIVE,true);

        bootstrap.childHandler(serverHandle);

        //绑定端口，开始监听
        ChannelFuture channelFuture = bootstrap.bind(port).sync();
        if(channelFuture.isSuccess()){
            log.info("netty 服务端启动完毕 .....使用端口为：{}", port);
        }
    }
}
