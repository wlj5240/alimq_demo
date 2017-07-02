package com.rqbao.mq.listenter;

import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

/**
 * Created by baiguantao on 2017/6/9.
 * 现在消息队列是根据tag来区分消息类型
 * 参照MQConstant工具类
 * MQ消息内容工具类
 *
示例：发送消息
@Autowired
MQService mqService;
mqService.sendMQMsg(MQConstant.ACCOUNT_OPERATE, MQUtils.REWARD_MSG("sub1","222","PC"));
 */
public class MQUtils {
    private static final Logger logger = Logger.getLogger(MQUtils.class);
    /**
     * //TODO 暂时性的  待完成
     * 注册成功相关参数拼接
     * "{'subject':'%s','user_id':'%s'}"
     *  @Date 2017-06-26
     *  @author  ricky
     * */
    public  static String REGESTER_SUCCESS(String subject,String userId,
                                         String investId){
        String REGESTER_SUCCESS = "{"
                +"\"userId\":\""+userId+"\","
                +"\"investId\":\""+investId+"\""
                +"}";
        logger.info("注册成功相关参数格式："+REGESTER_SUCCESS);
        return REGESTER_SUCCESS;
    }

    /**
     * 2017-06-09
     * 公共解析实体
     * {
     *     "key":"value",
     *     "key2":"{
     *         "innerkey":"innervalue"
     *     }"
     * }
     * msg：消息字符串
     * key：这里对应的是key2
     * classz：对应实体
     * */
    public static Object analyze(JSONObject MQjson,String key,Class classz){
        //解析
        return JSONObject.toBean(JSONObject.fromObject(MQjson.get(key)),classz);
    }

    /**
     * 获取json对象
     * @param msg
     * @return
     */
    public static JSONObject getJsonObject(String msg){
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject=JSONObject.fromObject(msg);
        }catch (Exception e){
            logger.error("解析错误");
            JSONObject jsonObjecterror=new JSONObject();
            jsonObjecterror.put("error","消费时，解析字符串错误");
            return jsonObjecterror;
        }
        return jsonObject;
    }


    public static void main(String[] args) {
       // ACCOUNT_OPERATE("11",BigDecimal.TEN,"add","sub",new AccountRecords());
    }
}
