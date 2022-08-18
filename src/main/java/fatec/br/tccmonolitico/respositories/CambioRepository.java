package fatec.br.tccmonolitico.respositories;

import fatec.br.tccmonolitico.entities.Cambio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CambioRepository extends JpaRepository<Cambio, Long> {

    Cambio findByFromAndTo(String from_currency, String to_currency);

    @Query(value = "SELECT c.* FROM cambio AS c WHERE c.to_currency = 'MCK'", nativeQuery = true)
    List<Cambio> getCambioMocks();
}
