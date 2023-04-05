package br.com.sprint4.controller;

import br.com.sprint4.entites.Associado;
import br.com.sprint4.entites.Partido;
import br.com.sprint4.enums.Ideologia;
import br.com.sprint4.repositories.AssociadoRepository;
import br.com.sprint4.services.AssociadoService;
import br.com.sprint4.services.PartidoService;
import br.com.sprint4.services.assembler.AssociadoDTOAssembler;
import br.com.sprint4.services.assembler.PartidoDTOAssembler;
import br.com.sprint4.services.assembler.PartidoInputDisassembler;
import br.com.sprint4.services.dto.request.PartidoInputDTO;
import br.com.sprint4.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static br.com.sprint4.utils.TestUtils.mapToJson;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@WebMvcTest(controllers = PartidoController.class)
@ContextConfiguration(classes = {PartidoController.class, AssociadoService.class})
class PartidoControllerTest {

    public static final String BASE_URL = "/partidos";
    public static final String ID_URL = BASE_URL + "/1";
    public static final String PARTIDOS_ID_ASSOCIADOS = ID_URL + "/associados";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PartidoService service;

    @MockBean
    private PartidoDTOAssembler assembler;

    @MockBean
    private PartidoInputDisassembler disassembler;

    @MockBean
    private AssociadoDTOAssembler associadoAssembler;

    @MockBean
    private AssociadoRepository associadoRepository;

    private Partido partido;
    private Associado associado;
    private PartidoInputDTO partidoInputDTO;

    @BeforeEach
    void beforeEach() {
        partido = new Partido();
        partido.setId(1L);
        partido.setNome("Teste");
        partido.setSigla("PM");
        partido.setIdeologia(Ideologia.CENTRO);

        partidoInputDTO = new PartidoInputDTO();
        partidoInputDTO.setNome("Test");
        partidoInputDTO.setIdeologia(Ideologia.CENTRO);
        partidoInputDTO.setSigla("AS");
    }

    @Test
    void deveriaRetornarOkMetodoListar() throws Exception {
        var result = mvc
                .perform(MockMvcRequestBuilders.get(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    void deveriaRetornarOkMetodoListarTodosAssociadosDeUmDeterminadoPartido() throws Exception {
        var result = mvc
                .perform(MockMvcRequestBuilders.get(PARTIDOS_ID_ASSOCIADOS)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    void deveriaRetornarOkMetodoBuscar() throws Exception {
        var result = mvc
                .perform(MockMvcRequestBuilders.get(ID_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    void deveriaRetornarOkMetodoListarOsAssociadosPorPartido() throws Exception {
        var result = mvc
                .perform(MockMvcRequestBuilders.get(ID_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }
    @Test
    void removerResultadoNoContent() throws Exception {
        var result = mvc
                .perform(MockMvcRequestBuilders.delete(ID_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertEquals(HttpStatus.NO_CONTENT.value(), result.getResponse().getStatus());
    }

    @Test
    void adicionar() throws Exception {
        var result = mvc
                .perform(MockMvcRequestBuilders.post(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapToJson(partidoInputDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
    }

    @Test
    void atualizar() throws Exception {
        var result = mvc
                .perform(MockMvcRequestBuilders.put(ID_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapToJson(partidoInputDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    void remover(){
       service.excluir(partido.getId());
       verify(service, times(1)).excluir(partido.getId());
    }
}
