package br.com.sprint4.controller;

import br.com.sprint4.entites.Partido;
import br.com.sprint4.repositories.PartidoRepository;
import br.com.sprint4.services.PartidoService;
import br.com.sprint4.services.assembler.PartidoDTOAssembler;
import br.com.sprint4.services.assembler.PartidoInputDisassembler;
import br.com.sprint4.services.dto.request.PartidoInputDTO;
import br.com.sprint4.services.dto.responses.PartidoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class PartidoController {

    private final PartidoService service;

    private final PartidoDTOAssembler assembler;

    private final PartidoInputDisassembler disassembler;

    private final PartidoRepository repository;

    @GetMapping(value = "/partidos")
    public List<PartidoResponseDTO> listar(){
        List<Partido> todosPartidos = repository.findAll();

        return assembler.toCollectionModel(todosPartidos);
    }

    @GetMapping(value = "/partidos/{partidoId}")
    public PartidoResponseDTO buscar(@PathVariable Long partidoId){

        Partido partido = service.buscaOuFalha(partidoId);

        return assembler.toModel(partido);
    }

    //(Listar todos os associados daquele partido)
//    @GetMapping(value = "/partidos/{partidoId}/associados")
//    public PartidoResponseDTO buscar(@PathVariable Long partidoId){
//
//        Partido partido = service.buscaOuFalha(partidoId);
//
//        return assembler.toModel(partido);
//    }

    @PostMapping(value = "/partidos")
    @ResponseStatus(HttpStatus.CREATED)
    public PartidoResponseDTO adicionar(@RequestBody @Valid PartidoInputDTO partidoInputDTO) {
        Partido partido = disassembler.toDomainObject(partidoInputDTO);

        partido = service.adicionar(partido);

        return assembler.toModel(partido);
    }

    @PutMapping(value = "/partidos/{partidoId}")
    public PartidoResponseDTO atualizar(@PathVariable Long partidoId,
                               @RequestBody @Valid PartidoInputDTO partidoInputDTO){

        Partido partidoAtual = service.buscaOuFalha(partidoId);

        disassembler.copyToDomainObject(partidoInputDTO, partidoAtual);

        partidoAtual = service.adicionar(partidoAtual);

        return assembler.toModel(partidoAtual);
    }

    @DeleteMapping(value = "/partidos/{partidoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long partidoId){
        service.excluir(partidoId);
    }

}
