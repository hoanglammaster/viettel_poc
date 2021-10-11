package vn.com.viettel.vds.cache;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "cache")
@Data
public class RedisCacheConfigurationProperties {

    private long timeToLife;
    private int redisPort;
    private String redisHost;
    // Mapping of cacheNames to expira-after-write timeout in seconds
    private Map<String, Long> cacheExpirations = new HashMap<>();

    public RedisCacheConfigurationProperties(
        @Value("${spring.redis.host}") String redisHost,
        @Value("${spring.redis.port}") int redisPort,
        @Value("${spring.redis.time-to-live}") long timeToLife
        ) {
        this.timeToLife = timeToLife;
        this.redisPort = redisPort;
        this.redisHost = redisHost;
    }
}