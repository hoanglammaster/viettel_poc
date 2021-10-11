package vn.com.viettel.vds.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.com.viettel.vds.dto.OrderDTO;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class PaymentServiceTest {

    private final Integer TABLE_ID = 100;
    private final Integer FOOD_ID = 1000;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CacheBillService cacheBillService;

    @BeforeEach
    void setUp() {
        orderService.getFoodFromPublicApi(new OrderDTO(TABLE_ID,FOOD_ID));
    }

    @AfterEach
    void tearDown() {
        cacheBillService.removeFromCache(TABLE_ID);
    }

    @Test
    @DisplayName("Pay_The_Bill_With_Correct_Table_ID_And_Exist_Bill")
    void payTheBill() {
        boolean expectedResult = paymentService.payTheBill(TABLE_ID);
        Assertions.assertTrue(expectedResult);
    }
    @Test
    @DisplayName("Pay_The_Bill_With_InCorrect_Table_ID_And_Exist_Bill")
    void payTheBill2() {
        boolean expectedResult = paymentService.payTheBill(TABLE_ID+1);
        Assertions.assertFalse(expectedResult);
    }
    @Test
    @DisplayName("Pay_The_Bill_With_Correct_Table_ID_And_Not_Exist_Bill")
    void payTheBill3() {
        cacheBillService.removeFromCache(TABLE_ID);
        boolean expectedResult = paymentService.payTheBill(TABLE_ID);
        Assertions.assertFalse(expectedResult);
    }
}