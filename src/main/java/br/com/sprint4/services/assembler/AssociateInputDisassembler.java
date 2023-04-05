package br.com.sprint4.services.assembler;

import br.com.sprint4.entity.Associate;
import br.com.sprint4.dto.request.AssociateInputDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssociateInputDisassembler {
    private final ModelMapper modelMapper;

    public Associate toDomainObject(AssociateInputDTO associateInput) {
        return modelMapper.map(associateInput, Associate.class);
    }

    public void copyToDomainObject(AssociateInputDTO associateInput, Associate associate) {
        modelMapper.map(associateInput, associate);
    }
}
