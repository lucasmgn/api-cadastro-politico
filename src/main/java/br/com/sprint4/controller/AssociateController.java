package br.com.sprint4.controller;

import br.com.sprint4.dto.request.AssociateBindInputDTO;
import br.com.sprint4.dto.request.AssociateInputDTO;
import br.com.sprint4.dto.responses.AssociateResponseDTO;
import br.com.sprint4.enums.Office;
import br.com.sprint4.services.AssociateService;
import br.com.sprint4.services.assembler.AssociateDTOAssembler;
import br.com.sprint4.services.assembler.AssociateInputDisassembler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AssociateController {

    private final AssociateService service;
    private final AssociateDTOAssembler assembler;
    private final AssociateInputDisassembler disassembler;

    @GetMapping(value = "/associates")
    public ResponseEntity<List<AssociateResponseDTO>> findAll(@RequestParam(required = false, name = "Office") Office office, Pageable pageable){
        return ResponseEntity.ok().body(service.associateResponseDTOVerification(office, pageable));
    }

    @GetMapping(value = "/associates/{associateId}")
    public AssociateResponseDTO findBy(@PathVariable Long associateId){
        var associate = service.fetchOrFail(associateId);

        return assembler.toModel(associate);
    }

    @PostMapping(value = "/associates")
    @ResponseStatus(HttpStatus.CREATED)
    public AssociateResponseDTO create(@RequestBody @Valid AssociateInputDTO associateInputDTO) {
        var associate = disassembler.toDomainObject(associateInputDTO);
        associate = service.create(associate);

        return assembler.toModel(associate);
    }

    @PostMapping(value = "/associates/parties")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void bindAssociatedParty(@RequestBody @Valid AssociateBindInputDTO associateInputDTO) {
        service.bind(associateInputDTO.getAssociateId(), associateInputDTO.getPartyId());
    }

    @PutMapping(value = "/associates/{associateId}")
    public AssociateResponseDTO update(@PathVariable Long associateId,
            @RequestBody @Valid AssociateInputDTO associateInputDTO){

        var associateAtual = service.fetchOrFail(associateId);
        disassembler.copyToDomainObject(associateInputDTO, associateAtual);
        associateAtual = service.create(associateAtual);

        return assembler.toModel(associateAtual);
    }

    @PutMapping(value = "/associates/unbind/{associateId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unbindAssociateParty(@PathVariable Long associateId){
            service.unbind(associateId);
    }

    @DeleteMapping(value = "/associates/{associateId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long associateId){
        service.remove(associateId);
    }
}
