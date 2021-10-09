package vn.com.viettel.vds.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(RedisCacheConfigurationProperties.class)
@Slf4j
public class RedisCacheConfig extends CachingConfigurerSupport {

    private static RedisCacheConfiguration createCacheConfiguration(long timeoutInSeconds) {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(timeoutInSeconds));
    }

    @Bean
    public LettuceConnectionFactory redisConnectionFactory(RedisCacheConfigurationProperties properties) {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(properties.getRedisHost());
        redisStandaloneConfiguration.setPort(properties.getRedisPort());
        log.info("Connect to redis cache from:"+properties.getRedisHost()+":"+ properties.getRedisPort());
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public RedisTemplate<Byte[], Byte[]> redisTemplate(RedisConnectionFactory cf) {
        RedisTemplate<Byte[], Byte[]> redisTemplate = new RedisTemplate<Byte[], Byte[]>();
        redisTemplate.setConnectionFactory(cf);
        return redisTemplate;
    }

    @Bean
    public RedisCacheConfiguration cacheConfiguration(RedisCacheConfigurationProperties properties) {
        return createCacheConfiguration(properties.getTimeoutSeconds());
    }

    @Bean
    public CacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory, RedisCacheConfigurationProperties properties) {
        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();

        for (Map.Entry<String, Long> cacheNameAndTimeout : properties.getCacheExpirations().entrySet()) {
            cacheConfigurations.put(cacheNameAndTimeout.getKey(), createCacheConfiguration(cacheNameAndTimeout.getValue()));
        }

        return RedisCacheManager
                .builder(redisConnectionFactory)
                .cacheDefaults(cacheConfiguration(properties))
                .withInitialCacheConfigurations(cacheConfigurations).build();
    }
}