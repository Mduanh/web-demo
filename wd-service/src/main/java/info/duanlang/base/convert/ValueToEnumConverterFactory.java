package info.duanlang.base.convert;


import info.duanlang.base.enumeration.IEnum;
import info.duanlang.base.utils.EnumUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

/**
 * 请求参数INT Value 转换为 枚举类型
 * @author lzf
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ValueToEnumConverterFactory implements ConverterFactory<String, IEnum> {

    public <T extends IEnum> Converter<String, T> getConverter(Class<T> targetType) {
        Class<?> enumType = targetType;
        while (enumType != null && !enumType.isEnum()) {
            enumType = enumType.getSuperclass();
        }
        if (enumType == null) {
            throw new IllegalArgumentException("The target type " + targetType.getName() + " does not refer to an enum");
        }
        return new StringToEnum(enumType);
    }

    private class StringToEnum<T extends IEnum> implements Converter<String, T> {

        private final Class<T> enumType;

        public StringToEnum(Class<T> enumType) {
            this.enumType = enumType;
        }

        public T convert(String source) {
            if (source.length() == 0) 
                return null;
            return EnumUtil.convert(this.enumType, Integer.valueOf(source.trim()));
        }
    }

}
