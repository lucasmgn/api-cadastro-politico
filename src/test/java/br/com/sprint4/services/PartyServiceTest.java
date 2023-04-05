package br.com.sprint4.services;

import br.com.sprint4.dto.responses.PartyResponseDTO;
import br.com.sprint4.entity.Associate;
import br.com.sprint4.entity.Party;
import br.com.sprint4.enums.Ideology;
import br.com.sprint4.exceptions.EntityInUseException;
import br.com.sprint4.exceptions.PartyNotFoundException;
import br.com.sprint4.repositories.PartyRepository;
import br.com.sprint4.services.assembler.PartyDTOAssembler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static br.com.sprint4.utils.mocks.AssociateMocks.getAssociateWithPartyMock;
import static br.com.sprint4.utils.mocks.PartyMocks.getPartyMock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PartyServiceTest {

    public static final Long ID = 1L;

    @InjectMocks
    private PartyServiceImpl service;

    @Mock
    private AssociateService associateService;

    @Mock
    private PartyRepository repository;

    @Mock
    private Pageable pageable;

    @Mock
    private PartyDTOAssembler assembler;

    private final Associate associateComParty = getAssociateWithPartyMock();

    private final Party party = getPartyMock();

    private final List<Party> parties = List.of(party);

    private final PageImpl<Party> partiesPage = new PageImpl<>(parties);

    private final PartyResponseDTO partyResponseDTO = new PartyResponseDTO();

    @Test
    void should_savePartySuccess() {
        when(repository.save(any())).thenReturn(party);
        var partyCreated = service.create(party);

        assertEquals(party, partyCreated);
    }
    @Test
    void should_fetchOrFail_Success() {
        when(repository.findById(any())).thenReturn(Optional.of(party));
        var response = service.fetchOrFail(ID);

        assertEquals(party, response);
    }
    @Test
    void should_callDeleteByIdMethod_Success() {
        service.remove(ID);
        verify(repository).deleteById(any());
        verify(repository).flush();
    }

    @Test
    @SuppressWarnings({"all"})
    void should_throwPartyNotFoundException() {
        doThrow(new EmptyResultDataAccessException(1)).when(repository).deleteById(null);
        assertThrows(PartyNotFoundException.class, () -> service.remove(null));
    }

    @Test
    void should_throwEntityInUseException() {
        doThrow(new DataIntegrityViolationException("")).when(repository).deleteById(ID);
        assertThrows(EntityInUseException.class, () -> service.remove(ID));
    }

    @Test
    void should_returnListOfAssociatesOfAParty_Success() {
        when(repository.findById(any())).thenReturn(Optional.of(party));
        when(associateService.findAllAssociatesOf(party.getId())).thenReturn(List.of(associateComParty));
        var associates = service.findAllAssociatesOf(1L);

        assertEquals(List.of(associateComParty), associates);
    }

    @Test
    @SuppressWarnings({"all"})
    void should_callTheFindAllMethod_WhenTheIdeologyIsNull(){
        when(repository.findAll(any(Pageable.class))).thenReturn(partiesPage);
        when(assembler.toCollectionModel(parties)).thenReturn(List.of(partyResponseDTO));

        service.partyResponseDTOVerification(null, pageable);
        verify(repository).findAll(any(Pageable.class));
    }

    @Test
    @SuppressWarnings({"all"})
    void should_callTheFindAllByIdeologyMethod_WhenTheIdeologyNotNull(){
        when(repository.findAllByIdeology(any(),any(Pageable.class))).thenReturn(partiesPage);
        when(assembler.toCollectionModel(parties)).thenReturn(List.of(partyResponseDTO));

        service.partyResponseDTOVerification(Ideology.CENTER, pageable);
    }
}
