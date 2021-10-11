package vn.com.viettel.vds.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BillDTO implements Serializable {

    private static final long serialVersionUID = -3354243364607930472L;

    private Integer tableId;
    @DateTimeFormat(pattern = "yyyy-MM-dd@HH:mm:ss")
    private Date entryTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd@HH:mm:ss")
    private Date outTime;
    private BigDecimal totalPrice;
}
