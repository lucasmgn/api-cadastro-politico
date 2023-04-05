package br.com.sprint4.services.assembler;

import br.com.sprint4.entity.Party;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

import static br.com.sprint4.utils.mocks.PartyMocks.getPartyMock;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PartyDTOAssemblerTest {

    @InjectMocks
    private PartyDTOAssembler assembler;

    @Spy
    private ModelMapper mapper;

    private Party partyMock = getPartyMock();

    @Test
    void toModel_Success() {
        var partyResponseDTO = assembler.toModel(partyMock);
        assertEquals(partyMock.getName(), partyResponseDTO.getName());
    }

    @Test
    void toCollectionModel_Success() {
        var parties = List.of(partyMock);
        var partiesResponseDTOS = assembler.toCollectionModel(parties);
        assertEquals(parties.get(0).getName(), partiesResponseDTOS.get(0).getName());
    }
}