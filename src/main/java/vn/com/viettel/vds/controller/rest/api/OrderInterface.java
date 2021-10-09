package vn.com.viettel.vds.controller.rest.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.com.viettel.vds.dto.OrderDTO;

@RequestMapping("api/v1/order")
public interface OrderInterface {

    @PostMapping(
            path = "/food",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> orderFoodById( OrderDTO order);


}
