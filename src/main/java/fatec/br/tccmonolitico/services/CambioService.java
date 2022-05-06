package fatec.br.tccmonolitico.services;

import fatec.br.tccmonolitico.respositories.CambioRepository;
import org.springframework.stereotype.Service;

@Service
public class CambioService {

    private final CambioRepository cambioRepository;

    public CambioService(CambioRepository cambioRepository) {
        this.cambioRepository = cambioRepository;
    }

    //TODO métodos
}
