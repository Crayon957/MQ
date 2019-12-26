package com.wpg.netty_tcp.tcpNetty;

import cn.hutool.json.JSONUtil;
import com.wpg.netty_tcp.entity.AttendJson;
import com.wpg.netty_tcp.mqtt.MqttTemplate;
import com.wpg.netty_tcp.util.AttendUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.time.LocalDateTime;

@Slf4j
@Service
@ChannelHandler.Sharable
public class ServerHandle extends SimpleChannelInboundHandler<ByteBuf> {

    @Autowired
    private MqttTemplate mqttTemplate;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf byteBuf) throws Exception {
        //处理得到的tcp的字节流数据
        String msg = byteBuf.toString(Charset.forName("UTF-8"));

        //打印获得的数据
        System.out.println("客户端： "+msg);
        //处理数据
        AttendJson json = AttendUtil.transform(msg);
        //将处理后的数据向MQTT转发
        String mqttStr = JSONUtil.toJsonStr(json);
        log.info("------"+mqttStr);
        //向mqtt发送数据
        mqttTemplate.publish(json.getNumber()+"/attendance ",mqttStr);
        //写操作 自动释放缓存，避免内存溢出问题
        //ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("服务端已收到消息，时间为：" + LocalDateTime.now(), CharsetUtil.UTF_8), packet.sender()));

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        cause.printStackTrace();
    }


}
