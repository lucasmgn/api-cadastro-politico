package br.com.sprint4.controller;

import br.com.sprint4.entites.Partido;
import br.com.sprint4.enums.Ideologia;
import br.com.sprint4.services.PartidoService;
import br.com.sprint4.services.dto.request.PartidoInputDTO;
import br.com.sprint4.services.dto.responses.AssociadoResumoRespostaDTO;
import br.com.sprint4.services.dto.responses.PartidoRespostaDTO;
import br.com.sprint4.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = PartidoController.class)
public class PartidoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PartidoService service;

    @MockBean
    private PartidoController controller;

    private Partido partido;

    @BeforeEach
    void beforeEach() {
        partido = new Partido();
        partido.setId(1L);
        partido.setNome("Teste");
        partido.setSigla("PM");
        partido.setIdeologia(Ideologia.CENTRO);
    }

    public static final String BASE_URL = "/partidos";

    public static final String BASE_URL_PARTIDOS_ASSOCIADOS = "/partidos/1";
    public static final String ID_URL = BASE_URL + "/1";

    @Test
    public void deveriaRetornarOkMetodoListar() throws Exception {
        List<PartidoRespostaDTO> partidosRespostaDTO = new ArrayList<>();

        when(controller.listar(any(), any())).thenReturn(partidosRespostaDTO);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.get(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse resposta = result.getResponse();
        assertEquals(HttpStatus.OK.value(), resposta.getStatus());
    }

    @Test
    public void deveriaRetornarOkMetodoBuscar() throws Exception {
        PartidoRespostaDTO partidoRespostaDTO = new PartidoRespostaDTO();

        when(controller.buscar(any())).thenReturn(partidoRespostaDTO);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.get(ID_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse resposta = result.getResponse();
        assertEquals(HttpStatus.OK.value(), resposta.getStatus());
    }

    @Test
    public void deveriaRetornarOkMetodoListarOsAssociadosPorPartido() throws Exception {
        List<AssociadoResumoRespostaDTO> associadoResumoRespostaDTO = new ArrayList<>();

        when(controller.listar(any())).thenReturn(associadoResumoRespostaDTO);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.get(BASE_URL_PARTIDOS_ASSOCIADOS)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse resposta = result.getResponse();
        assertEquals(HttpStatus.OK.value(), resposta.getStatus());
    }

    @Test
    public void deveriaRetornarNoContentMetodoExcluir() throws Exception {
        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.delete(ID_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse resposta = result.getResponse();
        assertEquals(HttpStatus.NO_CONTENT.value(), resposta.getStatus());
    }


    @Test
    public void deveriaRetornarCreatedMetodoAdicionar() throws Exception {
        PartidoInputDTO partidoInputDTO = criarPartidoInput();
        PartidoRespostaDTO partidoRespostaDTO = new PartidoRespostaDTO();

        when(controller.adicionar(any())).thenReturn(partidoRespostaDTO);

        String input = TestUtils.mapToJson(partidoInputDTO);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse resposta = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), resposta.getStatus());
    }

    @Test
    public void deveriaRetornarOkMetodoAtualizar() throws Exception {
        PartidoInputDTO partidoInputDTO = criarPartidoInput();
        PartidoRespostaDTO partidoRespostaDTO = new PartidoRespostaDTO();

        when(controller.atualizar(any(),any())).thenReturn(partidoRespostaDTO);

        String input = TestUtils.mapToJson(partidoInputDTO);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.put(ID_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse resposta = result.getResponse();

        assertEquals(HttpStatus.OK.value(), resposta.getStatus());
    }

    private PartidoInputDTO criarPartidoInput(){
        PartidoInputDTO partido = new PartidoInputDTO();
        partido.setNome("Test");
        partido.setIdeologia(Ideologia.CENTRO);
        partido.setSigla("AS");
        return partido;
    }
}
