package br.com.sprint4.service;

import br.com.sprint4.entites.Associado;
import br.com.sprint4.entites.Partido;
import br.com.sprint4.enums.Cargo;
import br.com.sprint4.enums.Ideologia;
import br.com.sprint4.enums.Sexo;
import br.com.sprint4.repositories.AssociadoRepository;
import br.com.sprint4.services.AssociadoService;
import br.com.sprint4.services.PartidoService;
import br.com.sprint4.services.assembler.AssociadoDTOAssembler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
    private AssociadoDTOAssembler assembler;

    private Associado associado;

    private Partido partido;

   public Partido novoPartido() {
        partido = new Partido();
        partido.setId(1L);
        partido.setNome("Teste");
        partido.setSigla("PM");
        partido.setIdeologia(Ideologia.CENTRO);
        return partido;
    }


    public Associado novoAssociado() {
        associado = new Associado();
        associado.setId(1L);
        associado.setNome("Teste");
        associado.setCargo(Cargo.GOVERNADOR);
        associado.setSexo(Sexo.MASCULINO);
        associado.setPartido(partido);
        return associado;
    }

    @Test
    void deveSalvarUmAssociado() {
        Associado associado1 = novoAssociado();
        when(repository.save(any())).thenReturn(associado1);
        Associado adicionar = service.adicionar(associado1);
        assertEquals(associado1, adicionar);
        verify(repository).save(any());
    }

    @Test
    void deveChamarOMetodoExcluir() {
        Associado associado1 = novoAssociado();

        service.excluir(ID);

        verify(repository).deleteById(any());
        verify(repository).flush();
    }

    @Test
    void deveBuscarOuFalhar() {
        Associado associado1 = novoAssociado();
        when(repository.findById(any())).thenReturn(Optional.of(associado1));
        Associado adicionar = service.buscaOuFalha(associado1.getId());
        assertEquals(associado1, adicionar);
        verify(repository).findById(any());
    }

    @Test
    void deveVincularUmAssociado() {
        Associado associado1 = novoAssociado();
        Partido partido1 = novoPartido();

        when(repository.findById(any())).thenReturn(Optional.of(associado1));
        Associado associadoExiste = service.buscaOuFalha(associado1.getId());
        when(partidoService.buscaOuFalha(any())).thenReturn(partido1);
        associadoExiste.setPartido(partido1);

        service.vincular(associado.getId(), partido.getId());
        Assertions.assertEquals(associado1.getPartido().getId(), partido1.getId());
    }

    @Test
    void deveDesvincularUmAssociado() {
        Associado associado1 = novoAssociado();
        Partido partido1 = novoPartido();

        when(repository.findById(any())).thenReturn(Optional.of(associado1));

        Associado associadoExiste = service.buscaOuFalha(associado1.getId());

        when(repository.findById(any())).thenReturn(Optional.of(associado1));

        associadoExiste.setPartido(partido1);

        service.desvincular(associado.getId());

        Assertions.assertNull(associado1.getPartido());
    }

}
