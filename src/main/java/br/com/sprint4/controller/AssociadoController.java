package br.com.sprint4.controller;

import br.com.sprint4.enums.Cargo;
import br.com.sprint4.services.AssociadoService;
import br.com.sprint4.services.assembler.AssociadoDTOAssembler;
import br.com.sprint4.services.assembler.AssociadoInputDisassembler;
import br.com.sprint4.services.dto.request.AssociadoInputDTO;
import br.com.sprint4.services.dto.request.AssociadoVinculaInputDTO;
import br.com.sprint4.services.dto.responses.AssociadoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AssociadoController {

    private final AssociadoService service;

    private final AssociadoDTOAssembler assembler;

    private final AssociadoInputDisassembler disassembler;

    @GetMapping(value = "/associados")
    public List<AssociadoResponseDTO> listar(@RequestParam(required = false, name = "Cargo") Cargo cargo, Pageable pageable){
        return service.verificacaoAssociadoresponseDTO(cargo, pageable);
    }

    @GetMapping(value = "/associados/{associadoId}")
    public AssociadoResponseDTO buscar(@PathVariable Long associadoId){
        var associado = service.buscaOuFalha(associadoId);

        return assembler.toModel(associado);
    }

    @PostMapping(value = "/associados")
    @ResponseStatus(HttpStatus.CREATED)
    public AssociadoResponseDTO adicionar(@RequestBody @Valid AssociadoInputDTO associadoInputDTO) {
        var associado = disassembler.toDomainObject(associadoInputDTO);
        associado = service.adicionar(associado);

        return assembler.toModel(associado);
    }

    @PostMapping(value = "/associados/partidos")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void adicionarAssociadoPartido(@RequestBody @Valid AssociadoVinculaInputDTO associadoInputDTO) {
        service.vincular(associadoInputDTO.getAssociadoId(), associadoInputDTO.getPartidoId());
    }

    @PutMapping(value = "/associados/{associadoId}")
    public AssociadoResponseDTO atualizar(@PathVariable Long associadoId,
                                          @RequestBody @Valid AssociadoInputDTO associadoInputDTO){

        var associadoAtual = service.buscaOuFalha(associadoId);
        disassembler.copyToDomainObject(associadoInputDTO, associadoAtual);
        associadoAtual = service.adicionar(associadoAtual);

        return assembler.toModel(associadoAtual);
    }

    @PutMapping(value = "/associados/desvincular/{associadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desvincularAssociadoPartido(@PathVariable Long associadoId){
            service.desvincular(associadoId);
    }

    @DeleteMapping(value = "/associados/{associadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long associadoId){
        service.excluir(associadoId);
    }
}
