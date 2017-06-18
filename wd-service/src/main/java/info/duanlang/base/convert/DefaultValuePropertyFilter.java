package info.duanlang.base.convert;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import info.duanlang.base.bean.BasicBean;
import info.duanlang.base.enumeration.IEnum;
import info.duanlang.base.utils.NumberUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * JSON属性默认值过滤器
 * @author lzf
 */
public class DefaultValuePropertyFilter extends SimpleBeanPropertyFilter {

    
    @Override
    public void serializeAsField(Object pojo, JsonGenerator jgen, SerializerProvider provider, PropertyWriter writer) throws Exception {
        BeanPropertyWriter propertyWriter = (BeanPropertyWriter) writer;
        JsonDefaultValueIgnore ignoreAnnontation = writer.findAnnotation(JsonDefaultValueIgnore.class);
        Object value = propertyWriter.get(pojo);
        if(writer.getType().isTypeOrSubTypeOf(IEnum.class) && ignoreAnnontation == null)
            segmentIEnum(pojo, jgen, provider, propertyWriter, value);
        else if (value == null && include(writer) && ignoreAnnontation == null)
            setDefaultValue(pojo, jgen, provider, propertyWriter);
        else
            super.serializeAsField(pojo, jgen, provider, writer);
    }
    
    /**
     * 分割枚举
     */
    public void segmentIEnum(Object pojo, JsonGenerator jgen, SerializerProvider provider, PropertyWriter writer, Object value) throws IOException {
        String fieldName = writer.getName();
        IEnum v = (IEnum) value;
        int id = -666;
        String text = StringUtils.EMPTY;
        if (v != null) {
            id = v.getId();
            text = v.getText();
        }
        jgen.writeNumberField(fieldName + "Id", id);
        jgen.writeStringField(fieldName + "Text", text);
    }

    /**
     * 设置对应类型的默认值
     */
    public void setDefaultValue(Object pojo, JsonGenerator jgen, SerializerProvider provider, PropertyWriter writer) throws Exception {
        String fieldName = writer.getName();
        JavaType javaType = writer.getType();
        if (javaType.isArrayType() || javaType.isCollectionLikeType()) {
            jgen.writeArrayFieldStart(fieldName);
            jgen.writeEndArray();
        } else if (javaType.isJavaLangObject()
                || javaType.isMapLikeType()
                || javaType.isTypeOrSubTypeOf(BasicBean.class)) {
            jgen.writeFieldName(fieldName);
            jgen.writeStartObject();
            jgen.writeEndObject();
        } else if (javaType.hasRawClass(Integer.class) 
                || javaType.hasRawClass(Long.class)
                || javaType.hasRawClass(Short.class)) {
            jgen.writeNumberField(fieldName, NumberUtil.INTEGER_ZERO);
        } else if (javaType.hasRawClass(BigDecimal.class)
                || javaType.hasRawClass(Double.class)
                || javaType.hasRawClass(Float.class)) {
            jgen.writeNumberField(fieldName, NumberUtil.DOUBLE_ZERO);
        } else if (javaType.hasRawClass(String.class)
                || javaType.hasRawClass(Date.class)
                || javaType.isEnumType()) {
            jgen.writeStringField(fieldName, StringUtils.EMPTY);
        } else {
            super.serializeAsField(pojo, jgen, provider, writer);
        }
    }
}
