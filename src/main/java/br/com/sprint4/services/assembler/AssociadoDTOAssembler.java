package br.com.sprint4.services.assembler;

import br.com.sprint4.entites.Associado;
import br.com.sprint4.services.dto.responses.AssociadoResponseDTO;
import br.com.sprint4.services.dto.responses.AssociadoResumoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AssociadoDTOAssembler {

    private final ModelMapper modelMapper;

    public AssociadoResponseDTO toModel(Associado associado) {
        return modelMapper.map(associado, AssociadoResponseDTO.class);
    }

    public AssociadoResumoResponseDTO toModelResponse(Associado associado) {
        return modelMapper.map(associado, AssociadoResumoResponseDTO.class);
    }


    public List<AssociadoResponseDTO> toCollectionModel(List<Associado> associados) {
        return associados.stream()
                .map(associado -> toModel(associado))
                .collect(Collectors.toList());
    }

    public List<AssociadoResumoResponseDTO> toCollectionModelResponse(List<Associado> associados) {
        return associados.stream()
                .map(associado -> toModelResponse(associado))
                .collect(Collectors.toList());
    }
}
