package info.duanlang.base.utils;

import info.duanlang.constant.NumberConstant;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;

/**
 * 数字工具类
 * 
 * @author lzf
 */
public class NumberUtil extends NumberUtils {

    /**
     * 检查是否有值，如果没有就为0，并四舍五入
     * 
     * @param value
     *            被检查值
     * @return 结果
     */
    public static BigDecimal ifNullDefaultZero(BigDecimal value) {
        if (value == null)
            return BigDecimal.ZERO;
        return value.setScale(NumberConstant.SCALE, NumberConstant.ROUNDING_MODE);
    }

    /**
     * 四舍五入，如果为null 原样返回
     * 
     * @param value
     *            被检查值
     * @return 结果
     */
    public static BigDecimal round(BigDecimal value) {
        if (value == null)
            return null;
        return value.setScale(NumberConstant.SCALE, NumberConstant.ROUNDING_MODE);
    }

    /**
     * 加
     * 
     * @param value1
     * @param values
     *            可以多个，也可以一个
     * @return 结果
     */
    public static BigDecimal add(BigDecimal value1, BigDecimal... values) {
        BigDecimal _value = ifNullDefaultZero(value1);

        for (BigDecimal v : values)
            _value = _value.add(ifNullDefaultZero(v));

        return _value;
    }

    /**
     * 减
     * 
     * @param value1
     * @param values
     *            可以多个，也可以一个
     * @return 结果
     */
    public static BigDecimal subtract(BigDecimal value1, BigDecimal... values) {
        BigDecimal _value = ifNullDefaultZero(value1);

        for (BigDecimal v : values)
            _value = _value.subtract(ifNullDefaultZero(v));

        return _value;
    }

    /**
     * 乘
     * 
     * @param value1
     * @param values
     *            可以多个，也可以一个
     * @return 结果
     */
    public static BigDecimal multiply(BigDecimal value1, BigDecimal... values) {
        BigDecimal _value = ifNullDefaultZero(value1);

        for (BigDecimal v : values)
            _value = _value.multiply(ifNullDefaultZero(v));

        return _value.setScale(NumberConstant.SCALE, NumberConstant.ROUNDING_MODE);
    }

    /**
     * 除
     * 
     * @param value1
     * @param values
     *            可以多个，也可以一个
     * @return 结果
     */
    public static BigDecimal divide(BigDecimal value1, BigDecimal... values) {

        if (value1 == null)
            return BigDecimal.ZERO;

        for (BigDecimal v : values) {
            if (v == null || v.doubleValue() == 0)
                return BigDecimal.ZERO;
            value1 = value1.divide(v, NumberConstant.SCALE, NumberConstant.ROUNDING_MODE);
        }

        return value1;
    }

    /**
     * 判断value是否大于0
     * 
     * @param value
     * @return
     */
    public static boolean isGtZero(BigDecimal value) {

        if (value == null)
            return false;
        else
            return value.compareTo(BigDecimal.ZERO) == 1;
    }

    /**
     * 判断value是否大于等于0
     * 
     * @param value
     * @return
     */
    public static boolean isGeZero(BigDecimal value) {

        if (value == null)
            return false;
        else
            return value.compareTo(BigDecimal.ZERO) == 1 || value.compareTo(BigDecimal.ZERO) == 0;
    }

    /**
     * 获取负数
     * @param value 正数或负数
     * @return 返回round后的 负数
     */
    public static BigDecimal nabs(BigDecimal value) {

        value = round(value);
        return value.abs().negate();
    }

    /**
     * 获取正数
     * @param value 正数或负数
     * @return 返回round后的 绝对值
     */
    public static BigDecimal abs(BigDecimal value) {

        value = round(value);
        return value.abs();
    }
    
    /**
     * 检查是否有值，如果没有就为0
     * @param value
     * @return
     */
    public static double ifNullDefaultZero(Double value) {
        if (value == null)
            return 0D;
        return value;
    }
}
