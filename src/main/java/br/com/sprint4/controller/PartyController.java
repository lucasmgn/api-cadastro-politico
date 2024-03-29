package br.com.sprint4.controller;

import br.com.sprint4.dto.request.PartyInputDTO;
import br.com.sprint4.dto.responses.AssociateResumeResponseDTO;
import br.com.sprint4.dto.responses.PartyResponseDTO;
import br.com.sprint4.enums.Ideology;
import br.com.sprint4.services.PartyService;
import br.com.sprint4.services.assembler.AssociateDTOAssembler;
import br.com.sprint4.services.assembler.PartyDTOAssembler;
import br.com.sprint4.services.assembler.PartyInputDisassembler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PartyController {

    private final PartyService service;
    private final PartyDTOAssembler assembler;
    private final PartyInputDisassembler disassembler;
    private final AssociateDTOAssembler associateAssembler;


    @GetMapping(value = "/parties")
    public List<PartyResponseDTO> findAll(@RequestParam(required = false, name = "Ideology") Ideology ideology, Pageable pageable){
        return service.partyResponseDTOVerification(ideology, pageable);
    }

    @GetMapping(value = "/parties/{partyId}")
    public PartyResponseDTO findBy(@PathVariable Long partyId){
        var party = service.fetchOrFail(partyId);
        return assembler.toModel(party);
    }

    @GetMapping(value = "/parties/{partyId}/associates")
        public List<AssociateResumeResponseDTO> findAllAssociates(@PathVariable Long partyId){
        var associates = service.findAllAssociatesOf(partyId);
        return associateAssembler.toCollectionModelResponse(associates);
    }

    @PostMapping(value = "/parties")
    @ResponseStatus(HttpStatus.CREATED)
    public PartyResponseDTO create(@RequestBody @Valid PartyInputDTO partyInputDTO) {
        var party = disassembler.toDomainObject(partyInputDTO);
        party = service.create(party);
        return assembler.toModel(party);
    }

    @PutMapping(value = "/parties/{partyId}")
    public PartyResponseDTO update(@PathVariable Long partyId, @RequestBody @Valid PartyInputDTO partyInputDTO){
        var partyAtual = service.fetchOrFail(partyId);
        disassembler.copyToDomainObject(partyInputDTO, partyAtual);
        partyAtual = service.create(partyAtual);
        return assembler.toModel(partyAtual);
    }

    @DeleteMapping(value = "/parties/{partyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long partyId){
        service.remove(partyId);
    }

}
