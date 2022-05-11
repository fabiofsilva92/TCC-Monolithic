package fatec.br.tccmonolitico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TccMonoliticoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TccMonoliticoApplication.class, args);
    }

}
