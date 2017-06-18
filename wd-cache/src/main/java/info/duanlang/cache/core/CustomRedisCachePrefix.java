package info.duanlang.cache.core;

import org.springframework.data.redis.cache.RedisCachePrefix;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 缓存前缀设定，方便识别 以及批量清除缓存
 * @author liuzhifei
 */
public class CustomRedisCachePrefix implements RedisCachePrefix {

	private final RedisSerializer<String> serializer = new StringRedisSerializer();
	public static final String delimiter = ":";
	public static final String prefix = "Cache:";

	public byte[] prefix(String cacheName) {
		return serializer.serialize(prefix.concat(cacheName.concat(delimiter)));
	}
}
