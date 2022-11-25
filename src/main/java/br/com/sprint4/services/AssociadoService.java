package br.com.sprint4.services;

import br.com.sprint4.entites.Associado;
import br.com.sprint4.exceptions.AssociadoNaoEncontradoException;
import br.com.sprint4.repositories.AssociadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AssociadoService {

    private AssociadoRepository repository;

    @Transactional
    public Associado adicionar(Associado associado) {
        return repository.save(associado);
    }

    @Transactional
    public void excluir(Long associadoId) {
        try {
            repository.deleteById(associadoId);
            repository.flush();

        }catch (EmptyResultDataAccessException e) {
            throw new AssociadoNaoEncontradoException();
        }
    }

    public Associado buscaOuFalha(Long associadoId) {
        return repository.findById(associadoId)
                .orElseThrow(AssociadoNaoEncontradoException::new);
    }
}
