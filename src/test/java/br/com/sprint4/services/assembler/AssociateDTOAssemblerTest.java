package br.com.sprint4.services.assembler;

import br.com.sprint4.entity.Associate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

import static br.com.sprint4.utils.mocks.AssociateMocks.getAssociateWithOutPartyMock;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AssociateDTOAssemblerTest {

    @InjectMocks
    private AssociateDTOAssembler assembler;

    @Spy
    private ModelMapper mapper;

    private Associate associate = getAssociateWithOutPartyMock();

    @Test
    void toModel_Success() {
        var associateResponseDTO = assembler.toModel(associate);
        assertEquals(associate.getName(), associateResponseDTO.getName());
    }

    @Test
    void toModelResponse_Success() {
        var associateResumeResponseDTO = assembler.toModelResponse(associate);
        assertEquals(associate.getName(), associateResumeResponseDTO.getName());
    }

    @Test
    void toCollectionModel_Success() {
        var associates = List.of(associate);
        var associateResponseDTOS = assembler.toCollectionModel(associates);
        assertEquals(associates.get(0).getName(), associateResponseDTOS.get(0).getName());
    }

    @Test
    void toCollectionModelResponse_Success() {
        var associates = List.of(associate);
        var associateResponseDTOS = assembler.toCollectionModelResponse(associates);
        assertEquals(associates.get(0).getName(), associateResponseDTOS.get(0).getName());
    }
}