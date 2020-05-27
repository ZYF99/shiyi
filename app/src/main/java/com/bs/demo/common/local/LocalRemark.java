package com.bs.demo.common.local;


import com.bs.demo.common.TApplication;

/**
 * Description: 临时字段存储
 */
public class LocalRemark {
    public final static String KEY_TOKEN ="remark_token";
    public static String getRemark(String key) {
        try {
            String is= TApplication.Companion.getInstance().getPreference().getString(key,"");
            return is;
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }
    public static void remark(String content,String key){
        TApplication.Companion.getInstance().getPreference().putString(key,content).commit();
    }
}
