package vn.com.viettel.vds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import vn.com.viettel.vds.dto.BillDTO;
import vn.com.viettel.vds.model.converter.BillToDTOConverter;
import vn.com.viettel.vds.model.converter.Converter;
import vn.com.viettel.vds.model.entity.secondarydb.Bill;
import vn.com.viettel.vds.model.mapper.JsonMapper;
import vn.com.viettel.vds.repository.defaults.TableRepository;
import vn.com.viettel.vds.repository.secondary.BillRepository;

@SpringBootApplication
public class Main implements CommandLineRunner{

    @Autowired
    private BillRepository billRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private TableRepository tableRepository;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String uri = "https://my-json-server.typicode.com/hoanglammaster/db_json_server/listFood/1000";
        ResponseEntity<String> response
                = restTemplate.getForEntity(uri , String.class);
        System.out.println(response.getBody());

        JsonMapper<BillDTO> billJsonMapper = new JsonMapper<>();
        Converter<BillDTO,Bill> billDTOConverter = new BillToDTOConverter();
        billRepository.findAll().stream().forEach(bill -> {
            System.out.println(billJsonMapper.objectToJson(billDTOConverter.convert(bill)));;
        });

        tableRepository.findAll().stream().forEach(tables -> {
            System.out.println(tables.getName());
        });



    }
}
