package com.stephen.demo.altermanager.controller;

import com.stephen.demo.altermanager.email.MsgModel;
import com.stephen.demo.altermanager.email.MsgSendUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="shishouchao@corp.netease.com">shouchao.shi</a>
 * @version 1.0.0
 * @date 2022/03/29 9:35
 * @Description
 */
@Slf4j
@RestController
public class HelloController {

    @RequestMapping("alertmanager/hook")
    public Object hook(@RequestBody String body) {
        log.info("接收到告警信息：{}",body);
        String email = "shishouchao@corp.netease.com";

        MsgModel msg = new MsgModel();
        msg.setReceivers(email);
        msg.setMsgType(MsgModel.MSGTYPE_EMAIL);
        msg.setContent("测试alertmanager/hook发送邮件");
        msg.setMsgGroup("测试");
        MsgSendUtils.sendMsg(msg);

        return "Hello altermanager hook";
    }

}
