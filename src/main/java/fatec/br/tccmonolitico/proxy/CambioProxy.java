package fatec.br.tccmonolitico.proxy;

import fatec.br.tccmonolitico.entities.Cambio;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(url = "http://localhost:8081",name = "cambio-service-mono")
public interface CambioProxy {

    @GetMapping(value = "/cambio-service-mono/{amount}/{from}/{to}")
    public Cambio getCambio(@PathVariable("amount") Double amount, @PathVariable("from") String from,
                            @PathVariable("to") String to);

}