package br.com.sprint4.services.assembler;

import br.com.sprint4.entites.Associado;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

import static br.com.sprint4.utils.mocks.AssociadoMocks.getAssociadoSemPartidoMock;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AssociadoDTOAssemblerTest {

    @InjectMocks
    private AssociadoDTOAssembler assembler;

    @Spy
    private ModelMapper mapper;

    private Associado associado = getAssociadoSemPartidoMock();

    @Test
    void toModel_Sucess() {
        var associadoResponseDTO = assembler.toModel(associado);
        assertEquals(associado.getNome(), associadoResponseDTO.getNome());
    }

    @Test
    void toModelresponse() {
        var associadoResumoResponseDTO = assembler.toModelResponse(associado);
        assertEquals(associado.getNome(), associadoResumoResponseDTO.getNome());
    }

    @Test
    void toCollectionModel() {
        var associados = List.of(associado);
        var associadoResponseDTOS = assembler.toCollectionModel(associados);
        assertEquals(associados.get(0).getNome(), associadoResponseDTOS.get(0).getNome());
    }

    @Test
    void toCollectionModelresponse() {
        var associados = List.of(associado);
        var associadoResponseDTOS = assembler.toCollectionModelResponse(associados);
        assertEquals(associados.get(0).getNome(), associadoResponseDTOS.get(0).getNome());
    }
}