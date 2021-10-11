package vn.com.viettel.vds.validator;

import org.springframework.stereotype.Service;
import vn.com.viettel.vds.dto.FoodDTO;

@Service
public class FoodValidator {

    private final Integer MIN_FOOD_ID = 999;
    private final Integer MAX_FOOD_ID = 2000;

    /**
     * => validate food id in range 999-2000
     * @param id
     * @return
     */
    public Boolean isFoodIdValid(Integer id){
        if(MIN_FOOD_ID < id && id < MAX_FOOD_ID){
            return true;
        }else{
            return false;
        }
    }

    /**
     * => validate food contain full field(name, description, price)
     * @param food
     * @return
     */
    public Boolean isFoodExist(FoodDTO food){
        if(food.getName()!= null && food.getDescription()!= null && food.getPrice() != null){
            return true;
        }else{
            return false;
        }
    }
}
