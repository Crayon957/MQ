package com.example.demo.udpNetty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author menghm.
 * @description
 * @date 2019/9/24.
 */
@Component
public class NettybootServerInitConfig implements CommandLineRunner {
    @Value("${netty.port}")
    private int port;
    @Autowired
    private Server server;

    @Override
    public void run(String... strings) throws Exception {
        server.run(port);
    }
}
