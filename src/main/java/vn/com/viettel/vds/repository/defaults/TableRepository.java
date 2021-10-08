package vn.com.viettel.vds.repository.defaults;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.com.viettel.vds.model.entity.defaultdb.Tables;

@Repository
public interface TableRepository extends JpaRepository<Tables, Long> {
}
