package com.wpg.netty_tcp.util;

import com.wpg.netty_tcp.entity.Attend;
import com.wpg.netty_tcp.entity.AttendJson;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class AttendUtil {

    public static AttendJson transform(String msg) {
        //去除头部信息
        String s = msg.substring(5);
        log.info(s);
        //分割字符串
        String[] strAttend = s.split("[@]");
        if (strAttend.length == 2) {
            AttendJson attendJson = new AttendJson();
            //设置员工编号
            attendJson.setNumber(strAttend[0]);
            //继续分割字符串
            String[] split = strAttend[1].split("[&]");

            List<Attend> list = new LinkedList<>();
            for (int i = 1; i < split.length; i++){
                //分割字符串，构建对象
                String[] split1 = split[i].split("[:]");
                log.info(Arrays.toString(split1));
                Attend attend = new Attend();
                attend.setTime(split1[0]);
                if(split1.length==3 ){
                    if("0".equals(split1[1])){
                        attend.setStatus1("上午迟到");
                    }else if("1".equals(split1[1])){
                        attend.setStatus1("上午正常");
                    }else if("2".equals(split1[1])){
                        attend.setStatus1("上午早退");
                    }else if("100".equals(split1[1])){
                        attend.setStatus1("上午请假");
                    }else if ("".equals(split1[1])){
                        attend.setStatus1("上午缺勤");
                    }
                    if("0".equals(split1[2])){
                        attend.setStatus2("下午迟到");
                    }else if("1".equals(split1[2])){
                        attend.setStatus2("下午正常");
                    }else if("2".equals(split1[2])){
                        attend.setStatus2("下午早退");
                    }else if("100".equals(split1[2])){
                        attend.setStatus2("下午请假");
                    }
                }else if(split1.length==1){
                    attend.setStatus1("上午缺勤");
                    attend.setStatus2("下午缺勤");
                } else if(split1.length==2){
                    if("0".equals(split1[1])){
                        attend.setStatus1("上午迟到");
                    }else if("1".equals(split1[1])){
                        attend.setStatus1("上午正常");
                    }else if("2".equals(split1[1])){
                        attend.setStatus1("上午早退");
                    }else if("100".equals(split1[1])){
                        attend.setStatus1("上午请假");
                    }
                    attend.setStatus2("下午缺勤");
                }
                list.add(attend);
            }
            attendJson.setValues(list);
            return attendJson;
        } else {
            log.info("数据异常");
        }

        return null;
    }
}
