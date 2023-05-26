package br.com.sprint4.controller;

import br.com.sprint4.repositories.AssociateRepository;
import br.com.sprint4.services.PartyServiceImpl;
import br.com.sprint4.services.assembler.AssociateDTOAssembler;
import br.com.sprint4.services.assembler.PartyDTOAssembler;
import br.com.sprint4.services.assembler.PartyInputDisassembler;
import br.com.sprint4.dto.request.PartyInputDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static br.com.sprint4.utils.TestUtils.mapToJson;
import static br.com.sprint4.utils.mocks.PartyMocks.getPartyInputMock;
import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(controllers = PartyController.class)
class PartyControllerTest {

    public static final String BASE_URL = "/parties";
    public static final String ID_URL = BASE_URL + "/1";
    public static final String PARTY_ID_ASSOCIATES = ID_URL + "/associates";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PartyServiceImpl service;

    @MockBean
    private PartyDTOAssembler assembler;

    @MockBean
    private PartyInputDisassembler disassembler;

    @MockBean
    private AssociateDTOAssembler associateAssembler;

    @MockBean
    private AssociateRepository associateRepository;

    private final PartyInputDTO partyInputDTO = getPartyInputMock();

    @Test
    void should_returnOkMethodFindAll() throws Exception {
        var result = mvc
                .perform(MockMvcRequestBuilders.get(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    void should_returnOkMethodFindAllAssociatesOfADeterminateParty() throws Exception {
        var result = mvc
                .perform(MockMvcRequestBuilders.get(PARTY_ID_ASSOCIATES)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    void should_returnOkMethodFindBy() throws Exception {
        var result = mvc
                .perform(MockMvcRequestBuilders.get(ID_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }
    @Test
    void should_returnNoContentMethodRemove() throws Exception {
        var result = mvc
                .perform(MockMvcRequestBuilders.delete(ID_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertEquals(HttpStatus.NO_CONTENT.value(), result.getResponse().getStatus());
    }

    @Test
    void should_returnCreatedMethodeCreate() throws Exception {
        var result = mvc
                .perform(MockMvcRequestBuilders.post(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapToJson(partyInputDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
    }

    @Test
    void should_returnOkMethodeUpdate() throws Exception {
        var result = mvc
                .perform(MockMvcRequestBuilders.put(ID_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapToJson(partyInputDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }
}
