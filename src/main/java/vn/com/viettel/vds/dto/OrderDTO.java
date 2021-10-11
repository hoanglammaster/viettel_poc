package vn.com.viettel.vds.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class OrderDTO implements Serializable {
    private static final long serialVersionUID = 550261767711152012L;

    @JsonProperty("table-id")
    private Integer tableId;
    @JsonProperty("food-id")
    private Integer foodId;
}
