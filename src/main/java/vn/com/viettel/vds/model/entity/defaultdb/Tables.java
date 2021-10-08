package vn.com.viettel.vds.model.entity.defaultdb;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tbs")
@Setter
@Getter
@RequiredArgsConstructor
public class Tables implements Serializable {

    private static final long serialVersionUID = 4734217452230025997L;
    @Id
    @Column(name = "tb_id")
    private Long id;
    @Column(name = "tb_name")
    private String name;
    @Column(name = "number_of_seats")
    private Integer nụmberOfSeats;
}