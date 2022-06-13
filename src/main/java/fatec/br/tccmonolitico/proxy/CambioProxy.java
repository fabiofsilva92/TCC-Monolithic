package fatec.br.tccmonolitico.proxy;

import fatec.br.tccmonolitico.dtos.CambioDTO;
import fatec.br.tccmonolitico.entities.Cambio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.openfeign.FeignClient;

import javax.validation.Valid;
import java.util.List;

@FeignClient(url = "http://localhost:8081",name = "cambio-service-mono")
public interface CambioProxy {

    @GetMapping(value = "/cambio-service-mono/{amount}/{from}/{to}")
    public Cambio getCambio(@PathVariable("amount") Double amount, @PathVariable("from") String from,
                            @PathVariable("to") String to);

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cambio> findById(@PathVariable Long id) ;

    @GetMapping("/cambio-service-mono")
    public ResponseEntity<List<Cambio>> findAll() ;
    @GetMapping(value = "/time")
    public ResponseEntity<List<Cambio>> findAllTimeProcessed() ;

    @PostMapping("/cambio-service-mono")
    public ResponseEntity<Cambio> create(@Valid @RequestBody Cambio obj) ;
    @PutMapping(value = "/{id}")
    public ResponseEntity<CambioDTO> update(@Valid @PathVariable Long id, @RequestBody CambioDTO objDto);

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id);


}