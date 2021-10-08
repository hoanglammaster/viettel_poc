package vn.com.viettel.vds.model.converter;

import vn.com.viettel.vds.dto.BillDTO;
import vn.com.viettel.vds.model.entity.secondarydb.Bill;

public class BillToDTOConverter implements Converter<BillDTO, Bill>{

    public BillDTO convert(Bill bill){
        return new BillDTO(bill.getTableId(),bill.getEntryTime(), bill.getOutTime(),bill.getPrice());
    }
}
