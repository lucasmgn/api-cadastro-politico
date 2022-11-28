package br.com.sprint4.controller;

import br.com.sprint4.entites.Associado;
import br.com.sprint4.entites.Partido;
import br.com.sprint4.enums.Cargo;
import br.com.sprint4.enums.Ideologia;
import br.com.sprint4.enums.Sexo;
import br.com.sprint4.services.AssociadoService;
import br.com.sprint4.services.dto.request.AssociadoInputDTO;
import br.com.sprint4.services.dto.request.AssociadoVinculaInputDTO;
import br.com.sprint4.services.dto.responses.AssociadoRespostaDTO;
import br.com.sprint4.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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

@WebMvcTest(controllers = AssociadoController.class)
class AssociadoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AssociadoService service;

    @MockBean
    private AssociadoController controller;

    private Associado associado;

    AssociadoControllerTest() {
    }

    @BeforeEach
    void beforeEach() {
        associado = new Associado();
        associado.setId(1L);
        associado.setNome("Teste");
        associado.setCargo(Cargo.NENHUM);
        associado.setSexo(Sexo.MASCULINO);
        associado.setPartido(criarPartido());
    }

    public static final String BASE_URL = "/associados";

    public static final String ID_URL = BASE_URL + "/1";

    @Test
    void deveriaRetornarOkMetodoListar() throws Exception {
        List<AssociadoRespostaDTO> associadosRespostaDTO = new ArrayList<>();

        when(controller.listar(any(), any())).thenReturn(associadosRespostaDTO);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.get(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse resposta = result.getResponse();
        assertEquals(HttpStatus.OK.value(), resposta.getStatus());
    }

    @Test
    void deveriaRetornarOkMetodoBuscar() throws Exception {
        AssociadoRespostaDTO associadoRespostaDTO = new AssociadoRespostaDTO();

        when(controller.buscar(any())).thenReturn(associadoRespostaDTO);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.get(ID_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse resposta = result.getResponse();
        assertEquals(HttpStatus.OK.value(), resposta.getStatus());
    }

    @Test
    void deveriaRetornarCreatedMetodoAdicionar() throws Exception {
        AssociadoInputDTO associadoInputDTO = criarAssociadoInputDTO();
        AssociadoRespostaDTO associadoRespostaDTO = new AssociadoRespostaDTO();

        when(controller.adicionar(any())).thenReturn(associadoRespostaDTO);

        String input = TestUtils.mapToJson(associadoInputDTO);

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
    void deveriaRetornarOkMetodoAtualizar() throws Exception {
        AssociadoInputDTO associadoInputDTO = criarAssociadoInputDTO();
        AssociadoRespostaDTO associadoRespostaDTO = new AssociadoRespostaDTO();

        when(controller.atualizar(any(),any())).thenReturn(associadoRespostaDTO);

        String input = TestUtils.mapToJson(associadoInputDTO);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.put(ID_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse resposta = result.getResponse();

        assertEquals(HttpStatus.OK.value(), resposta.getStatus());
    }

    @Test
    void deveriaRetornarNoContentMetodoRemover() throws Exception {
        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.delete(ID_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse resposta = result.getResponse();
        assertEquals(HttpStatus.NO_CONTENT.value(), resposta.getStatus());
    }

    @Test
    void deveriaChamarMetodoRemover(){
        controller.remover(associado.getId());
        Mockito.verify(controller, Mockito.times(1)).remover(associado.getId());
    }

    @Test
    void deveriaChamarAdicionarAssociadoAoPartido(){
        AssociadoVinculaInputDTO associadoVinculaInputDTO = criaAssociadoVinculaDTO();
        controller.adicionarAssociadoPartido(associadoVinculaInputDTO);
        Mockito.verify(controller, Mockito.times(1)).adicionarAssociadoPartido(associadoVinculaInputDTO);
    }

    @Test
    void deveriaChamarDesvincularAssociadoPartido(){
        controller.desvincularAssociadoPartido(associado.getId());
        Mockito.verify(controller, Mockito.times(1)).desvincularAssociadoPartido(associado.getId());
    }

    private AssociadoInputDTO criarAssociadoInputDTO() {
        AssociadoInputDTO associado = new AssociadoInputDTO();
        associado.setNome("L");
        associado.setSexo(Sexo.MASCULINO);
        associado.setCargo(Cargo.NENHUM);
        return associado;
    }

    private AssociadoVinculaInputDTO criaAssociadoVinculaDTO(){
        AssociadoVinculaInputDTO associadoVinculaInputDTO = new AssociadoVinculaInputDTO();
        associadoVinculaInputDTO.setAssociadoId(1L);
        associadoVinculaInputDTO.setPartidoId(1L);
        return  associadoVinculaInputDTO;
    }

    private Partido criarPartido(){
        Partido partido = new Partido();
        partido.setId(1L);
        partido.setNome("Teste");
        partido.setSigla("PM");
        partido.setIdeologia(Ideologia.CENTRO);
        return partido;
    }
}
