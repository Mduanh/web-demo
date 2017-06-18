package info.duanlang.cache.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import info.duanlang.base.utils.StringUtil;
import info.duanlang.cache.core.CustomRedisCachePrefix;
import info.duanlang.cache.service.CacheService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CacheServiceImpl implements CacheService, InitializingBean {
    
    private Log log = LogFactory.getLog(this.getClass());

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    @Resource(name="stringRedisTemplate")
    private HashOperations<String, String, String> hashOperations;
    
    @Override
    public void afterPropertiesSet() throws Exception {
       //重启启动的话 清除注解缓存，以防数据格式有变动 无法兼容
       clearAnnotationCache();
    }

    @Override
    public void set(String key, String value, int timeout) {
        stringRedisTemplate.opsForValue().set(key, value, timeout);
    }

    @Override
    public void set(String key, String value, int timeout, TimeUnit timeUnit) {
        stringRedisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }
    
    @Override
    public void hSet(String key, String hashKey, String value) {
        hashOperations.put(key, hashKey, value);
    }
    
    @Override
    public void multiHSet(String key, Map<? extends String, ? extends String> m){
        hashOperations.putAll(key, m);
    }

    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }
    
    @Override
    public Map<String, String> hGet(String key){
        return hashOperations.entries(key);
    }
    
    @Override
    public String hGet(String key, String hashKey){
        return hashOperations.get(key, hashKey);
    }
    
    @Override
    public List<String> multiGet(Collection<String> keys){
        return stringRedisTemplate.opsForValue().multiGet(keys);
    }
    
    @Override
    public List<String> multiHGet(String key, Collection<String> hashKeys){
        return hashOperations.multiGet(key, hashKeys);
    }

    @Override
    public void del(String key) {
        stringRedisTemplate.delete(key);
    }
    
    @Override
    public void del(Collection<String> keys) {
        stringRedisTemplate.delete(keys);
    }
    
    @Override
    public void hDel(String key, Object ...hashKeys) {
        hashOperations.delete(key, hashKeys);
    }

    @Override
    public void clearCache(String keyPrefix) {
        if (StringUtil.isEmpty(keyPrefix)) {
            return;
        }
        Set<String> keys = stringRedisTemplate.keys(keyPrefix + ":*");
        log.info("clearCache " + keyPrefix + " keys:" + keys.size());
        stringRedisTemplate.delete(keys);
    }

    @Override
    public void clearAnnotationCache() {
        Set<String> keys = stringRedisTemplate.keys(CustomRedisCachePrefix.prefix + "*");
        log.info("clearAnnotationCache keys:" + keys.size());
        stringRedisTemplate.delete(keys);
    }

    @Override
    public void clearAnnotationCache(String keyPrefix) {
        if (StringUtil.isEmpty(keyPrefix)) {
            clearAnnotationCache();
            return;
        }
        Set<String> keys = stringRedisTemplate.keys(CustomRedisCachePrefix.prefix + keyPrefix + ":*");
        log.info("clearAnnotationCache " + keyPrefix + " keys:" + keys.size());
        stringRedisTemplate.delete(keys);
    }

    @Override
    public Set<String> keys(String pattern) {
        return stringRedisTemplate.keys(pattern);
    }

    @Override
    public long getExpire(String key) {
        Long expire = stringRedisTemplate.getExpire(key);
        if (expire == null)
            return -1;

        return expire;
    }
}
