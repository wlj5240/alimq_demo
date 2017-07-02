package com.rqbao.mq.listenter;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.order.ConsumeOrderContext;
import com.aliyun.openservices.ons.api.order.MessageOrderListener;
import com.aliyun.openservices.ons.api.order.OrderAction;
import com.aliyun.openservices.ons.api.order.OrderConsumer;

import java.util.Properties;

/**
 * Created by baiguantao on 2017/7/2.
 */
public class MQOrderConsumer {
    private  Properties consumerProperties = new Properties();
    private  OrderConsumer consumer;

    public MQOrderConsumer(Properties consumerProperties) {
        this.consumerProperties = consumerProperties;
        consumer = ONSFactory.createOrderedConsumer(consumerProperties);
        consumer.subscribe(consumerProperties.get("ORDER_TOPIC").toString(), consumerProperties.get("TAG").toString(),  new MessageOrderListener() {
            @Override
            public OrderAction consume(final Message message, final ConsumeOrderContext context) {
                System.out.println(message);
                return OrderAction.Success;
            }
        });
    }
    public  void  start(){
        new MQOrderProducer(consumerProperties);
        consumer.start();
    }
    public  void  shutdown(){
        consumer.shutdown();
    }

    public Properties getConsumerProperties() {
        return consumerProperties;
    }

    public void setConsumerProperties(Properties consumerProperties) {
        this.consumerProperties = consumerProperties;
    }

    public OrderConsumer getConsumer() {
        return consumer;
    }

    public void setConsumer(OrderConsumer consumer) {
        this.consumer = consumer;
    }
}
