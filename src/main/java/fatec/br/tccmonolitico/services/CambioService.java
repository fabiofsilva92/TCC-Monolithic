package fatec.br.tccmonolitico.services;

import fatec.br.tccmonolitico.dtos.CambioDTO;
import fatec.br.tccmonolitico.entities.Cambio;
import fatec.br.tccmonolitico.respositories.CambioRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CambioService {

    private final CambioRepository cambioRepository;

    public CambioService(CambioRepository cambioRepository) {
        this.cambioRepository = cambioRepository;
    }

    public Cambio getCambio(String from, String to){
        return this.cambioRepository.findByFromAndTo(from, to);
    }

    public Cambio findById(Long id) {

        Optional<Cambio> obj = cambioRepository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException(new Cambio(),
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cambio.class.getName()));
    }

    public List<Cambio> findAll() {
        return cambioRepository.findAll();
    }

    public List<Cambio> getListOfCambio(){
        List<Cambio> cambios = cambioRepository.findAll();
        return cambios;
    }

    public Cambio create(Cambio obj) {
        obj.setId(null);
        return cambioRepository.save(obj);
    }

    public Cambio update(Long id, CambioDTO objDto) {
        Cambio obj = findById(id);
        obj.setEnvironment(obj.getEnvironment());
        obj.setFrom(obj.getFrom());
        obj.setTo(objDto.getTo());
        obj.setConversionFactor(objDto.getConversionFactor());
        obj.setConvertedValue(objDto.getConvertedValue());
        return cambioRepository.save(obj);
    }

    public void delete(Long id) {
        findById(id);

        try {
            cambioRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) { // esta exceção é do spring
            throw new DataIntegrityViolationException(// adicionada esta exceção
                    // personalizada
                    "Categoria não pode ser deletado! Possui livros associados");
        }
    }
}
