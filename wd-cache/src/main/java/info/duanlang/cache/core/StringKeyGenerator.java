package info.duanlang.cache.core;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;

/**
 * 字符串 redis cache key
 * @author liuzhifei
 */
public class StringKeyGenerator implements KeyGenerator {
	
	protected static final String NO_ARGS = "NO_ARGS";

	@Override
	public Object generate(Object target, Method method, Object... params) {
		
		if (params.length == 0) {
			return StringKeyGenerator.NO_ARGS;
		}
		if (params.length == 1) {
			Object param = params[0];
			if (param != null && !param.getClass().isArray()) {
				return param;
			}
		}
		return StringKeyGenerator.genRedisKey(params);
	}

	public static String genRedisKey(Object ...strs){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < strs.length; i++) {
			sb.append(strs[i]);
			if (i != strs.length - 1) {
				sb.append("^");
			}
		}
		return sb.toString();
	}
}
