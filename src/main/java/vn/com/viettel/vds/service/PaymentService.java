package vn.com.viettel.vds.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;
import vn.com.viettel.vds.dto.BillDTO;
import vn.com.viettel.vds.model.entity.secondarydb.Bill;
import vn.com.viettel.vds.model.mapper.BillToDTOMapper;
import vn.com.viettel.vds.model.mapper.Mapper;
import vn.com.viettel.vds.repository.secondary.BillRepository;

import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class PaymentService {

    private final CacheBillService cacheBillService;
    private final BillRepository billRepository;

    public PaymentService(CacheBillService cacheBillService, BillRepository billRepository) {
        this.cacheBillService = cacheBillService;
        this.billRepository = billRepository;
    }

    /**
     * => select bill from cache
     * => if bill exist -> set out-date for this bill -> add to db -> return true
     * => if not exist return false
     * @param id
     * @return
     */
    public Boolean payTheBill(Integer id){
        Optional<byte[]> billArray = Optional.ofNullable(cacheBillService.getFromCache(id));
        Mapper<BillDTO, Bill> billMapper = new BillToDTOMapper();
        if (billArray.isPresent() && !billArray.isEmpty()) {
            log.info("Get success bill with id: "+ id);
            BillDTO bill = (BillDTO) SerializationUtils.deserialize(billArray.get());
            bill.setOutTime(new Date());
            Bill result = billRepository.save(billMapper.mapToEntity(bill));
            if(result != null){
                log.info("Payment successful");
                cacheBillService.removeFromCache(id);
                return true;
            }else{
                log.info("Payment failure");
                return false;
            }
        } else {
            log.info("Bill with id not exist: "+ id);
            return false;
        }
    }
}
