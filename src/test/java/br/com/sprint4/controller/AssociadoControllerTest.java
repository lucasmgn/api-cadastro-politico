package br.com.sprint4.controller;

import br.com.sprint4.entites.Associado;
import br.com.sprint4.entites.Partido;
import br.com.sprint4.enums.Cargo;
import br.com.sprint4.enums.Ideologia;
import br.com.sprint4.enums.Sexo;
import br.com.sprint4.services.AssociadoService;
import br.com.sprint4.services.assembler.AssociadoDTOAssembler;
import br.com.sprint4.services.assembler.AssociadoInputDisassembler;
import br.com.sprint4.services.dto.request.AssociadoInputDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static br.com.sprint4.utils.TestUtils.mapToJson;
import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(controllers = AssociadoController.class)
class AssociadoControllerTest {

    public static final String BASE_URL = "/associados";
    public static final String URL_VINCULAR_ASSOCIADO = "/associados/partidos";
    public static final String URL_DESVINCULAR_ASSOCIADO_ID = "/associados/desvincular/1";
    public static final String ID_URL = BASE_URL + "/1";
    @Autowired
    private MockMvc mvc;
    @MockBean
    private AssociadoService service;
    @MockBean
    private AssociadoDTOAssembler assembler;
    @MockBean
    private AssociadoInputDisassembler disassembler;
    private Associado associado;
    private AssociadoInputDTO associadoInputDTO;
    private Partido partido;

    @BeforeEach
    void setUp() {
        partido = new Partido();
        partido.setId(1L);
        partido.setNome("Teste");
        partido.setSigla("PM");
        partido.setIdeologia(Ideologia.CENTRO);

        associado = new Associado();
        associado.setId(1L);
        associado.setNome("Teste");
        associado.setCargo(Cargo.NENHUM);
        associado.setSexo(Sexo.MASCULINO);
        associado.setPartido(partido);

        associadoInputDTO = new AssociadoInputDTO();
        associadoInputDTO.setNome("L");
        associadoInputDTO.setSexo(Sexo.MASCULINO);
        associadoInputDTO.setCargo(Cargo.NENHUM);
    }

    @Test
    void deveriaRetornarOkMetodoListar() throws Exception {
        var result = mvc
                .perform(MockMvcRequestBuilders.get(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        var response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void deveriaRetornarOkMetodoBuscar() throws Exception {
        var result = mvc
                .perform(MockMvcRequestBuilders.get(ID_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        var response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void deveriaRetornarCreatedMetodoAdicionar() throws Exception {
        var result = mvc
                .perform(MockMvcRequestBuilders.post(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapToJson(associadoInputDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        var response = result.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    void deveriaRetornarOkMetodoAtualizar() throws Exception {
        var result = mvc
                .perform(MockMvcRequestBuilders.put(ID_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapToJson(associadoInputDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        var response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void deveriaRetornarNoContentMetodoRemover() throws Exception {
        var result = mvc
                .perform(MockMvcRequestBuilders.delete(ID_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        
        var response = result.getResponse();
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }

    @Test
    void deveriaAdicionarAssociadoPartido() throws Exception {
        var result = mvc
                .perform(MockMvcRequestBuilders.post(URL_VINCULAR_ASSOCIADO)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapToJson(associadoInputDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        
        var response = result.getResponse();
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }

    @Test
    void deveriaDesvincularAssociadoPartido() throws Exception {
        var input = mapToJson(associadoInputDTO);

        var result = mvc
                .perform(MockMvcRequestBuilders.put(URL_DESVINCULAR_ASSOCIADO_ID)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        
        var response = result.getResponse();
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }
}
