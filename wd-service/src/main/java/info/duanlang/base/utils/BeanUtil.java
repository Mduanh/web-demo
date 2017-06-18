package info.duanlang.base.utils;

import info.duanlang.base.bean.BasicBean;
import org.apache.commons.lang3.ClassUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.CollectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 不同类型相同属性COPY的 深度克隆需要继承BasicBean
 * @author liuzhifei
 */
public class BeanUtil {

    @SuppressWarnings("unchecked")
    public static <T> T copyProperties(Object source, T target) {
        
        if (source == null || target == null)
            return null;

        // 创建属性读写器
        BeanWrapper wrapper = new BeanWrapperImpl(source);
        BeanWrapper targetWrapper = new BeanWrapperImpl(target);

        // 循环源的属性集合
        for (PropertyDescriptor descriptor : wrapper.getPropertyDescriptors()) {

            // 验证有get方法的
            Method readMethod = descriptor.getReadMethod();
            if (readMethod == null)
                continue;

            // 获取目标的数据类型
            Class<?> targetPropertyType = targetWrapper.getPropertyType(descriptor.getName());
            if (targetPropertyType == null)
                continue;

            // 获取目标属性
            PropertyDescriptor propertyDescriptor = targetWrapper.getPropertyDescriptor(descriptor.getName());
            // 排除目标不能写入的属性
            Method writeMethod = propertyDescriptor.getWriteMethod();
            if (writeMethod == null)
                continue;

            // 如果不是公共方法移除访问权限检查
            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers()))
                writeMethod.setAccessible(true);
            
            Class<?> propertyType = descriptor.getPropertyType();
            // 源数据如果是集合
            if (propertyType.isAssignableFrom(List.class)) {
                
                // 获取源的值
                List<?> list = (List<?>) wrapper.getPropertyValue(descriptor.getName());
                if (CollectionUtils.isEmpty(list)) {
                    continue;
                }

                // 目标List的对象类型
                Class<Object> genericClass = null;
                // 目标List新对象
                List<Object> targetList = new ArrayList<Object>();
                
                Type[] genericParameterTypes = writeMethod.getGenericParameterTypes();
                ParameterizedType type = (ParameterizedType) genericParameterTypes[0];
                genericClass = (Class<Object>) type.getActualTypeArguments()[0];
              /*  
                try {
                    Field field = target.getClass().getDeclaredField(descriptor.getName());
                    ParameterizedType type = (ParameterizedType) field.getGenericType();
                    genericClass = (Class<Object>) type.getActualTypeArguments()[0];
                } catch (Exception e) {
                    e.printStackTrace();
                }*/

                if (genericClass == null)
                    continue;

                try {

                    // 循环深克隆集合数据
                    for (Object object : list) {
                        // 如果是Entity则深克隆， 其他直接赋值
                        if (ClassUtils.getAllSuperclasses(genericClass).contains(BasicBean.class))
                            targetList.add(copyProperties(object, genericClass.newInstance()));
                        else
                            targetList.add(object);
                    }

                    // 赋值给目标对象
                    writeMethod.invoke(target, targetList);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {

                try {

                    // 如果不是公共方法移除访问权限检查
                    if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                        readMethod.setAccessible(true);
                    }

                    // 读取源属性的值
                    Object object = readMethod.invoke(source);
                    if (object != null) {
                        // 如果是PO对象 则深克隆 其他则直接赋值
                        if (ClassUtils.getAllSuperclasses(propertyType).contains(BasicBean.class)) {
                            Object instance = targetPropertyType.newInstance();
                            writeMethod.invoke(target, copyProperties(object, instance));
                        } else {
                            writeMethod.invoke(target, readMethod.invoke(source));
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return target;
    }

    public static <T> List<T> copyListProperties(List<?> source, List<T> target, Class<T> targetValueClass) {
        
        if (source == null || target == null)
            return null;

        try {
            for (Object obj : source) 
                target.add(copyProperties(obj, targetValueClass.newInstance()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
        return target;
    }
}
