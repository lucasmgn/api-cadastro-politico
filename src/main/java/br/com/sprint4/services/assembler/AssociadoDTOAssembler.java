package br.com.sprint4.services.assembler;

import br.com.sprint4.entites.Associado;
import br.com.sprint4.services.dto.responses.AssociadoRespostaDTO;
import br.com.sprint4.services.dto.responses.AssociadoResumoRespostaDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AssociadoDTOAssembler {

    private final ModelMapper modelMapper;

    public AssociadoRespostaDTO toModel(Associado associado) {
        return modelMapper.map(associado, AssociadoRespostaDTO.class);
    }

    public AssociadoResumoRespostaDTO toModelResposta(Associado associado) {
        return modelMapper.map(associado, AssociadoResumoRespostaDTO.class);
    }


    public List<AssociadoRespostaDTO> toCollectionModel(List<Associado> associados) {
        return associados.stream()
                .map(associado -> toModel(associado))
                .collect(Collectors.toList());
    }

    public List<AssociadoResumoRespostaDTO> toCollectionModelResposta(List<Associado> associados) {
        return associados.stream()
                .map(associado -> toModelResposta(associado))
                .collect(Collectors.toList());
    }
}
