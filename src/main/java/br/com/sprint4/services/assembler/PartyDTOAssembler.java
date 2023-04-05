package br.com.sprint4.services.assembler;

import br.com.sprint4.entity.Party;
import br.com.sprint4.dto.responses.PartyResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PartyDTOAssembler {

    private final ModelMapper modelMapper;


    public PartyResponseDTO toModel(Party party) {
        return modelMapper.map(party, PartyResponseDTO.class);
    }


    public List<PartyResponseDTO> toCollectionModel(List<Party> parties) {
        return parties.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
