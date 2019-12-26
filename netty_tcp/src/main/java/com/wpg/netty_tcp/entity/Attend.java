package com.wpg.netty_tcp.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wpg.netty_tcp.entity.eunms.Status1Eunm;
import lombok.Data;

import java.io.Serializable;

@Data
public class Attend implements Serializable {

    /**
     *时间
     * */
    private String time;

    /**
     *上午
     * */
    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private String status1;

    /**
     *下午
     * */
    private String status2;

}
