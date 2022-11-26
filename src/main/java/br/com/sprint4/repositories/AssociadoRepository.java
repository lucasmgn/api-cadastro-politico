package br.com.sprint4.repositories;

import br.com.sprint4.entites.Associado;
import br.com.sprint4.enums.Cargo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long> {
    List<Associado> findAllByPartido_id(Long Id);

    Page<Associado> findAllByCargo(Cargo cargo, Pageable pageable);

}
