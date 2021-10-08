package vn.com.viettel.vds.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class FoodDTO {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String foodName;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String foodDescription;
}
