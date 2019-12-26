package com.wpg.netty_udp.udpNetty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dingsl.
 * @description
 * @date 2019/12/12.
 */
@Slf4j
@Component
public class Server {

    @Autowired
    private ServerHandler serverHandler;

    public void run(int port) throws InterruptedException{
        //处理客户端相关操作线程组，负责处理与客户端的数据通讯
        EventLoopGroup group = new NioEventLoopGroup();
        //服务器启动相关配置信息
        Bootstrap bootstrap = new Bootstrap();

        //绑定线程组
        bootstrap.group(group);
        //因用的是udp协议，所以要用NioDatagramChannel来创建
        bootstrap.channel(NioDatagramChannel.class);
        //支持广播
        bootstrap.option(ChannelOption.SO_BROADCAST, true);
        bootstrap.handler(serverHandler);
        //绑定监听端口，开始监听
        log.info("netty 服务端启动完毕 ----使用的端口为：{}",port);
        bootstrap.bind(port).sync().channel().closeFuture().await();
    }
}
