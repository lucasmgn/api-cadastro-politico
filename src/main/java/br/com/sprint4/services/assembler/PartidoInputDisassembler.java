package br.com.sprint4.services.assembler;

import br.com.sprint4.entites.Partido;
import br.com.sprint4.services.dto.request.PartidoInputDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PartidoInputDisassembler {
    private final ModelMapper modelMapper;

    public Partido toDomainObject(PartidoInputDTO partidoInput) {
        return modelMapper.map(partidoInput, Partido.class);
    }

    public void copyToDomainObject(PartidoInputDTO partidoInput, Partido partido) {

        modelMapper.map(partidoInput, partido);
    }
}
