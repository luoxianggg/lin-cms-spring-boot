package io.github.talelin.latticy.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class JsonUtil {

    /***
     * 将对象转换为HashMap
     *
     * @param object
     * @return
     */
    public static Map<String, Object> toHashMap(Object obj) {
        if (obj == null) {
            return null;
        }

        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Field[] declaredFields = obj.getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
        } catch (Exception e) {

        }

        return map;
    }

    /**
     * 将HashMap转换为对象
     *
     * @param map
     * @param class1
     * @param <T>
     * @return
     */
    public static <T> T MaptoModel(Map<String, Object> map, Class<T> class1) {
        //因为beanutils不支持字符串转换成其他对象类型。要想转换需要注册一个转换器
        ConvertUtils.register(new Converter() {
            public Object convert(Class type, Object value) {
                try {
                    return new BigDecimal(value.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }, BigDecimal.class);
        ConvertUtils.register(new Converter() {
            public Object convert(Class type, Object value) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    return simpleDateFormat.parse(value.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }, Date.class);
        T bean = null;
        try {
            bean = class1.newInstance();
            BeanUtils.populate(bean, map);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 将Map中某个List转换为List<对象>
     *
     * @param requestMap
     * @param listName
     * @param class1
     * @param <T>
     * @return
     */
    public static <T> List<T> getMapList(Map<String, Object> requestMap, String listName, Class<T> class1) {
        List<T> returnBeans = new ArrayList<>();
        if (StringUtil.hasKeyValue(requestMap, listName)) {
            List<Object> list = StringUtil.getMapKeyList(requestMap, listName);
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = toHashMap(list.get(i));
                returnBeans.add(MaptoModel(map, class1));
            }
        }
        return returnBeans;
    }


}
