package vn.com.viettel.vds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Main{

//    @Autowired
//    private BillRepository billRepository;
//
//    @Autowired
//    RestTemplate restTemplate;
//
//    @Autowired
//    BillService billService;
//
//    @Autowired
//    DemoService demoService;
//
//    @Autowired
//    private TableRepository tableRepository;
//
//    @Autowired
//    private CacheManager cache;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        String uri = "https://my-json-server.typicode.com/hoanglammaster/db_json_server/listFood/1000";
//        ResponseEntity<String> response
//                = restTemplate.getForEntity(uri , String.class);
//        System.out.println(response.getBody());
//
//        demoService.setToCache(new BillDTO(100,null,null, BigDecimal.TEN));
//        demoService.setToCache(new BillDTO(101,null,null, BigDecimal.TEN));
//        demoService.setToCache(new BillDTO(102,null,null, BigDecimal.TEN));
////        demoService.setToCache(new BillDTO(100,null,null, BigDecimal.TEN));
////        demoService.setToCache(new BillDTO(100,null,null, BigDecimal.TEN));
//        BillDTO bill = (BillDTO) SerializationUtils.deserialize(demoService.getFromCache(100));
//        System.out.println(bill.getTableId());
//        BillDTO bill2 = (BillDTO) SerializationUtils.deserialize(demoService.getFromCache(102));
//        System.out.println(bill2.getTableId());
//
//
////        System.out.println(billService.getBillById(100L).getEntryTime());
////        System.out.println(billService.getBillById(100L).getEntryTime());
////        System.out.println(billService.getBillById(100L).getEntryTime());
////        System.out.println(billService.getBillById(100L).getEntryTime());
////        System.out.println(billService.getBillById(100L).getEntryTime());
////        System.out.println(billService.getBill(100L).getEntryTime());
//    }
}
