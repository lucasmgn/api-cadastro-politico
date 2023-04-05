package br.com.sprint4.services;

import br.com.sprint4.entites.Associado;
import br.com.sprint4.entites.Partido;
import br.com.sprint4.enums.Ideologia;
import br.com.sprint4.exceptions.EntidadeEmUsoException;
import br.com.sprint4.exceptions.PartidoNaoEncontradoException;
import br.com.sprint4.repositories.PartidoRepository;
import br.com.sprint4.services.assembler.PartidoDTOAssembler;
import br.com.sprint4.services.dto.responses.PartidoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PartidoService {

    private final PartidoRepository repository;

    private final PartidoDTOAssembler assembler;

    private final AssociadoService associadoService;

    private static final String MSG_PARTIDO_ESTA_EM_USO = "Partido de código %d não pode ser removido, pois está em uso";

    @Transactional
    public Partido adicionar(Partido partido) {
        return repository.save(partido);
    }

    @Transactional
    public void excluir(Long partidoId) {
        try {
            repository.deleteById(partidoId);
            repository.flush();

        }catch (EmptyResultDataAccessException e) {
            throw new PartidoNaoEncontradoException();
        }catch (DataIntegrityViolationException e) { //erro se tentar excluir associado q está em uso
            throw new EntidadeEmUsoException(String.format(MSG_PARTIDO_ESTA_EM_USO, partidoId));
        }

    }

    public Partido buscaOuFalha(Long partidoId) {
        return repository.findById(partidoId)
                .orElseThrow(PartidoNaoEncontradoException::new);
    }

    public List<Associado> listarAssociadosDeUmPartido(Long partidoId){
        buscaOuFalha(partidoId);
        return associadoService.listarAssociados(partidoId);
    }

    public List<PartidoResponseDTO> verificacaoPartidoresponseDTO(Ideologia ideologia, Pageable pageable) {
        if(ideologia == null){
            var partidosPage = repository.findAll(pageable);
            return assembler.toCollectionModel(partidosPage.getContent());
        }else{
            var partidos = repository.findAllByIdeologia(ideologia, pageable).getContent();
            return assembler.toCollectionModel(partidos);
        }
    }
}
