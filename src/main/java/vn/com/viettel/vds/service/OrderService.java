package vn.com.viettel.vds.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;
import org.springframework.web.client.RestTemplate;
import vn.com.viettel.vds.dto.BillDTO;
import vn.com.viettel.vds.dto.FoodDTO;
import vn.com.viettel.vds.dto.OrderDTO;
import vn.com.viettel.vds.exception.FoodNotFoundException;
import vn.com.viettel.vds.validator.FoodValidator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class OrderService {

    //url for public api
    private final String API_URL = "https://my-json-server.typicode.com/hoanglammaster/db_json_server/listFood/";
    private final RestTemplate restTemplate;
    private final FoodValidator foodValidator;
    private final CacheBillService cacheBillService;

    @Autowired
    public OrderService(RestTemplate restTemplate, FoodValidator foodValidator, CacheBillService cacheBillService) {
        this.restTemplate = restTemplate;
        this.foodValidator = foodValidator;
        this.cacheBillService = cacheBillService;
    }

    /**
     * => get food from public api using rest template
     * => create new Bill or update exist Bill to cache
     * @param order
     * @return Optional.empty() if food id not valid
     *  Optional.empty() if food id not exist
     */
    public Optional<FoodDTO> getFoodFromPublicApi(OrderDTO order) {
        if(foodValidator.isFoodIdValid(order.getFoodId())) {
            Optional<FoodDTO> food = Optional.of(restTemplate.getForObject(API_URL + order.getFoodId(), FoodDTO.class));
            if (food.isPresent()
                    && !food.isEmpty()
                    && foodValidator.isFoodExist(food.get())
            ) {
                log.info("Get food from api success");
                addBillToCache(order.getTableId(), food.get().getPrice());
            } else {
                throw new FoodNotFoundException(String.format("Food %s not found!", order.getFoodId()));
            }
            return food;
        }else{
            return Optional.empty();
        }
    }

    /**
     *  => check if exist tableId in cache -> update price of Bill to cache
     *  => not exist -> create new Bill and add to cache
     * @param tableId
     * @param foodPrice
     */
    private void addBillToCache(Integer tableId, BigDecimal foodPrice) {
        Optional<byte[]> billArray = Optional.ofNullable(cacheBillService.getFromCache(tableId));
        if (billArray.isPresent() && !billArray.isEmpty()) {
            BillDTO bill = (BillDTO) SerializationUtils.deserialize(billArray.get());
            bill.setTotalPrice(bill.getTotalPrice().add(foodPrice));
            cacheBillService.setToCache(bill);
        } else {
            cacheBillService.setToCache(new BillDTO(tableId, new Date(), null, foodPrice));
        }
    }


}
