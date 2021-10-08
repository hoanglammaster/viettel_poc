package vn.com.viettel.vds.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BillDTO {

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Integer tableId;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd@HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd@HH:mm:ss")
    private Date entryTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd@HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd@HH:mm:ss")
    private Date outTime;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private BigDecimal totalPrice;
}
