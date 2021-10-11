package vn.com.viettel.vds.service;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.SerializationUtils;
import vn.com.viettel.vds.dto.BillDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class CacheBillServiceTest {

    @Autowired
    private CacheBillService cacheBillService;
    private BillDTO billDTO;

    @BeforeEach
    void setUp() {
        billDTO = new BillDTO(100,Date.from(LocalDateTime.of(2021,10,10,10,10).atZone(ZoneId.systemDefault()).toInstant()), null, BigDecimal.TEN);
    }

    @AfterEach
    void tearDown() {
        cacheBillService.removeFromCache(billDTO.getTableId());
    }

    @Nested
    @DisplayName("Test_For_Set_To_Cache_Method")
    class SetToCacheNest{

        @Test
        @DisplayName("Set_Bill_To_Cache_Success")
        void setToCache() {
            byte[] expectedResult = cacheBillService.setToCache(billDTO);
            Assertions.assertArrayEquals(expectedResult, SerializationUtils.serialize(billDTO),"Byte array return after set cache is not match");
        }
    }


    @Nested
    @DisplayName("Test_For_Method_Get_From_Cache")
    class GetFromCacheNest{

        @Test
        @DisplayName("Get_Bill_From_Cache_Success")
        void getFromCache() {
            cacheBillService.setToCache(billDTO);
            byte[] expectedResult = cacheBillService.getFromCache(billDTO.getTableId());
            Assertions.assertArrayEquals(expectedResult,SerializationUtils.serialize(billDTO),"Byte array when get bill from cache is not match");
        }
        @Test
        @DisplayName("Get_Bill_From_Cache_With_Wrong_Table_Id_Failed")
        void getFromCache2() {
            cacheBillService.setToCache(billDTO);
            byte[] expectedResult = cacheBillService.getFromCache(billDTO.getTableId()+1);
            Assertions.assertNull(expectedResult);
        }

    }

    @Nested
    @DisplayName("Test_For_Method_Remove_From_Cache")
    class RemoveFromCacheNest{

        @Test
        @DisplayName("Remove_From_Cache_With_Existing_Data_Success")
        void removeFromCache() {
            cacheBillService.setToCache(billDTO);
            cacheBillService.removeFromCache(billDTO.getTableId());
            byte[] expectedResult = cacheBillService.getFromCache(billDTO.getTableId());
            Assertions.assertNull(expectedResult,"Remove from cache with existing value not work");
        }
        @Test
        @DisplayName("Remove_From_Cache_With_Wrong_Bill_Id")
        void removeFromCache2() {
            cacheBillService.setToCache(billDTO);
            cacheBillService.removeFromCache(billDTO.getTableId()+1);
            byte[] expectedResult = cacheBillService.getFromCache(billDTO.getTableId());
            Assertions.assertNotNull(expectedResult,"Remove from cache all value");
        }
    }

}