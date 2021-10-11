package vn.com.viettel.vds.controller.rest.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.com.viettel.vds.dto.OrderDTO;

@RequestMapping("api/v1/order")
public interface OrderInterface {

    /**
     * => create new order by table id and food id
     * @param order
     * @return
     */
    @PostMapping(
            path = "/food",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> orderFoodById( OrderDTO order);


}
