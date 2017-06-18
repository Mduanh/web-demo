package info.duanlang.base.convert;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于方法上，使用该注解可不生成默认值<br>
 * 用于IEnum上面则不会生成Id和Text
 * @author lzf
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonDefaultValueIgnore {

}
