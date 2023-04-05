package br.com.sprint4.repositories;

import br.com.sprint4.entity.Associate;
import br.com.sprint4.enums.Office;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssociateRepository extends JpaRepository<Associate, Long> {
    List<Associate> findAllByPartyId(Long id);

    Page<Associate> findAllByOffice(Office office, Pageable pageable);

}
