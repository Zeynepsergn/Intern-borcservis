package tr.gov.gib.borc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "tr.gov.gib")
@EnableFeignClients
public class BorcServisApplication {

    public static void main(String[] args) {
        SpringApplication.run(BorcServisApplication.class, args);
    }

}
