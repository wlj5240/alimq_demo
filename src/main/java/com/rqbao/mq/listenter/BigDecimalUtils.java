package com.rqbao.mq.listenter;

import java.math.BigDecimal;

/**
 * Created by baiguantao on 2017/6/9.
 * 工具类
 */
public class BigDecimalUtils {
    /**
     * String 转Bigdecimal
     */
    public  static BigDecimal strToBigDecimal(Object str){
       return BigDecimal.valueOf(Long.valueOf(str.toString()));
    }
    /**
     * 保留2位精度  进行四舍五入  最后转为整数形式
     * 比如10.234---》1023
     * 比如10.235---》1024
     * */
    public  static BigDecimal IntBigDecimal(BigDecimal bigDecimal){
        return bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP).movePointRight(2);
    }
    /**
     * 保留2位精度  不进行四舍五入  最后转为整数形式
     * 比如10.234---》1023
     * */
    public  static BigDecimal IntBigDecimal2(BigDecimal bigDecimal){
        return bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP).movePointRight(2);
    }

    /**
     * 大于0
     * */
    public  static boolean MoreThanZero(BigDecimal bigDecimal){
        if (null==bigDecimal) {
            return false;
        }
        if (bigDecimal.compareTo(BigDecimal.ZERO)==1) {
            return true;
        }
        return false;
    }
    /**
     * 大于等于 指定数
     * temnum  临时变量
     * compareNum  指定数
     * */
    public  static boolean MoreThanCompareNum(BigDecimal temnum,BigDecimal compareNum){
        if (null==temnum||null==compareNum) {
            return false;
        }
        if (temnum.compareTo(compareNum)==1||temnum.compareTo(compareNum)==0) {
            return true;
        }
        return false;
    }
}
