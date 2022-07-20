package fatec.br.tccmonolitico.controllers;

import fatec.br.tccmonolitico.dtos.CambioDTO;
import fatec.br.tccmonolitico.entities.Cambio;
import fatec.br.tccmonolitico.services.CambioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cambio-service-mono")
public class CambioController {

    @Autowired
    private Environment environment;

    @Autowired
    private CambioService cambioService;

    @GetMapping(value = "/{amount}/{from}/{to}")
    public Cambio getCambio(@PathVariable("amount") Double amount,
                            @PathVariable("from") String from,
                            @PathVariable("to") String to) {

        Cambio cambio = cambioService.getCambio(from, to);

        if(cambio == null) throw new RuntimeException("Currency Unsupported");

        var port = environment.getProperty("local.server.port");
        Double conversionFactor = cambio.getConversionFactor();
        Double convertValue = conversionFactor * amount;
        cambio.setConvertedValue(convertValue);
        cambio.setEnvironment(port);

        return cambio;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cambio> findById(@PathVariable Long id) {

        Cambio obj = cambioService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<Cambio>> findAll() {
        List<Cambio> listDTO = cambioService.getListOfCambio();
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping(value = "/time")
    public ResponseEntity<List<Cambio>> findAllTimeProcessed() {
        Long start = System.nanoTime();
        List<Cambio> listDTO = cambioService.getListOfCambio();
        Long end = System.nanoTime();
        System.out.println("Tempo passado dentro no metodo :" + (end-start) + " nanossegundos");
        System.out.println("Tempo passado dentro no metodo segundos :" + (end-start)*(Math.pow(10, -9)) + "s");
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<Cambio> create(@Valid @RequestBody Cambio obj) {
        obj = cambioService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CambioDTO> update(@Valid @PathVariable Long id, @RequestBody CambioDTO objDto) {


        Cambio newObj = cambioService.update(id, objDto);

        return ResponseEntity.ok().body(new CambioDTO(newObj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cambioService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
