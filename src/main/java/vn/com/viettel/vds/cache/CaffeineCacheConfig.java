package vn.com.viettel.vds.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableCaching
public class CaffeineCacheConfig {

  @Bean
  @Primary
  public CacheManager caffeineCacheManager(){
    CaffeineCacheManager cacheManager = new CaffeineCacheManager("bill");
    cacheManager.setCaffeine(caffeineCacheBuilder());
    return cacheManager;
  }

  @Bean
  Caffeine<Object, Object> caffeineCacheBuilder() {
    return Caffeine.newBuilder()
            .initialCapacity(100)
            .maximumSize(500)
            .expireAfterAccess(1, TimeUnit.HOURS)
            .weakKeys()
            .recordStats();
  }

}
