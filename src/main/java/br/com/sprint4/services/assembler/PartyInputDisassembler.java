package br.com.sprint4.services.assembler;

import br.com.sprint4.entity.Party;
import br.com.sprint4.dto.request.PartyInputDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PartyInputDisassembler {
    private final ModelMapper modelMapper;

    public Party toDomainObject(PartyInputDTO partyInput) {
        return modelMapper.map(partyInput, Party.class);
    }

    public void copyToDomainObject(PartyInputDTO partyInput, Party party) {
        modelMapper.map(partyInput, party);
    }
}
