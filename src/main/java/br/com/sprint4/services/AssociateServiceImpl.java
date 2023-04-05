package br.com.sprint4.services;

import br.com.sprint4.entity.Associate;
import br.com.sprint4.enums.Office;
import br.com.sprint4.exceptions.AssociateNotFoundException;
import br.com.sprint4.exceptions.EntityInUseException;
import br.com.sprint4.repositories.AssociateRepository;
import br.com.sprint4.services.assembler.AssociateDTOAssembler;
import br.com.sprint4.dto.responses.AssociateResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssociateServiceImpl implements AssociateService{

    private final PartyService service;

    private static final String MSG_ASSOCIATE_IN_USE = "associate code %d cannot be removed as it is in use";

    private final AssociateRepository repository;

    private final AssociateDTOAssembler assembler;

    @Override
    @Transactional
    public Associate create(Associate associate) {
        return repository.save(associate);
    }

    @Override
    @Transactional
    public void bind(Long associateId, Long partyId) {
        var associateReturn = fetchOrFail(associateId);
        var partyReturn = service.fetchOrFail(partyId);

        associateReturn.setParty(partyReturn);
    }

    @Override
    @Transactional
    public void unbind(Long associateId) {
        var associateReturn = fetchOrFail(associateId);

        associateReturn.setParty(null);
    }

    @Override
    @Transactional
    public void remove(Long associateId) {
        try {
            repository.deleteById(associateId);
            repository.flush();

        }catch (EmptyResultDataAccessException e) {
            throw new AssociateNotFoundException();
        }catch (DataIntegrityViolationException e) { //erro se tentar excluir associate q está em uso
            throw new EntityInUseException(
                    String.format(MSG_ASSOCIATE_IN_USE, associateId));
        }
    }

    @Override
    public List<Associate> findAllAssociatesOf(Long partyId){
        return repository.findAllByPartyId(partyId);
    }

    @Override
    public Associate fetchOrFail(Long associateId) {
        return repository.findById(associateId)
                .orElseThrow(AssociateNotFoundException::new);
    }

    @Override
    public List<AssociateResponseDTO> associateResponseDTOVerification(Office office, Pageable pageable) {
        if(office == null){
            var associatesPage = repository.findAll(pageable);
            return assembler.toCollectionModel(associatesPage.getContent());
        }else{
            var associates = repository.findAllByOffice(office, pageable).getContent();
            return assembler.toCollectionModel(associates);
        }
    }
}
