package br.com.sprint4.services;

import br.com.sprint4.dto.responses.PartyResponseDTO;
import br.com.sprint4.entity.Associate;
import br.com.sprint4.entity.Party;
import br.com.sprint4.enums.Ideology;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PartyService {

    Party create(Party party);
    void remove(Long partyId);
    Party fetchOrFail(Long partyId);
    List<Associate> findAllAssociatesOf(Long partyId);
    List<PartyResponseDTO> partyResponseDTOVerification(Ideology ideology, Pageable pageable);
}
