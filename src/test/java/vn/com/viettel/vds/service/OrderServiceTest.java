package vn.com.viettel.vds.service;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.com.viettel.vds.dto.FoodDTO;
import vn.com.viettel.vds.dto.OrderDTO;

import java.util.Optional;

@SpringBootTest
@Slf4j
class OrderServiceTest {

    @Autowired
    private OrderService orderService;
    private OrderDTO orderDTO;


    @BeforeEach
    public void setup(){
        orderDTO = new OrderDTO(100,1000);
        log.info("Create new OrderDTO with data: {}", orderDTO.toString());

    }

    @Test
    @DisplayName("Get_Food_From_URL_By_Correct_Food_ID")
    void getFoodFromPublicApi() {
        Optional<FoodDTO> result = orderService.getFoodFromPublicApi(orderDTO);
        Assertions.assertFalse(result.isEmpty(),"result is empty");
        Assertions.assertTrue(result.isPresent(),"result is not present");
    }
    @Test
    @DisplayName("Get_Food_From_URL_By_Incorrect_Food_ID")
    void getFoodFromPublicApi2() {
        orderDTO.setFoodId(500);
        Optional<FoodDTO> result = orderService.getFoodFromPublicApi(orderDTO);
        Assertions.assertTrue(result.isEmpty(),"result is not empty");
    }
}