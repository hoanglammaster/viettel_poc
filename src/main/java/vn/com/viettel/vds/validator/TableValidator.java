package vn.com.viettel.vds.validator;

import org.springframework.stereotype.Service;

@Service
public class TableValidator {

    private final Integer MAX_TABLE_ID = 200;
    private final Integer MIN_TABLE_ID = 0;

    /**
     * => validate table id in range 0 - 200
     * @param id
     * @return
     */
    public Boolean isTableIdValid(Integer id){
        if(MIN_TABLE_ID < id && id < MAX_TABLE_ID){
            return true;
        }else{
            return false;
        }
    }
}
