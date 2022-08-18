package fatec.br.tccmonolitico.services;


import fatec.br.tccmonolitico.entities.Cambio;
import fatec.br.tccmonolitico.respositories.CambioRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CambioCrudService {

	@Autowired
	private CambioRepository cambioCrudRepository;

	public Cambio findById(Long id) {

		Optional<Cambio> obj = cambioCrudRepository.findById(id);

		return obj.orElseThrow(RuntimeException::new);
	}

	public List<Cambio> findAll() {
		return cambioCrudRepository.findAll();
	}

	public List<Cambio> getListOfCambio(){
		List<Cambio> cambios = cambioCrudRepository.findAll();
		return cambios;
	}

	public Cambio create(Cambio obj) {
		obj.setId(null);
		return cambioCrudRepository.save(obj);
	}

	public Cambio update(Long id, Cambio objDto) {
		Cambio obj = findById(id);
		objDto.setId(obj.getId());
		obj = objDto;
		return cambioCrudRepository.save(obj);
	}

	public void delete(Long id) {
		findById(id);

		try {
			cambioCrudRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) { // esta exceção é do spring
			throw new DataIntegrityViolationException(// adicionada esta exceção
																						// personalizada
					"Categoria não pode ser deletado! Possui livros associados");
		}
	}

    public void deleteMocks() {
		List<Cambio> cambios = cambioCrudRepository.getCambioMocks();
		for (int i = 0; i < cambios.size(); i++) {
			cambioCrudRepository.deleteById(cambios.get(i).getId());
		}
    }

	public List<Cambio> getCambiosMock() {
		return cambioCrudRepository.getCambioMocks();
	}
}
