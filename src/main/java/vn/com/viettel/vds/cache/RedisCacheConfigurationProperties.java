package vn.com.viettel.vds.cache;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "cache")
@Data
public class RedisCacheConfigurationProperties {

    private long timeoutSeconds = 60;
    private int redisPort = 6379;
    private String redisHost = "localhost";
    // Mapping of cacheNames to expira-after-write timeout in seconds
    private Map<String, Long> cacheExpirations = new HashMap<>();
}