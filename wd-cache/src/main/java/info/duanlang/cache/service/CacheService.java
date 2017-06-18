package info.duanlang.cache.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/6/3.
 */
public interface CacheService {
    public void set(String key, String value, int timeout);

    public void set(String key, String value, int timeout, TimeUnit timeUnit);

    public void hSet(String key, String hashKey, String value);

    public void multiHSet(String key, Map<? extends String, ? extends String> m);

    public String get(String key);

    public String hGet(String key, String hashKey);

    public Map<String, String> hGet(String key);

    public List<String> multiGet(Collection<String> keys);

    public List<String> multiHGet(String key, Collection<String> hashKeys);

    public void del(String key);

    public void hDel(String key, Object ...hashKeys);

    public void del(Collection<String> key);

    public void clearCache(String keyPrefix);

    /**
     * 默认' Cache: '顶级前缀，主要处理注解缓存
     */
    public void clearAnnotationCache();

    /**
     * 默认' Cache: ' 顶级前缀，主要处理注解缓存
     */
    public void clearAnnotationCache(String keyPrefix);

    public Set<String> keys(String pattern);

    public long getExpire(String key);
}
