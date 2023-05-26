package br.com.sprint4.services;

import br.com.sprint4.entity.Associate;
import br.com.sprint4.entity.Party;
import br.com.sprint4.enums.Ideology;
import br.com.sprint4.exceptions.EntityInUseException;
import br.com.sprint4.exceptions.PartyNotFoundException;
import br.com.sprint4.dto.responses.PartyResponseDTO;
import br.com.sprint4.repositories.AssociateRepository;
import br.com.sprint4.repositories.PartyRepository;
import br.com.sprint4.services.assembler.PartyDTOAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PartyServiceImpl implements PartyService{
    private static final String MSG_PARTY_IN_USE = "Party code %d cannot be removed as it is in use";

    private final PartyRepository repository;

    private final PartyDTOAssembler assembler;

    private final AssociateRepository associateRepository;

    @Transactional
    @Override
    public Party create(Party party) {
        return repository.save(party);
    }

    @Transactional
    @Override
    public void remove(Long partyId) {
        try {
            repository.deleteById(partyId);
            repository.flush();

        }catch (EmptyResultDataAccessException e) {
            throw new PartyNotFoundException();
        }catch (DataIntegrityViolationException e) { //erro se tentar excluir associate q está em uso
            throw new EntityInUseException(String.format(MSG_PARTY_IN_USE, partyId));
        }
    }

    @Override
    public Party fetchOrFail(Long partyId) {
        return repository.findById(partyId)
                .orElseThrow(PartyNotFoundException::new);
    }

    @Override
    public List<Associate> findAllAssociatesOf(Long partyId){
        fetchOrFail(partyId);
        return associateRepository.findAllByPartyId(partyId);
    }

    @Override
    public List<PartyResponseDTO> partyResponseDTOVerification(Ideology ideology, Pageable pageable) {
        if(ideology == null){
            var partiesPage = repository.findAll(pageable);
            return assembler.toCollectionModel(partiesPage.getContent());
        }else{
            var parties = repository.findAllByIdeology(ideology, pageable).getContent();
            return assembler.toCollectionModel(parties);
        }
    }
}
