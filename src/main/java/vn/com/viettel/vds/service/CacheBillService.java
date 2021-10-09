package vn.com.viettel.vds.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;
import vn.com.viettel.vds.dto.BillDTO;

@Service
@Slf4j
public class CacheBillService {

    /**
     * update Bill to cache
     * @param billDTO
     * @return
     */
    @CachePut(cacheNames = "bill", key = "#billDTO.tableId", cacheManager = "redisCacheManager")
    public byte[] setToCache(BillDTO billDTO){
        return SerializationUtils.serialize(billDTO);
    }

    /**
     * => try to get Bill from cache, if not exist return null
     * @param id
     * @return
     */
    @Cacheable(cacheNames = "bill", key = "#id",cacheManager = "redisCacheManager")
    public byte[] getFromCache(Integer id){
        return null;
    }

    @CacheEvict(cacheNames = "bill", key = "#id", cacheManager = "redisCacheManager")
    public void removeFromCache(Integer id){
    }
}
