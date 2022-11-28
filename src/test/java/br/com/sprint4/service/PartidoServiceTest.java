package br.com.sprint4.service;

import br.com.sprint4.entites.Partido;
import br.com.sprint4.enums.Ideologia;
import br.com.sprint4.repositories.PartidoRepository;
import br.com.sprint4.services.PartidoService;
import br.com.sprint4.services.assembler.PartidoDTOAssembler;
import net.bytebuddy.implementation.bind.ArgumentTypeResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PartidoServiceTest {

    public static final Long ID = 1L;

    @Mock
    private  PartidoDTOAssembler assembler;

    @InjectMocks
    private PartidoService service;

    @Mock
    private PartidoRepository repository;

    private Partido partido;

    private Partido criarPartido() {
        partido = new Partido();
        partido.setId(1L);
        partido.setNome("Teste");
        partido.setSigla("PM");
        partido.setIdeologia(Ideologia.CENTRO);
        return partido;
    }


    @Test
    void deveSalvarUmPartido() {
        Partido partido1 = new Partido();

        when(repository.save(any())).thenReturn(partido1);

        Partido adicionar = service.adicionar(partido1);

        assertEquals(partido1,adicionar);

    }

    @Test
    void testarBuscaOuFalhaPartido() {
        Partido partido1 = new Partido();

        when(repository.findById(any())).thenReturn(Optional.of(partido1));
        Partido response = service.buscaOuFalha(ID);

        assertEquals(partido1, response);
    }


//    @Test
//    void testandoSeRetornaOMesmoIdPartido() {
//        when(service.adicionar(any(Partido.class))).thenReturn(partido);
//
//        Partido response = service.adicionar(partido);
//
//        assertEquals(response.getId(), partido.getId());
//    }

}
