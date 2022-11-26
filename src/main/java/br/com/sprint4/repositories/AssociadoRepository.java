package br.com.sprint4.repositories;

import br.com.sprint4.entites.Associado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long> {
    List<Associado> findAllByPartido_id(Long Id);

}
