package com.bs.demo.common.local;


import com.bs.demo.common.TApplication;

/**
 * Description: 存储布尔类型
 */
public class LocalSetting {

    public static boolean isSetting(String key) {
        try {
            Boolean is= TApplication.Companion.getInstance().getPreference().getBoolean(key,false);
            return is;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static void settting(boolean b,String key){
        TApplication.Companion.getInstance().getPreference().putBoolean(key,b).commit();
    }
}
