package com.cn.wrapper;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.MapWrapper;

import java.util.Map;

/**
 * @description:
 * @author: helisen
 * @create: 2021-02-19 15:52
 **/
public class CamelMapWrapper extends MapWrapper {
    public CamelMapWrapper(MetaObject metaObject, Map<String, Object> map) {
        super(metaObject, map);
    }

    @Override
    public String findProperty(String name, boolean useCamelCaseMapping) {
        //如果字段名中含有下划线_  ，就会把下划线转换为驼峰格式
        if(useCamelCaseMapping && (name.charAt(0) >= 'A' && name.charAt(0) <= 'Z') || name.indexOf("_") >= 0) {
            return underlineToCamelhump(name);
        }
        return name;
    }

    /**
     * 将下划线风格替换为驼峰风格
     * @param inputString
     * @return
     */
    private String underlineToCamelhump(String inputString) {
        StringBuilder sb = new StringBuilder();
        boolean nextUpperCase = false;
        for (int i = 0; i < inputString.length(); i++) {
            char c = inputString.charAt(i);
            if(c == '_') {
                if(sb.length() > 0) {
                    nextUpperCase = true;
                }
            }else {
                if(nextUpperCase) {
                    sb.append(Character.toUpperCase(c));
                    nextUpperCase = false;
                }else {
                    sb.append(Character.toLowerCase(c));
                }
            }
        }
        return sb.toString();
    }
}
