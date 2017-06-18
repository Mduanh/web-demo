package info.duanlang.base.convert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * API 接口专用 JSON解析
 * 
 * @author dh
 */
@Component("apiObjectMapper")
public class ApiObjectMapper extends ObjectMapper {

    private static final long serialVersionUID = 1L;

    public ApiObjectMapper() {
        SimpleBeanPropertyFilter filter = new DefaultValuePropertyFilter();
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("DefaultValuePropertyFilter", filter);
        this.setFilterProvider(filterProvider);
        this.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }
    
}
