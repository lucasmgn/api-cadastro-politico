package br.com.sprint4.service;

import br.com.sprint4.repositories.PartidoRepository;
import br.com.sprint4.services.PartidoService;
import org.hibernate.query.criteria.internal.expression.SimpleCaseExpression;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Mockito.*;


public class PartidoServiceTest {

    @Mock
    private PartidoService service;

    @InjectMocks
    private PartidoRepository repository;

//    public PartidoService(PartidoRepository repository) {
//    }

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void deveSalvarUmPartido() throws Exception {
//        service = new PartidoService();
        when(repository.save(partido)).t

    }
}
