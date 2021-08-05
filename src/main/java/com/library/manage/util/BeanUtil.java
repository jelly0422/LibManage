package com.library.manage.util;

import lombok.SneakyThrows;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BeanUtil {

    private BeanUtil(){}

    /**
     * 将Entity转为DTO，变量名称需一致
     * @param dto DTO class
     * @param entity
     * @param <T>
     * @param <D>
     * @return DTO
     */
    @SneakyThrows
    public static <T, D> T entityToDTO(Class<T>  dto, @lombok.NonNull D entity){
        Class<?> entityClass = entity.getClass();
        Field[] entityFields = entityClass.getDeclaredFields();
        Method[] dtoDeclaredMethods = dto.getDeclaredMethods();
        T t = dto.newInstance();
        for (Method method: dtoDeclaredMethods) {
            String name = method.getName();
            if (name.startsWith("set")){
                String s = name.substring(3);
                for (Field e: entityFields) {
                    e.setAccessible(true);
                    if (s.equalsIgnoreCase(e.getName())){
                            if (e.getType().equals(Date.class) && e.get(entity) != null){
                                method.invoke(t, DateUtil.dateFormat_To_Day((Date) e.get(entity)));
                            }else{
                                method.invoke(t, e.get(entity));
                            }
                    }
                }
            }

        }
        return t;
    }

    public static <T, D> List<D> entityListToDTOList(List<T> list, Class<D> dClass){
        List<D> dList = new ArrayList<>();
        list.forEach(t -> dList.add(BeanUtil.entityToDTO(dClass, t)));
        return dList;
    }

    @Nullable
    public static <T> T transformFrom(@Nullable Object source, @NonNull Class<T> targetClass) {
        Assert.notNull(targetClass, "Target class must not be null");

        if (source == null) {
            return null;
        }

        try {
            T targetInstance = targetClass.newInstance();
            org.springframework.beans.BeanUtils
                    .copyProperties(source, targetInstance);
            return targetInstance;
        } catch (Exception e) {
            throw new BeanUtilException("transform fail", e);
        }
    }

    static class BeanUtilException extends RuntimeException{

        public BeanUtilException(String s, Throwable e) {
            super(s, e);
        }
    }
}
