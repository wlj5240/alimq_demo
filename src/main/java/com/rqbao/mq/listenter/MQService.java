package com.rqbao.mq.listenter;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.bean.ConsumerBean;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.rqbao.mq.common.config.Global;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by ricky on 2017/6/8.
 *MQ消息生产功能，可能附加一些其他功能
 */
@Service
public class MQService implements InitializingBean{
    private static final Logger logger = Logger.getLogger(MQService.class);
    @Autowired
    private ProducerBean producer;
    @Autowired
    private ConsumerBean consumer;

    /**
     * 发送普通MQ消息
     * @param TAG 业务tag  tag
     * @param msg 需传递对应接口需要参数，
     * @return
     */
    public String sendMQMsg(String TAG,String msg){
        Message message = new Message(Global.getConfig("topic"), TAG,msg.getBytes());
        SendResult sendResult = producer.send(message);
        if (sendResult != null) {
            System.out.println(new Date() + " Send mq message success! Topic is:" + Global.getConfig("topic") + "msgId is: " + sendResult.getMessageId());
        }
        return sendResult.toString();
    }
    /**
     * 发送延迟MQ消息
     * @param TAG 业务tag  tag将使用CGUTILS中的存管接口变量
     * @param msg 需传递对应接口需要参数，格式待考究
     *        delayTime 延迟时间  3000--->3s
     * @return
     */
    public String sendDelayMQMsg(String TAG,String msg,long delayTime){
        Message message = new Message(Global.getConfig("topic"), TAG,msg.getBytes());
        message.setStartDeliverTime(System.currentTimeMillis() + delayTime);
        SendResult sendResult = producer.send(message);
        if (sendResult != null) {
            System.out.println(new Date() + " Send mq message success! Topic is:" + Global.getConfig("topic") + "msgId is: " + sendResult.getMessageId());
        }
        return sendResult.toString();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        String result=producer.getProperties().toString();
        logger.debug("消息队列工具类"+result);
    }
}
