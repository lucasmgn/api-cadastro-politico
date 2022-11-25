package br.com.sprint4.services.assembler;

import br.com.sprint4.entites.Associado;
import br.com.sprint4.services.dto.responses.AssociadoResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AssociadoDTOAssembler {
    @Autowired
    private ModelMapper modelMapper;

    public AssociadoResponseDTO toModel(Associado associado) {
        return modelMapper.map(associado, AssociadoResponseDTO.class);
    }

    public List<AssociadoResponseDTO> toCollectionModel(List<Associado> associados) {
        return associados.stream()
                .map(associado -> toModel(associado))
                .collect(Collectors.toList());
    }
}
