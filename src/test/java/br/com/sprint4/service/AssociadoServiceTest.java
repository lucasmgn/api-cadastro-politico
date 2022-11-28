package br.com.sprint4.service;

import br.com.sprint4.entites.Associado;
import br.com.sprint4.entites.Partido;
import br.com.sprint4.enums.Cargo;
import br.com.sprint4.enums.Ideologia;
import br.com.sprint4.enums.Sexo;
import br.com.sprint4.services.AssociadoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AssociadoServiceTest {

    @Mock
    private AssociadoService service;

    private Associado associado;

    private Partido partido;

    @BeforeEach void beforeEachPartido() {
        partido = new Partido();
        partido.setId(1L);
        partido.setNome("Teste");
        partido.setSigla("PM");
        partido.setIdeologia(Ideologia.CENTRO);
    }

    @BeforeEach
    void beforeEach() {
        associado = new Associado();
        associado.setId(1L);
        associado.setNome("Teste");
        associado.setCargo(Cargo.GOVERNADOR);
        associado.setSexo(Sexo.MASCULINO);
        associado.setPartido(partido);
    }

    @Test
    void deveSalvarUmAssociado() {
        when(service.adicionar(any(Associado.class))).thenReturn(associado);
        Associado response = service.adicionar(associado);
        assertNotNull(response);
    }

    @Test
    void deveVincularUmAssociado() {
        service.vincular(associado.getId(), partido.getId());
        Mockito.verify(service, Mockito.times(1)).vincular(associado.getId(), partido.getId());
    }

    @Test
    void deveDesvincularUmAssociado() {
        service.desvincular(associado.getId());
        Mockito.verify(service, Mockito.times(1)).desvincular(associado.getId());
    }

    @Test
    void deveChamarOMetodoExcluir() {
        service.excluir(associado.getId());
        Mockito.verify(service, Mockito.times(1)).excluir(associado.getId());
    }

    @Test
    void deveBuscarUmAssocadi() {
        when(service.buscaOuFalha(any())).thenReturn(associado);
        Associado response = service.buscaOuFalha(associado.getId());

        assertEquals(response.getId(), associado.getId());
    }
}
