package info.duanlang.cache.core;

import java.nio.charset.Charset;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.Assert;

public class CustomStringRedisSerializer implements RedisSerializer<Object> {

    private final Charset charset;

    public CustomStringRedisSerializer() {
        this(Charset.forName("UTF8"));
    }

    public CustomStringRedisSerializer(Charset charset) {
        Assert.notNull(charset);
        this.charset = charset;
    }

    public Object deserialize(byte[] bytes) {
        return new String(bytes, this.charset);
    }

    public byte[] serialize(Object string) {
        return ((string == null) ? null : string.toString().getBytes(this.charset));
    }
}
