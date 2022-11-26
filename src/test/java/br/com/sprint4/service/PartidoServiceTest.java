package br.com.sprint4.service;

import br.com.sprint4.entites.Partido;
import br.com.sprint4.enums.Ideologia;
import br.com.sprint4.repositories.PartidoRepository;
import br.com.sprint4.services.PartidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PartidoServiceTest {

    @Mock
    private PartidoService service;

    private Partido partido;

    @BeforeEach void beforeEach() {
        partido = new Partido();
        partido.setId(1L);
        partido.setNome("Teste");
        partido.setSigla("PM");
        partido.setIdeologia(Ideologia.CENTRO);
    }


    @Test
    void deveSalvarUmPartido() {
        when(service.adicionar(any(Partido.class))).thenReturn(partido);
        Partido response = service.adicionar(partido);
        assertNotNull(response);
    }

    @Test
    void testarBuscaPartido() {
        when(service.buscaOuFalha(any())).thenReturn(partido);
        Partido response = service.buscaOuFalha(partido.getId());

        assertEquals(response.getId(), partido.getId());
    }

    @Test
    void testarFalhabuscarPartido() {
        Partido response = service.buscaOuFalha(10000L);
        assertNull(response);
    }

    @Test
    void testarExcluir() {
//       when(service.excluir(partido.getId()));

        Partido response = service.adicionar(partido);

        assertEquals(response.getId(), partido.getId());


    }


    @Test
    void testarAssociacao() {
        when(service.adicionar(any(Partido.class))).thenReturn(partido);

        Partido response = service.adicionar(partido);

        assertEquals(response.getId(), partido.getId());
    }

}
