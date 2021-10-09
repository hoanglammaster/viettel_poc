package vn.com.viettel.vds.controller.rest.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.com.viettel.vds.controller.rest.api.PaymentInterface;
import vn.com.viettel.vds.service.PaymentService;

@RestController
@Slf4j
public class PaymentController implements PaymentInterface {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public ResponseEntity<String> payBillByTableId(Integer id) {
        if(paymentService.payTheBill(id)){
            return ResponseEntity.ok("Payment success");
        }else{
            return ResponseEntity.badRequest().body("Payment failed");
        }
    }
}
