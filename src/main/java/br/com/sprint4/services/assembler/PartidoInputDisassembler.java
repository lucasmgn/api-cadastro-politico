package br.com.sprint4.services.assembler;

import br.com.sprint4.entites.Partido;
import br.com.sprint4.services.dto.request.PartidoInputDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PartidoInputDisassembler {
    @Autowired
    private ModelMapper modelMapper;

    public Partido toDomainObject(PartidoInputDTO partidoInput) {
        return modelMapper.map(partidoInput, Partido.class);
    }

    public void copyToDomainObject(PartidoInputDTO partidoInput, Partido partido) {
        // Para evitar org.hibernate.HibernateException: identifier of an instance of
        // com.wantfood.domain.model.Estado was altered from 1 to 2
//        cidade.setEstado(new Estado());

        modelMapper.map(partidoInput, partido);
    }
}
