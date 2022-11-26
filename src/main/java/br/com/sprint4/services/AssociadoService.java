package br.com.sprint4.services;

import br.com.sprint4.entites.Associado;
import br.com.sprint4.entites.Partido;
import br.com.sprint4.enums.Cargo;
import br.com.sprint4.exceptions.AssociadoNaoEncontradoException;
import br.com.sprint4.exceptions.EntidadeEmUsoException;
import br.com.sprint4.repositories.AssociadoRepository;
import br.com.sprint4.services.assembler.AssociadoDTOAssembler;
import br.com.sprint4.services.dto.responses.AssociadoRespostaDTO;
import br.com.sprint4.services.dto.responses.PartidoRespostaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssociadoService {

    private final PartidoService service;

    private static final String MSG_ASSOCIADO_ESTA_EM_USO
            = "Associado de código %d não pode ser removido, pois está em uso";

    private final AssociadoRepository repository;

    private final AssociadoDTOAssembler assembler;

    @Transactional
    public Associado adicionar(Associado associado) {
        return repository.save(associado);
    }

    @Transactional
    public void vincular(Long associadoId, Long partidoId) {
        Associado associadoExiste = buscaOuFalha(associadoId);
        Partido partidoExiste = service.buscaOuFalha(partidoId);

        associadoExiste.setPartido(partidoExiste);
    }

    //desvinculando o associado do partido
    @Transactional
    public void desvincular(Long associadoId) {
        Associado associadoExiste = buscaOuFalha(associadoId);

        associadoExiste.setPartido(null);
    }

    @Transactional
    public void excluir(Long associadoId) {
        try {
            repository.deleteById(associadoId);
            repository.flush();

        }catch (EmptyResultDataAccessException e) {
            throw new AssociadoNaoEncontradoException();
        }catch (DataIntegrityViolationException e) { //erro se tentar excluir associado q está em uso
            throw new EntidadeEmUsoException(
                    String.format(MSG_ASSOCIADO_ESTA_EM_USO, associadoId));
        }
    }

    public Associado buscaOuFalha(Long associadoId) {
        return repository.findById(associadoId)
                .orElseThrow(AssociadoNaoEncontradoException::new);
    }

    public List<AssociadoRespostaDTO> verificacaoAssociadoRespostaDTO(Cargo cargo, Pageable pageable) {
        if(cargo == null){
            Page<Associado> associadosPage = repository.findAll(pageable);
            List<AssociadoRespostaDTO> associados = assembler.toCollectionModel(associadosPage.getContent());
            return associados;
        }else{
            List<Associado> associados = repository.findAllByCargo(cargo, pageable).getContent();
            return assembler.toCollectionModel(associados);
        }
    }
}
