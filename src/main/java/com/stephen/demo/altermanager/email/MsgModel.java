package com.stephen.demo.altermanager.email;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;


/**
 * 消息
 * 
 * @since 1.0.0
 * @author wuzhaohai
 * @date 2017年9月5日 下午5:12:15
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MsgModel implements Serializable
{

    private static final long serialVersionUID = -3612813311468375880L;

    /**
     * 待发送
     */
    public static final int STATUS_WAIT = 0;
    /**
     * 发送成功
     */
    public static final int STATUS_SUCCESS = 1;
    /**
     * 发送失败
     */
    public static final int STATUS_FAIL = 2;

    /**
     * 消息类型：popo
     */
    public static final int MSGTYPE_POPO = 10;
    /**
     * 消息类型：popo服务号
     */
    public static final int MSGTYPE_POPO_PUBLISH = 11;
    /**
     * 消息类型：email
     */
    public static final int MSGTYPE_EMAIL = 20;
    /**
     * 消息类型：yixin
     */
    public static final int MSGTYPE_YIXIN = 30;
    /**
     * 消息类型：android app
     */
    public static final int MSGTYPE_ANDROID_APP = 40;
    /**
     * 消息类型：android app
     */
    public static final int MSGTYPE_IOS_APP = 41;
    /**
     * 消息类型：sms
     */
    public static final int MSGTYPE_SMS = 50;


    private long id;

    private String msgCode;

    private int msgType;

    private String msgGroup;

    private String receivers;

    private String content;

    private long receiveTime;

    private long sendTime;

    /**
     * 状态：0-待发送 1-发送成功 2-发送失败
     */
    private int status;

    /**
     * 重试次数
     */
    private int retryCount;

    /**
     * 其他信息
     */
    private Map<String, Object> extraInfo;



}
