package br.com.sprint4.controller;

import br.com.sprint4.entites.Partido;
import br.com.sprint4.services.PartidoService;
import br.com.sprint4.services.dto.responses.PartidoRespostaDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class PartidoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PartidoService service;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void deveriaDevolverUmPartido() throws Exception {
        PartidoRespostaDTO partidoRespostaDTO = new PartidoRespostaDTO();

        Partido partido = new Partido();

        when(service.buscaOuFalha(any())).thenReturn(partido);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.get("/partidos/{partidoId}")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse resposta = result.getResponse();
        assertEquals(HttpStatus.OK.value(), resposta.getStatus());
    }
}
