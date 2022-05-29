package fatec.br.tccmonolitico.respositories;

import fatec.br.tccmonolitico.entities.Cambio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CambioRepository extends JpaRepository<Cambio, Long> {

    Cambio findByFromAndTo(String from_currency, String to_currency);
}
