package vn.com.viettel.vds.model.mapper;

import vn.com.viettel.vds.dto.BillDTO;
import vn.com.viettel.vds.model.entity.secondarydb.Bill;

public class BillToDTOMapper implements Mapper<BillDTO, Bill> {

    public BillDTO mapToDTO(Bill bill){
        return new BillDTO(bill.getTableId(),bill.getEntryTime(), bill.getOutTime(),bill.getPrice());
    }
    public Bill mapToEntity(BillDTO bill){
        return new Bill(bill.getTableId(),bill.getEntryTime(), bill.getOutTime(),bill.getTotalPrice());
    }

}
