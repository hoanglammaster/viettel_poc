package vn.com.viettel.vds.controller.rest.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.function.EntityResponse;

@RequestMapping("api/v1/payment")
public interface PaymentInterface {

    @GetMapping(
            path = "/bill/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    EntityResponse<?> payBillById(@PathVariable("id") Long id);
}
