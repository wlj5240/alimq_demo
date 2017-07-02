package com.rqbao.mq.controller;


import com.rqbao.mq.listenter.MQOrderService;
import com.rqbao.mq.listenter.MQService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by baiguantao on 2017/6/15.
 */
@Controller
public class IndexController {
    private static final Logger _log = Logger.getLogger(IndexController.class);
    @Autowired
    MQService mqService;
    @Autowired
    MQOrderService mQOrderService;
    @RequestMapping("/index")
    @ResponseBody
    public  String index(){
        //mqService.sendMQMsg(MQConstant.CGUPINVEST, MQUtils.CGUPINVEST("00171b79728b466fb48ae70f42ca1119"));
        //还款
        //mqService.sendMQMsg(MQConstant.BACKMONEY_SUCCESS, MQUtils.BACKMONEY_SUCCESS("","e6be6a6d5c7947cb8a5fe6bd3cdac3b9"));
        //投资成功
        //mqService.sendMQMsg(MQConstant.INVEST_SUCCESS, MQUtils.INVEST_SUCCESS("2017-06-15","12.5","457","0b54ab2d38df4aabbe35a471d2ea0589"));
        //提现成功
        mqService.sendMQMsg("tag", "content-body");
        mQOrderService.sendOrderMQMsg("ricky","12345");
        return "success";
    }
}
