package info.duanlang.base.convert;


import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Spring MVC 数据类型转换 自动根据页面字符串格式适应转换，也支持赋值customFormat自定义
 * 
 * @author liuzhifei
 */
public class StringToDateConverter implements Converter<String, Date> {

    private final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final SimpleDateFormat dateTimFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final SimpleDateFormat dateNoDFormat = new SimpleDateFormat("yyyy-MM");
    private final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    private final SimpleDateFormat timFormat = new SimpleDateFormat("HH:mm");
    private final DateFormat customFormat;

    public StringToDateConverter() {
        this.customFormat = null;
        _init();
    }

    public StringToDateConverter(DateFormat dateFormat) {
        this.customFormat = dateFormat;
        _init();
    }

    // 严格处理
    private void _init() {
        dateTimeFormat.setLenient(false);
        dateTimFormat.setLenient(false);
        dateFormat.setLenient(false);
        dateNoDFormat.setLenient(false);
        timeFormat.setLenient(false);
        timFormat.setLenient(false);
    }

    @Override
    public Date convert(String text) {
        if (!StringUtils.hasText(text)) {
            return null;
        } else {
            try {
                if (customFormat != null) {
                    return this.customFormat.parse(text);
                } else {
                    String[] left = text.split("-");
                    String[] right = text.split(":");
                    if (left.length > 1) {
                        if (left.length == 2) {
                            return this.dateNoDFormat.parse(text);
                        }else if (right.length == 1) {
                            return this.dateFormat.parse(text);
                        } else if (right.length == 2) {
                            return this.dateTimFormat.parse(text);
                        } else {
                            return this.dateTimeFormat.parse(text);
                        }
                    } else {
                        if (right.length == 2) {
                            return this.timFormat.parse(text);
                        } else {
                            return this.timeFormat.parse(text);
                        }
                    }
                }
            } catch (ParseException ex) {
                throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
            }
        }
    }
}
