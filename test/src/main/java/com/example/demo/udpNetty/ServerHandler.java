package com.example.demo.udpNetty;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.example.demo.entity.Message;
import com.example.demo.mqtt.MqttTemplate;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author menghm.
 * @description
 * @date 2019/9/23.
 */
@Slf4j
@Service
public class ServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    @Autowired
    private MqttTemplate mqttTemplate;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
        // 获取读取的数据 通过content()来获取消息内容
        String hexDump = packet.content().toString(CharsetUtil.UTF_8);
       /* ByteBuf req = packet.content();
        String hexDump = ByteBufUtil.hexDump(req);*/
        System.out.println("来自客户端的消息:" + hexDump);
        String formatDateTime = DateUtil.formatDateTime(DateUtil.date());
        Message message = new Message(hexDump, formatDateTime);
        String mqttStr = JSONUtil.toJsonStr(message);
        //向mqtt发送数据
        mqttTemplate.publish("UDP/TEST", mqttStr);
        // 写操作自动释放缓存，避免内存溢出问题。
        ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("服务端已收到消息，时间为：" + LocalDateTime.now(), CharsetUtil.UTF_8), packet.sender()));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        cause.printStackTrace();
    }


}
