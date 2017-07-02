package com.rqbao.mq.listenter;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by baiguantao on 2017/6/7.
 * MQ消费类。
 * MQ监听工具处理类
 * 这里是想更加tag区分不同业务操作
 * 比如ACCOUNT_OPERATE
 * <p>
 * 注意对业务进行防重处理。
 */
public class MQListener implements MessageListener {
    private static final Logger logger = Logger.getLogger(MQListener.class);

    /**
     * 具体消费类
     */
    @Transactional
    @Override
    public Action consume(Message message, ConsumeContext consumeContext) {

        String msgtxt = "";
        JSONObject MQjson = new JSONObject();
        try {
            msgtxt = new String(message.getBody(), "UTF-8");

            String tag = message.getTag();//更加tag来发起不同接口的调用
            //TODO 防重未处理！！！
            logger.info(new Date() + "消息Tag:" + tag + "消息Id:" + message.getMsgID() + "消息内容：" + msgtxt);
            MQjson = MQUtils.getJsonObject(msgtxt);
            if (MQjson.containsKey("error")) {
                //消费失败
                logger.error("消费失败:" + MQjson.get("error") + ",参数：" + msgtxt);
                return Action.ReconsumeLater;
            }

        } catch (Exception e) {
            if ((e instanceof DuplicateKeyException) == true) {
                logger.error("数据库校验错误：返利已经处理过了" + e.getMessage());
                //return Action.CommitMessage;
            } else {
                logger.error("消息队列异常：" + e.getMessage(), e);
                throw new RuntimeException("消费异常：" + e);
            }
            //sendRicky("消息队列异常:" + MQjson.toString());
        } finally {

        }

        //如果想测试消息重投的功能,可以将Action.CommitMessage 替换成Action.ReconsumeLater
        return Action.CommitMessage;
    }


}
