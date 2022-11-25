package br.com.sprint4.services;

import br.com.sprint4.entites.Partido;
import br.com.sprint4.exceptions.PartidoNaoEncontradoException;
import br.com.sprint4.repositories.PartidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PartidoService {

    private final PartidoRepository repository;

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
        }
    }

    public Partido buscaOuFalha(Long partidoId) {
        return repository.findById(partidoId)
                .orElseThrow(PartidoNaoEncontradoException::new);
    }
}
