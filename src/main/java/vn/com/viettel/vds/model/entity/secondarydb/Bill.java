package vn.com.viettel.vds.model.entity.secondarydb;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "bills")
@RequiredArgsConstructor
@Setter
@Getter
public class Bill implements Serializable {

    private static final long serialVersionUID = 181466080299709183L;

    @Id
    @Column(name = "bill_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "table_id")
    private Integer tableId;
    @Column(name = "entry_time")
    private Date entryTime;
    @Column(name = "out_time")
    private Date outTime;
    @Column(name = "price")
    private BigDecimal price;

    public Bill(Integer tableId, Date entryTime, Date outTime, BigDecimal totalPrice) {
        this.tableId = tableId;
        this.entryTime = entryTime;
        this.outTime = outTime;
        this.price = totalPrice;
    }
}
