package br.com.sprint4.repositories;

import br.com.sprint4.entity.Party;
import br.com.sprint4.enums.Ideology;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {
    Page<Party> findAllByIdeology(Ideology ideology, Pageable page);
}
