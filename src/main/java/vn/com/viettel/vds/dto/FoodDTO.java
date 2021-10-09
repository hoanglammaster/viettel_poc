package vn.com.viettel.vds.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Getter
@Setter
public class FoodDTO {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private BigDecimal price;
}
