package br.com.sprint4.services;

import br.com.sprint4.dto.responses.AssociateResponseDTO;
import br.com.sprint4.entity.Associate;
import br.com.sprint4.entity.Party;
import br.com.sprint4.enums.Office;
import br.com.sprint4.exceptions.AssociateNotFoundException;
import br.com.sprint4.exceptions.EntityInUseException;
import br.com.sprint4.repositories.AssociateRepository;
import br.com.sprint4.services.assembler.AssociateDTOAssembler;
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

import static br.com.sprint4.utils.mocks.AssociateMocks.getAssociateWithOutPartyMock;
import static br.com.sprint4.utils.mocks.AssociateMocks.getAssociateWithPartyMock;
import static br.com.sprint4.utils.mocks.PartyMocks.getPartyMock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AssociateServiceTest {

    public static final Long ID = 1L;

    @InjectMocks
    private AssociateServiceImpl service;

    @Mock
    private PartyService partyService;

    @Mock
    private AssociateRepository repository;

    @Mock
    private Pageable pageable;

    @Mock
    private AssociateDTOAssembler assembler;

    private final Associate associateWithParty = getAssociateWithPartyMock();
    private final Associate associateWithOutParty = getAssociateWithOutPartyMock();
    private final Party party = getPartyMock();
    private final List<Associate> associates = List.of(associateWithParty);
    private final PageImpl<Associate> associatePage = new PageImpl<>(associates);
    private final AssociateResponseDTO associateResponseDTO = new AssociateResponseDTO();

    @Test
    void should_saveAssociateSuccess() {
        when(repository.save(any())).thenReturn(associateWithParty);
        var associate = service.create(associateWithParty);

        assertEquals(associateWithParty, associate);
        verify(repository).save(any());
    }

    @Test
    void should_callDeleteByIdMethod_Success() {
        service.remove(ID);
        verify(repository).deleteById(any());
        verify(repository).flush();
    }

    @Test
    @SuppressWarnings({"all"})
    void should_throwAssociateNotFoundException() {
        doThrow(new EmptyResultDataAccessException(1)).when(repository).deleteById(null);
        assertThrows(AssociateNotFoundException.class, () -> service.remove(null));
    }

    @Test
    void should_throwEntityInUseException() {
        doThrow(new DataIntegrityViolationException("")).when(repository).deleteById(ID);
        assertThrows(EntityInUseException.class, () -> service.remove(ID));
    }

    @Test
    void should_returnAllMembersOfAParty_Success() {
        when(repository.findAllByPartyId(any())).thenReturn(List.of(associateWithParty));
        var associates = service.findAllAssociatesOf(ID);

        assertEquals(List.of(associateWithParty), associates);
    }

    @Test
    void should_fetchOrFail_Success() {
        when(repository.findById(any())).thenReturn(Optional.of(associateWithParty));
        var associate = service.fetchOrFail(associateWithParty.getId());

        assertEquals(associateWithParty, associate);
    }

    @Test
    void should_linkAnAssociate_Success() {
        when(repository.findById(any())).thenReturn(Optional.of(associateWithOutParty));
        var associate = service.fetchOrFail(associateWithOutParty.getId());

        when(partyService.fetchOrFail(any())).thenReturn(party);
        associate.setParty(party);

        System.out.println(associateWithOutParty);
        service.bind(associateWithOutParty.getId(), this.party.getId());
        assertEquals(associateWithOutParty.getParty().getId(), party.getId());
    }

    @Test
    void should_unbindAssociateOfParty_Success() {
        when(repository.findById(any())).thenReturn(Optional.of(associateWithParty));
        service.unbind(associateWithParty.getId());

        assertNull(associateWithParty.getParty());
    }

    @Test
    @SuppressWarnings({"all"})
    void should_callFindAllMethod_WhenOfficeIsNull() {
        when(repository.findAll(any(Pageable.class))).thenReturn(associatePage);
        when(assembler.toCollectionModel(associates)).thenReturn(List.of(associateResponseDTO));

        service.associateResponseDTOVerification(null, pageable);
    }

    @Test
    @SuppressWarnings({"all"})
    void should_callFindAllByOfficeMethod_WhenOfficeNotNull() {
        when(repository.findAllByOffice(any(), any(Pageable.class))).thenReturn(associatePage);
        when(assembler.toCollectionModel(associates)).thenReturn(List.of(associateResponseDTO));

        service.associateResponseDTOVerification(Office.GOVERNOR, pageable);
    }
}
