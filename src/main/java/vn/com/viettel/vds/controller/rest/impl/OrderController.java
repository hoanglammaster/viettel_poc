package vn.com.viettel.vds.controller.rest.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.com.viettel.vds.controller.rest.api.OrderInterface;
import vn.com.viettel.vds.dto.FoodDTO;
import vn.com.viettel.vds.dto.OrderDTO;
import vn.com.viettel.vds.model.converter.JsonConverter;
import vn.com.viettel.vds.service.OrderService;
import vn.com.viettel.vds.validator.TableValidator;

import java.util.Optional;

@RestController
@Slf4j
public class OrderController implements OrderInterface {

    private final OrderService orderService;
    private final TableValidator tableValidator;

    @Autowired
    public OrderController(OrderService orderService, TableValidator tableValidator) {
        this.orderService = orderService;
        this.tableValidator = tableValidator;
    }

    @Override
    public ResponseEntity<String> orderFoodById(@RequestBody OrderDTO order) {
        JsonConverter<FoodDTO> orderDTOJsonConverter = new JsonConverter<>();
        if(tableValidator.isTableIdValid(order.getTableId())) {
            Optional<FoodDTO> food = orderService.getFoodFromPublicApi(order);
            if (food.isPresent() && !food.isEmpty()) {
                return ResponseEntity.ok(orderDTOJsonConverter.objectToJson(food.get()));
            } else {
                return ResponseEntity.badRequest().body("Food id not exist");
            }
        }else{
            return ResponseEntity.badRequest().body("Table not exist");
        }
    }
}
