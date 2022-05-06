package fatec.br.tccmonolitico.controllers;

import fatec.br.tccmonolitico.services.CambioService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cambio-service-mono")
public class CambioController {

    private final CambioService cambioService;

    public CambioController(CambioService cambioService) {
        this.cambioService = cambioService;
    }
}
