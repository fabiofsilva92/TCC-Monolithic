package fatec.br.tccmonolitico.controllers;

import fatec.br.tccmonolitico.entities.Cambio;
import fatec.br.tccmonolitico.respositories.CambioRepository;
import fatec.br.tccmonolitico.services.CambioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
@RequestMapping("/cambio-service-mono")
public class CambioController {

    @Autowired
    private Environment environment;

    //TODO referenciar service, arrumar findByFromAndTo
    @Autowired
    private CambioRepository repository;


    @GetMapping(value = "/{amount}/{from}/{to}")
    public Cambio getCambio(@PathVariable("amount") Double amount, @PathVariable("from") String from,
                            @PathVariable("to") String to) {

        Cambio cambio = repository.findByFromAndTo(from, to);

        if(cambio == null) throw new RuntimeException("Currency Unsupported");

        var port = environment.getProperty("local.server.port");
        Double conversionFactor = cambio.getConversionFactor();
        Double convertValue = conversionFactor * amount;
        cambio.setConvertedValue(convertValue);
        cambio.setEnvironment(port);

        return cambio;
    }
}
