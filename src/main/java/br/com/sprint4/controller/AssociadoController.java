package br.com.sprint4.controller;

import br.com.sprint4.entites.Associado;
import br.com.sprint4.enums.Cargo;
import br.com.sprint4.enums.Ideologia;
import br.com.sprint4.repositories.AssociadoRepository;
import br.com.sprint4.services.AssociadoService;
import br.com.sprint4.services.assembler.AssociadoDTOAssembler;
import br.com.sprint4.services.assembler.AssociadoInputDisassembler;
import br.com.sprint4.services.dto.request.AssociadoInputDTO;
import br.com.sprint4.services.dto.request.AssociadoVinculaInputDTO;
import br.com.sprint4.services.dto.responses.AssociadoRespostaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AssociadoController {

    private final AssociadoService service;

    private final AssociadoDTOAssembler assembler;

    private final AssociadoInputDisassembler disassembler;

    private final AssociadoRepository repository;

    @GetMapping(value = "/associados")
    public List<AssociadoRespostaDTO> listar(@RequestParam(required = false, name = "Cargo") Cargo cargo, Pageable pageable){
        return service.verificacaoAssociadoRespostaDTO(cargo, pageable);
    }

    @GetMapping(value = "/associados/{associadoId}")
    public AssociadoRespostaDTO buscar(@PathVariable Long associadoId){
        Associado associado = service.buscaOuFalha(associadoId);

        return assembler.toModel(associado);
    }

    @PostMapping(value = "/associados")
    @ResponseStatus(HttpStatus.CREATED)
    public AssociadoRespostaDTO adicionar(@RequestBody @Valid AssociadoInputDTO associadoInputDTO) {
        Associado associado = disassembler.toDomainObject(associadoInputDTO);
        associado = service.adicionar(associado);

        return assembler.toModel(associado);
    }

//    Adicionar um associado a um partido
    @PostMapping(value = "/associados/partidos")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void adicionarAssociadoPartido(@RequestBody @Valid AssociadoVinculaInputDTO associadoInputDTO) {
        service.vincular(associadoInputDTO.getAssociadoId(), associadoInputDTO.getPartidoId());
    }

    @PutMapping(value = "/associados/{associadoId}")
    public AssociadoRespostaDTO atualizar(@PathVariable Long associadoId,
                                          @RequestBody @Valid AssociadoInputDTO associadoInputDTO){

        Associado associadoAtual = service.buscaOuFalha(associadoId);
        disassembler.copyToDomainObject(associadoInputDTO, associadoAtual);
        associadoAtual = service.adicionar(associadoAtual);

        return assembler.toModel(associadoAtual);
    }

    //(Remove determinado associado daquele partido)
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
