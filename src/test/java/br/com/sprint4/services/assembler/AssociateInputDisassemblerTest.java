package br.com.sprint4.services.assembler;

import br.com.sprint4.dto.request.AssociateInputDTO;
import br.com.sprint4.entity.Associate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static br.com.sprint4.utils.mocks.AssociateMocks.getAssociateInputWithOutPartyMock;
import static br.com.sprint4.utils.mocks.AssociateMocks.getAssociateWithOutPartyMock;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AssociateInputDisassemblerTest {

    @InjectMocks
    private AssociateInputDisassembler disassembler;

    @Spy
    private ModelMapper mapper;

    private AssociateInputDTO associateInputMock = getAssociateInputWithOutPartyMock();
    private Associate associateMock = getAssociateWithOutPartyMock();

    @Test
    void toDomainObject_Success() {
        var associateEntity = disassembler.toDomainObject(associateInputMock);
        assertEquals(associateInputMock.getName(), associateEntity.getName());
    }

    @Test
    void copyToDomainObject_Success() {
        associateInputMock.setName("Lucas");
        disassembler.copyToDomainObject(associateInputMock, associateMock);

        assertEquals("Lucas", associateMock.getName());
    }
}