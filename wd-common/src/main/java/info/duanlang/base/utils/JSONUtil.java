package info.duanlang.base.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * JSON工具类
 * @author lzf
 */
public class JSONUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("DefaultValuePropertyFilter", SimpleBeanPropertyFilter.serializeAll());
        mapper.setFilterProvider(filterProvider);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    private JSONUtil() {

    }

    public static ObjectMapper getMapper() {
        return mapper;
    }

    /**
     * 对象转换为JSON字符串
     * @param obj 任何对象
     * @return 对象JSON字符串
     */
    public static String toJson(Object obj) {
        try {
            return getMapper().writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * JSON字符串转换为对象
     * @param in JSON字符串
     * @param classType 对象的class
     * @return 转换的对象
     */
    public static <T> T deserialize(String in, Class<T> classType) {
        if (in == null || classType == null) {
            return null;
        }
        try {
            return mapper.readerFor(classType).readValue(in);
        } catch (JsonProcessingException e) {
            System.err.println("Json parse error with string:" + in);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * JSON字符串转换为对象
     * @param in JSON字符串
     * @param classType 对象的class
     * @param objectMapper 自定义对象
     * @return 转换的对象
     */
    public static <T> T deserialize(String in, Class<T> classType, ObjectMapper objectMapper) {
        if (in == null || classType == null) {
            return null;
        }
        try {
            return objectMapper.readerFor(classType).readValue(in);
        } catch (JsonProcessingException e) {
            System.err.println("Json parse error with string:" + in);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * JSON字符串转换为对象集合
     * @param datas JSON字符串
     * @param beanClass T对象的class
     * @return List<T对象>
     */
    public static <T> List<T> stringToList(String datas, Class<T> beanClass) {

        if (StringUtils.isNotBlank(datas)) {
            try {
                return mapper.readValue(datas, mapper.getTypeFactory().constructParametricType(ArrayList.class, beanClass));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<T>();
    }
    
    /**
     * JSON字符串转换为对象集合
     * @param datas JSON字符串
     * @param beanClass T对象的class
     * @param objectMapper 自定义objectMapper对象
     * @return List<T对象>
     */
    public static <T> List<T> stringToList(String datas, Class<T> beanClass, ObjectMapper objectMapper) {

        if (StringUtils.isNotBlank(datas)) {
            try {
                return objectMapper.readValue(datas, objectMapper.getTypeFactory().constructParametricType(ArrayList.class, beanClass));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<T>();
    }

    /**
     * JSON字符串转换为复杂对象
     * @param datas JSON字符串
     * @param ref TypeReference ref = new TypeReference<List<Map<String, String>>>() { }; 类似
     * @return T对象
     */
    public static <T> T deserializeTypeReference(String datas, TypeReference<T> ref) {
        if (StringUtils.isNotBlank(datas)) {
            try {
                return mapper.readValue(datas, ref);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    /**
     * 输出HTTP JSON内容
     * @param dataResult
     * @param response
     */
    public static void writerHttpMsg(Object dataResult, HttpServletResponse response) {
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(toJson(dataResult));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 使用自定义输出HTTP JSON内容
     * @param objectMapper 自定义objectMapper
     * @param response
     */
    public static void writerHttpMsg(ObjectMapper objectMapper, Object obj, HttpServletResponse response) {
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(toJson(objectMapper, obj));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用自定义转换
     * @param objectMapper 自定义objectMapper
     * @param obj
     * @return
     */
    public static String toJson(ObjectMapper objectMapper, Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
