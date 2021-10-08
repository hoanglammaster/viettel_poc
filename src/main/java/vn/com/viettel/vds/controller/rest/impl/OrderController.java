package vn.com.viettel.vds.controller.rest.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.com.viettel.vds.controller.rest.api.OrderInterface;

@RestController
@Slf4j
public class OrderController implements OrderInterface {
    @Override
    public ResponseEntity<?> orderFoodById(Long id) {
        return null;
    }
}
