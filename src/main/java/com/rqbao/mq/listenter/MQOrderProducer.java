package com.rqbao.mq.listenter;

import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.order.OrderProducer;

import java.util.Properties;

/**
 * Created by baiguantao on 2017/7/2.
 */
public class MQOrderProducer {
    private  Properties producerProperties = new Properties();
    private  OrderProducer producer;

    public MQOrderProducer(Properties producerProperties) {
        this.producerProperties = producerProperties;
        producer = ONSFactory.createOrderProducer(producerProperties);
    }
    public OrderProducer getOrderProducer(){
        return  producer;
    }
    public  void  start(){
        new MQOrderProducer(producerProperties);
        producer.start();
    }
    public  void  shutdown(){
        producer.shutdown();
    }

    public Properties getProducerProperties() {
        return producerProperties;
    }

    public void setProducerProperties(Properties producerProperties) {
        this.producerProperties = producerProperties;
    }

    public OrderProducer getProducer() {
        return producer;
    }

    public void setProducer(OrderProducer producer) {
        this.producer = producer;
    }
}
