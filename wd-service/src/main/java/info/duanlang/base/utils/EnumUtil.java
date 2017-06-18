package info.duanlang.base.utils;


import info.duanlang.base.enumeration.IEnum;
import org.apache.commons.lang3.EnumUtils;

/**
 * 枚举工具类
 * @author lzf
 */
public class EnumUtil extends EnumUtils {

    /**
     * 通用枚举转换 (值转枚举)
     * @param enumClass 需要转换的对应枚举类
     * @param id 值
     * @return 对应值的枚举
     */
    public static <E extends IEnum> E convert(Class<E> enumClass, Integer id){
        
        if(id == null)
            return null;
        
        if(enumClass.isEnum()) {
            E[] enums = enumClass.getEnumConstants();
            for (E e : enums) {
                if(e.getId() == id)
                    return e;
            }
        }
        
        return null;
    }
}
