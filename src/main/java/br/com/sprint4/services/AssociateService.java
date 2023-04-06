package br.com.sprint4.services;

import br.com.sprint4.dto.responses.AssociateResponseDTO;
import br.com.sprint4.entity.Associate;
import br.com.sprint4.enums.Office;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AssociateService {

    Associate create(Associate associate);

    void bind(Long associateId, Long partyId);

    void unbind(Long associateId);

    void remove(Long associateId);

    List<Associate> findAllAssociatesOf(Long partyId);

    Associate fetchOrFail(Long associateId);

    List<AssociateResponseDTO> associateResponseDTOVerification(Office office, Pageable pageable);
}
