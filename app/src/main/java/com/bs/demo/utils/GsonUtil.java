package com.bs.demo.utils;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Description: gson转换处理
 */
public class GsonUtil {
    /**
     * 字符串转List bean
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> parseStr2List(String json, Class clazz) {
        Type type = new ParameterizedTypeImpl(clazz);
        List<T> list =  new Gson().fromJson(json, type);
        return list;
    }

    private static class ParameterizedTypeImpl implements ParameterizedType {
        Class clazz;

        public ParameterizedTypeImpl(Class clz) {
            clazz = clz;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{clazz};
        }

        @Override
        public Type getRawType() {
            return List.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }


}
