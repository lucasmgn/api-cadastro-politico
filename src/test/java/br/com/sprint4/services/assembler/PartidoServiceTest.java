package br.com.sprint4.services.assembler;

import br.com.sprint4.entites.Associado;
import br.com.sprint4.entites.Partido;
import br.com.sprint4.enums.Ideologia;
import br.com.sprint4.exceptions.EntidadeEmUsoException;
import br.com.sprint4.exceptions.PartidoNaoEncontradoException;
import br.com.sprint4.repositories.PartidoRepository;
import br.com.sprint4.services.AssociadoService;
import br.com.sprint4.services.PartidoService;
import br.com.sprint4.services.dto.responses.PartidoResponseDTO;
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

import static br.com.sprint4.utils.mocks.AssociadoMocks.getAssociadoComPartidoMock;
import static br.com.sprint4.utils.mocks.PartidoMocks.getPartidoMock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PartidoServiceTest {

    public static final Long ID = 1L;

    @InjectMocks
    private PartidoService service;

    @Mock
    private AssociadoService associadoService;

    @Mock
    private PartidoRepository repository;

    @Mock
    private Pageable pageable;

    @Mock
    private PartidoDTOAssembler assembler;

    private Associado associadoComPartido = getAssociadoComPartidoMock();

    private Partido partido = getPartidoMock();

    @Test
    void deveSalvarUmPartido() {
        when(repository.save(any())).thenReturn(partido);
        var adicionar = service.adicionar(partido);

        assertEquals(partido, adicionar);
    }
    @Test
    void testarBuscaOuFalhaPartido() {
        when(repository.findById(any())).thenReturn(Optional.of(partido));
        var response = service.buscaOuFalha(ID);

        assertEquals(partido, response);
    }
    @Test
    void deveChamarOMetodoExcluir_Sucess() {
        service.excluir(ID);
        verify(repository).deleteById(any());
        verify(repository).flush();
    }

    @Test
    @SuppressWarnings({"all"})
    void deveChamarOMetodoExcluir_PartidoNaoEncontradoException() {
        doThrow(new EmptyResultDataAccessException(1)).when(repository).deleteById(null);
        assertThrows(PartidoNaoEncontradoException.class, () -> service.excluir(null));
    }

    @Test
    void deveChamarOMetodoExcluir_EntidadeEmUsoException() {
        doThrow(new DataIntegrityViolationException("")).when(repository).deleteById(ID);
        assertThrows(EntidadeEmUsoException.class, () -> service.excluir(ID));
    }

    @Test
    void listarAssociadosDeUmPartido_Sucess() {
        when(repository.findById(any())).thenReturn(Optional.of(partido));
        when(associadoService.listarAssociados(partido.getId())).thenReturn(List.of(associadoComPartido));
        var associados = service.listarAssociadosDeUmPartido(1L);

        assertEquals(List.of(associadoComPartido), associados);
    }

    @Test
    void verificacaoPartidoresponseDTOIdeologyNull(){
        var partidos = List.of(partido);
        var partidosPage = new PageImpl<>(partidos);
        var partidoResponseDTO = new PartidoResponseDTO();

        when(repository.findAll(any(Pageable.class))).thenReturn(partidosPage);
        when(assembler.toCollectionModel(partidos)).thenReturn(List.of(partidoResponseDTO));
        service.verificacaoPartidoresponseDTO(null, pageable);
        verify(repository).findAll(any(Pageable.class));
    }

    @Test
    void verificacaoPartidoresponseDTOIdeologyNotNull(){
        var partidos = List.of(partido);
        var partidosPage = new PageImpl<>(partidos);
        var partidoResponseDTO = new PartidoResponseDTO();

        when(repository.findAllByIdeologia(any(),any(Pageable.class))).thenReturn(partidosPage);
        when(assembler.toCollectionModel(partidos)).thenReturn(List.of(partidoResponseDTO));

        service.verificacaoPartidoresponseDTO(Ideologia.CENTRO, pageable);
    }
}
