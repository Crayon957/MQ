package com.wpg.netty_udp.udpNetty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author dingsl.
 * @description
 * @date 2019/12/12.
 */

@Component
public class NettyServerConfig implements CommandLineRunner {
    @Value("${netty.port}")
    private int port;
    @Autowired
    private Server server;

    @Override
    public void run(String... args) throws Exception {
        server.run(port);
    }
}
