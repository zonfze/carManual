package com.viglle.carmanual.utils.caches;

/**
 * Created by Administrator on 2016/6/7.
 */
public class DataCahe {
    public static boolean isCacheEnable=false;

    public static void putData(String key,String json){
        if(!isCacheEnable){
            return;
        }
//        SharedPrefUtil.getInstance()
        /**
         * id,uid,ver,key,data
         */
    }
    public static String getData(String key,String defValue){
        if (!isCacheEnable){
            return defValue;
        }
        return defValue;
    }
}
