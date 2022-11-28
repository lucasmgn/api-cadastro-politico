package br.com.sprint4.services.assembler;

import br.com.sprint4.entites.Partido;
import br.com.sprint4.services.dto.responses.PartidoRespostaDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PartidoDTOAssembler {

    private final ModelMapper modelMapper;


    public PartidoRespostaDTO toModel(Partido partido) {
        return modelMapper.map(partido, PartidoRespostaDTO.class);
    }


    public List<PartidoRespostaDTO> toCollectionModel(List<Partido> partidos) {
        return partidos.stream()
                .map(partido -> toModel(partido))
                .collect(Collectors.toList());
    }
}
