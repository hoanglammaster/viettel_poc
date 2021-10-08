package vn.com.viettel.vds.controller.rest.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/v1/order")
public interface OrderInterface {

    @GetMapping(
            path = "/food/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> orderFoodById(@PathVariable("id") Long id);


}
