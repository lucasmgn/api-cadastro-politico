package br.com.sprint4.controller;

import br.com.sprint4.entites.Associado;
import br.com.sprint4.repositories.AssociadoRepository;
import br.com.sprint4.services.AssociadoService;
import br.com.sprint4.services.assembler.AssociadoDTOAssembler;
import br.com.sprint4.services.assembler.AssociadoInputDisassembler;
import br.com.sprint4.services.dto.request.AssociadoInputDTO;
import br.com.sprint4.services.dto.responses.AssociadoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<AssociadoResponseDTO> listar(){
        List<Associado> todosAssociados = repository.findAll();

        return assembler.toCollectionModel(todosAssociados);
    }

    @GetMapping(value = "/associados/{associadoId}")
    public AssociadoResponseDTO buscar(@PathVariable Long associadoId){

        Associado associado = service.buscaOuFalha(associadoId);

        return assembler.toModel(associado);
    }

    @PostMapping(value = "/associados")
    @ResponseStatus(HttpStatus.CREATED)
    public AssociadoResponseDTO adicionar(@RequestBody @Valid AssociadoInputDTO associadoInputDTO) {
        Associado associado = disassembler.toDomainObject(associadoInputDTO);

        associado = service.adicionar(associado);

        return assembler.toModel(associado);
    }

    @PutMapping(value = "/associados/{associadoId}")
    public AssociadoResponseDTO atualizar(@PathVariable Long associadoId,
                                        @RequestBody @Valid AssociadoInputDTO associadoInputDTO){

        Associado associadoAtual = service.buscaOuFalha(associadoId);

        disassembler.copyToDomainObject(associadoInputDTO, associadoAtual);

        associadoAtual = service.adicionar(associadoAtual);

        return assembler.toModel(associadoAtual);
    }

    @DeleteMapping(value = "/associados/{associadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long associadoId){
        service.excluir(associadoId);
    }
}
