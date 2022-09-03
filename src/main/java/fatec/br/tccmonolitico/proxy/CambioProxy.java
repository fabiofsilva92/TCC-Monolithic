package fatec.br.tccmonolitico.proxy;

import fatec.br.tccmonolitico.entities.Cambio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.openfeign.FeignClient;

import javax.validation.Valid;
import java.util.List;

@FeignClient(url="${aws.nomePodCambioService}", name = "cambio-service-mono")
public interface CambioProxy {

    @GetMapping(value = "/cambio-service-mono/{amount}/{from}/{to}")
    public Cambio getCambio(@PathVariable("amount") Double amount, @PathVariable("from") String from,
                            @PathVariable("to") String to);

    @GetMapping(value = "/cambio-service-mono/{id}")
    Cambio findById(@PathVariable Long id) ;

    @GetMapping("/cambio-service-mono")
    List<Cambio> findAll() ;

    @GetMapping(value = "/cambio-service-mono/time")
    List<Cambio> findAllTimeProcessed() ;

    @PostMapping("/cambio-service-mono")
    Cambio create(@Valid @RequestBody Cambio obj) ;

    @PutMapping(value = "/cambio-service-mono/{id}")
    Cambio update(@Valid @PathVariable Long id, @RequestBody Cambio objDto);

    @DeleteMapping(value = "/cambio-service-mono/{id}")
    Void delete(@PathVariable Long id);

    @GetMapping(value = "/cambio-service-mono/delete-mocks")
    ResponseEntity<Void>  deletemocks();

    @GetMapping(value = "/cambio-service-mono/get-mocks")
    List<Cambio>  getMocks();


}