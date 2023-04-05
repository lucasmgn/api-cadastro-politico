package br.com.sprint4.controller;

import br.com.sprint4.services.AssociateService;
import br.com.sprint4.services.assembler.AssociateDTOAssembler;
import br.com.sprint4.services.assembler.AssociateInputDisassembler;
import br.com.sprint4.dto.request.AssociateInputDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static br.com.sprint4.utils.TestUtils.mapToJson;
import static br.com.sprint4.utils.mocks.AssociateMocks.getAssociateInputWithOutPartyMock;
import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(controllers = AssociateController.class)
class AssociateControllerTest {
    public static final String BASE_URL = "/associates";
    public static final String URL_VINCULAR_associate = "/associates/parties";
    public static final String URL_DESVINCULAR_associate_ID = "/associates/unbind/1";
    public static final String ID_URL = BASE_URL + "/1";
    @Autowired
    private MockMvc mvc;
    @MockBean
    private AssociateService service;
    @MockBean
    private AssociateDTOAssembler assembler;
    @MockBean
    private AssociateInputDisassembler disassembler;
    private final AssociateInputDTO associateInputDTO = getAssociateInputWithOutPartyMock();

    @Test
    void should_returnOkMethodFindAll() throws Exception {
        var result = mvc
                .perform(MockMvcRequestBuilders.get(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        var response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void should_returnOkMethodFindBy() throws Exception {
        var result = mvc
                .perform(MockMvcRequestBuilders.get(ID_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        var response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void should_returnCreatedMethodCreate() throws Exception {
        var result = mvc
                .perform(MockMvcRequestBuilders.post(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapToJson(associateInputDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        var response = result.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    void should_returnOkUpdateMethod() throws Exception {
        var result = mvc
                .perform(MockMvcRequestBuilders.put(ID_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapToJson(associateInputDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        var response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void should_returnNoContentMethodRemove() throws Exception {
        var result = mvc
                .perform(MockMvcRequestBuilders.delete(ID_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        
        var response = result.getResponse();
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }

    @Test
    void should_bindAssociatedParty() throws Exception {
        var result = mvc
                .perform(MockMvcRequestBuilders.post(URL_VINCULAR_associate)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapToJson(associateInputDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        
        var response = result.getResponse();
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }

    @Test
    void should_unbindAssociateParty() throws Exception {
        var result = mvc
                .perform(MockMvcRequestBuilders.put(URL_DESVINCULAR_associate_ID)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapToJson(associateInputDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        
        var response = result.getResponse();
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }
}
