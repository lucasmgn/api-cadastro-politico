package br.com.sprint4.controller;

import br.com.sprint4.entites.Associado;
import br.com.sprint4.entites.Partido;
import br.com.sprint4.enums.Ideologia;
import br.com.sprint4.repositories.AssociadoRepository;
import br.com.sprint4.services.PartidoService;
import br.com.sprint4.services.assembler.AssociadoDTOAssembler;
import br.com.sprint4.services.assembler.PartidoDTOAssembler;
import br.com.sprint4.services.assembler.PartidoInputDisassembler;
import br.com.sprint4.services.dto.request.PartidoInputDTO;
import br.com.sprint4.services.dto.responses.AssociadoResumoRespostaDTO;
import br.com.sprint4.services.dto.responses.PartidoRespostaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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

    private final AssociadoDTOAssembler associadoAssembler;

    private final PartidoInputDisassembler disassembler;

    private final AssociadoRepository associadoRepository;

    @GetMapping(value = "/partidos")
    public List<PartidoRespostaDTO> listar(@RequestParam(required = false, name = "Ideologia") Ideologia ideologia, Pageable pageable){
        return service.verificacaoPartidoRespostaDTO(ideologia, pageable);
    }

    @GetMapping(value = "/partidos/{partidoId}")
    public PartidoRespostaDTO buscar(@PathVariable Long partidoId){

        Partido partido = service.buscaOuFalha(partidoId);

        return assembler.toModel(partido);
    }

    //(Listar todos os associados daquele partido)
    @GetMapping(value = "/partidos/{partidoId}/associados")
    public List<AssociadoResumoRespostaDTO> listar(@PathVariable Long partidoId){
        service.buscaOuFalha(partidoId);
        List<Associado> todosAssociados = associadoRepository.findAllByPartido_id(partidoId);
        return associadoAssembler.toCollectionModelResposta(todosAssociados);
    }

    @PostMapping(value = "/partidos")
    @ResponseStatus(HttpStatus.CREATED)
    public PartidoRespostaDTO adicionar(@RequestBody @Valid PartidoInputDTO partidoInputDTO) {
        Partido partido = disassembler.toDomainObject(partidoInputDTO);
        partido = service.adicionar(partido);
        return assembler.toModel(partido);
    }

    @PutMapping(value = "/partidos/{partidoId}")
    public PartidoRespostaDTO atualizar(@PathVariable Long partidoId, @RequestBody @Valid PartidoInputDTO partidoInputDTO){
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
