package com.rqbao.mq.listenter;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.order.OrderProducer;
import com.rqbao.mq.common.config.Global;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * Created by baiguantao on 2017/6/8.
 *MQ消息生产功能，可能附加一些其他功能
 */
@Service
public class MQOrderService implements InitializingBean{
    private static final Logger logger = Logger.getLogger(MQOrderService.class);
    @Autowired
    MQOrderProducer mqOrderProducer;

    public  void  send(){
        OrderProducer producer=mqOrderProducer.getOrderProducer();

    }
    /**
     * 发送顺序MQ消息
     * @param TAG 业务tag  tag将使用CGUTILS中的存管接口变量
     * @param msg 需传递对应接口需要参数，格式待考究
     *        delayTime 延迟时间  3000--->3s
     * @return
     */
    public String sendOrderMQMsg(String TAG,String msg){
        OrderProducer producer=mqOrderProducer.getOrderProducer();
        Message message = new Message(Global.getConfig("ORDER_TOPIC"), TAG,msg.getBytes());
        // 设置代表消息的业务关键属性，请尽可能全局唯一。
        String orderId = "rqb_" + UUID.randomUUID().toString().replaceAll("-","");
        message.setKey(orderId);
        // 分区顺序消息中区分不同分区的关键字段，sharding key于普通消息的key是完全不同的概念。
        // 全局顺序消息，该字段可以设置为任意非空字符串。
        String shardingKey = String.valueOf(orderId);
        SendResult sendResult = producer.send(message, shardingKey);
        if (sendResult != null) {
            System.out.println(new Date() + " Send mq message success! Topic is:" + Global.getConfig("topic") + "msgId is: " + sendResult.getMessageId());
        }
        return sendResult.toString();
    }
    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
