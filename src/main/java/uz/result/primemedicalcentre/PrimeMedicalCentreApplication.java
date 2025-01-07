package uz.result.primemedicalcentre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PrimeMedicalCentreApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrimeMedicalCentreApplication.class, args);
    }

}
