package com.stephen.demo.altermanager.email;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


/**
 * 消息发送工具类
 * 
 * @since 1.0.0
 * @author wuzhaohai
 * @date 2017-9-26 下午03:16:58
 * 
 */
public class MsgSendUtils
{

    private static Log log = LogFactory.getLog(MsgSendUtils.class);




    /**
     * 发送消息
     * 
     * @param msg
     * @return
     * 
     * @since 1.0.0
     */
    public static boolean sendMsg(MsgModel msg) {
        if (msg == null) {
            log.warn("消息不能为空！");
            return false;
        }

        boolean res = false;
        String result = null;
        try {
            String paramStr = getParamStr(msg);
            // String url = getMsgUrl(msg);
            String url = " http://192.168.44.115:9340/msg/msg/sendEmailMsg";
            if (url == null || "".equals(url)) {
                log.warn("找不到消息url！" + msg);
                return res;
            }

            result = HttpCaller.sendPost(url, paramStr);

            JSONObject resultObj = JSON.parseObject(result);
            if (resultObj != null && "200".equals(resultObj.getString("code"))) {
                JSONObject data = resultObj.getJSONObject("data");
                if (data.getBooleanValue("res")) {
                    res = true;
                    msg.setMsgCode(data.getString("msgCode"));
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        log.info("发送消息[" + msg + "], res->" + res);


        return res;
    }


    private static String getParamStr(MsgModel msg) throws UnsupportedEncodingException {
        String msgStr = JSON.toJSONString(msg);
        String paramStr = "data=" + URLEncoder.encode(msgStr, "UTF-8");
        return paramStr;
    }

}
