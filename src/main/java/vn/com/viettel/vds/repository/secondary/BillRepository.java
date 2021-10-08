package vn.com.viettel.vds.repository.secondary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.com.viettel.vds.model.entity.secondarydb.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
}
