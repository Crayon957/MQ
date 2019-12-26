package com.wpg.netty_udp.udpNetty;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.wpg.netty_udp.entity.Student;
import com.wpg.netty_udp.entity.StudentJson;
import com.wpg.netty_udp.mqtt.MqttTemplate;
import com.wpg.netty_udp.util.StudentUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @author dingsl.
 * @description
 * @date 2019/12/12.
 */
@Slf4j
@Service
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Autowired
    private MqttTemplate mqttTemplate;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //获取读取的数据，通过content()获得
        DatagramPacket packet = (DatagramPacket) msg;

        String hexDump = packet.content().toString(CharsetUtil.UTF_8);
        System.out.println("客户端："+hexDump);

        String formatDateTime = DateUtil.formatDateTime(DateUtil.date());

        log.info(formatDateTime);
        ctx.write("server :收到 "+formatDateTime);
        ctx.flush();
        //处理数据
        HashMap<String, Student> stus = StudentUtil.transform(hexDump);

        Set<String> set = stus.keySet();
        for (String num:set) {
            Student student = stus.get(num);
            StudentJson studentJson = new StudentJson();
            studentJson.setNumber(num);
            studentJson.setValues(student);

            //将对象处理为JSON字符串
            String mqttStr = JSONUtil.toJsonStr(studentJson);
            log.info("------"+mqttStr);
            //向mqtt发送数据
            mqttTemplate.publish(num+"/result",mqttStr);
        }


        //写操作自动释放缓存，避免内存溢出的问题。
        ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("服务端已收到信息，时间为："+ LocalDate.now(),CharsetUtil.UTF_8),packet.sender()));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        cause.printStackTrace();
    }
}
