package br.com.sprint4.services.assembler;

import br.com.sprint4.dto.request.PartyInputDTO;
import br.com.sprint4.entity.Party;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static br.com.sprint4.utils.mocks.PartyMocks.getPartyInputMock;
import static br.com.sprint4.utils.mocks.PartyMocks.getPartyMock;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PartyInputDisassemblerTest {

    @InjectMocks
    private PartyInputDisassembler disassembler;

    @Spy
    private ModelMapper mapper;

    private PartyInputDTO partyInputMock = getPartyInputMock();
    private Party partyMock = getPartyMock();

    @Test
    void toDomainObject() {
        var partyEntity = disassembler.toDomainObject(partyInputMock);
        assertEquals(partyInputMock.getName(), partyEntity.getName());
    }
    @Test
    void copyToDomainObject() {
        partyInputMock.setName("Lucas");
        disassembler.copyToDomainObject(partyInputMock, partyMock);

        assertEquals("Lucas", partyMock.getName());
    }
}