package br.com.sprint4.repositories;

import br.com.sprint4.entites.Partido;
import br.com.sprint4.enums.Ideologia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Long> {
    Page<Partido> findAllByIdeologia(Ideologia ideologia, Pageable page);
}
