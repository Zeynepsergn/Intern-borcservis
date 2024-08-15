package tr.gov.gib.borc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "tr.gov.gib")
public class BorcServisApplication {

    public static void main(String[] args) {
        SpringApplication.run(BorcServisApplication.class, args);
    }

}
