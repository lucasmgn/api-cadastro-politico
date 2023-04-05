package br.com.sprint4.services.assembler;

import br.com.sprint4.entity.Associate;
import br.com.sprint4.dto.responses.AssociateResponseDTO;
import br.com.sprint4.dto.responses.AssociateResumeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AssociateDTOAssembler {

    private final ModelMapper modelMapper;

    public AssociateResponseDTO toModel(Associate associate) {
        return modelMapper.map(associate, AssociateResponseDTO.class);
    }

    public AssociateResumeResponseDTO toModelResponse(Associate associate) {
        return modelMapper.map(associate, AssociateResumeResponseDTO.class);
    }


    public List<AssociateResponseDTO> toCollectionModel(List<Associate> associates) {
        return associates.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public List<AssociateResumeResponseDTO> toCollectionModelResponse(List<Associate> associates) {
        return associates.stream()
                .map(this::toModelResponse)
                .collect(Collectors.toList());
    }
}
