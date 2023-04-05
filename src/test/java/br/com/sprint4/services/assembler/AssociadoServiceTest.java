package br.com.sprint4.services.assembler;

import br.com.sprint4.entites.Associado;
import br.com.sprint4.entites.Partido;
import br.com.sprint4.enums.Cargo;
import br.com.sprint4.exceptions.AssociadoNaoEncontradoException;
import br.com.sprint4.exceptions.EntidadeEmUsoException;
import br.com.sprint4.repositories.AssociadoRepository;
import br.com.sprint4.services.AssociadoService;
import br.com.sprint4.services.PartidoService;
import br.com.sprint4.services.dto.responses.AssociadoResponseDTO;
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
import static br.com.sprint4.utils.mocks.AssociadoMocks.getAssociadoSemPartidoMock;
import static br.com.sprint4.utils.mocks.PartidoMocks.getPartidoMock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AssociadoServiceTest {

    public static final Long ID = 1L;

    @InjectMocks
    private AssociadoService service;

    @Mock
    private PartidoService partidoService;

    @Mock
    private AssociadoRepository repository;

    @Mock
    private Pageable pageable;

    @Mock
    private AssociadoDTOAssembler assembler;

    private Associado associadoComPartido = getAssociadoComPartidoMock();
    private Associado associadoSemPartido = getAssociadoSemPartidoMock();

    private Partido partido = getPartidoMock();

    @Test
    void deveSalvarUmAssociado() {
        when(repository.save(any())).thenReturn(getAssociadoComPartidoMock());
        var associadoAdicionado = service.adicionar(getAssociadoComPartidoMock());

        assertEquals(getAssociadoComPartidoMock(), associadoAdicionado);
        verify(repository).save(any());
    }

    @Test
    void deveChamarOMetodoExcluir() {
        service.excluir(ID);
        verify(repository).deleteById(any());
        verify(repository).flush();
    }

    @Test
    @SuppressWarnings({"all"})
    void deveChamarOMetodoExcluirEFalharNaoAchandoResultado() {
        doThrow(new EmptyResultDataAccessException(1)).when(repository).deleteById(null);
        assertThrows(AssociadoNaoEncontradoException.class, () -> service.excluir(null));
    }

    @Test
    void deveChamarOMetodoExcluirAssociadoEmUso() {
        doThrow(new DataIntegrityViolationException("")).when(repository).deleteById(ID);
        assertThrows(EntidadeEmUsoException.class, () -> service.excluir(ID));
    }

    @Test
    void listarAssociadosSucess() {
        when(repository.findAllByPartido_id(any())).thenReturn(List.of(getAssociadoComPartidoMock()));
        var associados = service.listarAssociados(ID);

        assertEquals(List.of(getAssociadoComPartidoMock()), associados);
    }

    @Test
    void deveBuscarOuFalhar() {
        when(repository.findById(any())).thenReturn(Optional.of(getAssociadoComPartidoMock()));
        var associadoRetornado = service.buscaOuFalha(getAssociadoComPartidoMock().getId());

        assertEquals(getAssociadoComPartidoMock(), associadoRetornado);
    }

    @Test
    void deveVincularUmAssociado() {
        when(repository.findById(any())).thenReturn(Optional.of(associadoSemPartido));
        var associadoExiste = service.buscaOuFalha(associadoSemPartido.getId());

        when(partidoService.buscaOuFalha(any())).thenReturn(partido);
        associadoExiste.setPartido(partido);

        System.out.println(associadoSemPartido);
        service.vincular(associadoSemPartido.getId(), this.partido.getId());
        assertEquals(associadoSemPartido.getPartido().getId(), partido.getId());
    }

    @Test
    void deveDesvincularUmAssociadoDeUmPartido() {
        when(repository.findById(any())).thenReturn(Optional.of(associadoComPartido));
        service.desvincular(associadoComPartido.getId());

        assertNull(associadoComPartido.getPartido());
    }

    @Test
    void verificacaoAssociadoresponseDTOCargoIsNull() {
        var associados = List.of(associadoComPartido);
        var associadoPage = new PageImpl<>(associados);
        var associadoResponseDTO = new AssociadoResponseDTO();

        when(repository.findAll(any(Pageable.class))).thenReturn(associadoPage);
        when(assembler.toCollectionModel(associados)).thenReturn(List.of(associadoResponseDTO));

        service.verificacaoAssociadoresponseDTO(null, pageable);
    }

    @Test
    void verificacaoAssociadoresponseDTOCargoNotNull() {
        var associados = List.of(associadoComPartido);
        var associadoPage = new PageImpl<>(associados);
        var associadoResponseDTO = new AssociadoResponseDTO();

        when(repository.findAllByCargo(any(), any(Pageable.class))).thenReturn(associadoPage);
        when(assembler.toCollectionModel(associados)).thenReturn(List.of(associadoResponseDTO));

        service.verificacaoAssociadoresponseDTO(Cargo.GOVERNADOR, pageable);
    }
}
