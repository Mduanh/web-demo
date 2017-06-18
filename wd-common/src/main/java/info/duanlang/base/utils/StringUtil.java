package info.duanlang.base.utils;


import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Random;

/**
 * 字符串工具类
 * @author huyong
 *
 */
public class StringUtil extends StringUtils {

	public static int toInt(Object obj) {
		return toInt(obj, 0);
	}

	public static int toInt(Object obj, int defaultValue) {
		int value = defaultValue;
		if (obj != null) {

			String strValue = toString(obj);
			if (isNotEmpty(strValue)) {
				try {
					value = Integer.parseInt(strValue);
				} catch (Exception e) {
					value = defaultValue;
				}
			}
		}
		return value;
	}

	public static String toString(Object obj) {
		return toString(obj, "");
	}

	public static String toString(Object obj, String defaultValue) {
		return obj != null ? String.valueOf(obj) : defaultValue;
	}

	public static double toDouble(Object obj) {
		return toDouble(obj, 0);
	}

	public static double toDouble(Object obj, double defaultValue) {
		double value = defaultValue;
		if (obj != null) {

			String strValue = toString(obj);
			if (isNotEmpty(strValue)) {
				try {
					value = Double.parseDouble(strValue);
				} catch (Exception e) {
					value = defaultValue;
				}
			}
		}
		return value;
	}

	public static float toFloat(Object obj) {
		return toFloat(obj, 0);
	}

	public static float toFloat(Object obj, float defaultValue) {
		float value = defaultValue;
		if (obj != null) {

			String strValue = toString(obj);
			if (isNotEmpty(strValue)) {
				try {
					value = Float.parseFloat(strValue);
				} catch (Exception e) {
					value = defaultValue;
				}
			}
		}
		return value;
	}

	public static BigDecimal toDecimal(Object obj) {
		BigDecimal defalueValue = new BigDecimal("0");
		return toDecimal(obj, defalueValue);
	}

	public static BigDecimal toDecimal(Object obj, BigDecimal defaultValue) {
		BigDecimal value = defaultValue;
		if (obj != null) {

			String strValue = toString(obj);
			if (isNotEmpty(strValue)) {
				try {
					value = new BigDecimal(strValue);
				} catch (Exception e) {
					value = defaultValue;
				}
			}
		}
		return value;
	}

	public static long toLong(Object obj) {
		return toLong(obj, 0);
	}

	public static long toLong(Object obj, long defaultValue) {
		long value = defaultValue;
		if (obj != null) {

			String strValue = toString(obj);
			if (isNotEmpty(strValue)) {
				try {
					value = Long.parseLong(strValue);
				} catch (Exception e) {
					value = defaultValue;
				}
			}
		}
		return value;
	}

	public static boolean toBoolean(Object obj) {
		return toBoolean(obj, false);
	}

	public static boolean toBoolean(Object obj, boolean defaultValue) {
		boolean value = defaultValue;
		if (obj != null) {
			value = Boolean.parseBoolean(toString(obj));
		}
		return value;
	}

	public static String[] split(String str, String splitChar) {
		return str.split("\\" + splitChar);
	}

	/**
	 * 将字符串类型转换成真实类型
	 * 
	 * @param obj
	 * @param cls
	 * @return
	 */
	public static Object convert(Object obj, Class<?> cls) {

		if (cls == String.class) {
			return toString(obj);
		} else if (cls == BigDecimal.class) {
			return toDecimal(obj);
		} else if (cls == Double.class || cls == double.class) {
			return toDouble(obj);
		} else if (cls == Float.class || cls == float.class) {
			return toFloat(obj);
		} else if (cls == Integer.class || cls == int.class) {
			return toInt(obj);
		} else if (cls == Boolean.class || cls == boolean.class) {
			return toBoolean(obj);
		}
		return obj;
	}
	

    /**
     * 获取一定长度的随机字符串
     * 
     * @param length
     *            指定字符串长度
     * @return 一定长度的字符串
     */
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
    
    /**
     * 计算字符串位数，中文占2位, 数字和英文占1位
     * @param str
     * @return
     */
    public static int calculatePlaces(String str) {

        int m = 0;
        char arr[] = str.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if ((c >= 0x0391 && c <= 0xFFE5)) {
                m = m + 2;
            } else if ((c >= 0x0000 && c <= 0x00FF)) {
                m = m + 1;
            } else {
                m = m + 2;
            }
        }

        return m;
    }
    
    /**
     * 按中文占2位, 数字和英文占1位  截取字符串
     * @param str
     * @param endIndex
     * @return
     */
    public static String subCalculatePlacesStr(String str, int endIndex) {
        return subCalculatePlacesStr(str, 0, endIndex);
    }
    
    /**
     * 按中文占2位, 数字和英文占1位  截取字符串
     * @param str
     * @param startIndex
     * @param endIndex
     * @return
     */
    public static String subCalculatePlacesStr(String str, int startIndex, int endIndex) {
        
        if (StringUtil.isEmpty(str))
            return "";

        if (startIndex < 0 || endIndex < 0 || endIndex < startIndex)
            throw new IndexOutOfBoundsException("数组越界");
        
        int m = 0;
        char arr[] = str.toCharArray();
        int index1 = -1;
        int index2 = -1;
        for (int i = 0; i < arr.length; i++) {
            
            if (m <= startIndex) {
                index1 = i;
            }
            
            char c = arr[i];
            if ((c >= 0x0391 && c <= 0xFFE5)) {
                m = m + 2;
            } else if ((c >= 0x0000 && c <= 0x00FF)) {
                m = m + 1;
            } else {
                m = m + 2;
            }

            if ((m >= endIndex ||  i == arr.length - 1) && index2 == -1) {
                index2 = i + 1;
            }
        }
        
        return str.substring(index1, index2);
    }
    
    /**
     * 按中文占2位, 数字和英文占1位  截取字符串
     * @param strLeft 左边字符串
     * @param strRight 右边字符串
     * @param separator 分隔符
     * @param leftRetainSize 左边保留位数
     * @param rightRetainSize 右边保留位数
     * @param giveAndTake 是否互相谦让空间
     * @return 字符串
     */
    public static String subCalculatePlacesStr(String strLeft, String strRight, String separator, int leftRetainSize, int rightRetainSize, boolean giveAndTake) {

        int leftSize = calculatePlaces(strLeft);
        int rightSize = calculatePlaces(strRight);

        int tempLeftRetainSize = leftRetainSize;
        int tempRightRetainSize = rightRetainSize;

        if ((leftSize + rightSize) <= (leftRetainSize + rightRetainSize))
            return strLeft + separator + strRight;

        if (giveAndTake) {
            if (leftSize < leftRetainSize)
                tempRightRetainSize += (leftRetainSize - leftSize);
            else if (rightSize < rightRetainSize)
                tempLeftRetainSize += (rightRetainSize - rightSize);
        }

        String tempLeftStr = subCalculatePlacesStr(strLeft, tempLeftRetainSize);
        String tempRightStr = subCalculatePlacesStr(strRight, tempRightRetainSize);

        return tempLeftStr + separator + tempRightStr;
    }
    
    public static String subBaiChuanCalculatePlacesStr(String merchantName, String realName) {
        return subCalculatePlacesStr(merchantName, realName, "-", 20, 10, true);
    }
}
