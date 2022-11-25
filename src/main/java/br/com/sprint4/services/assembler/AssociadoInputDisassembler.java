package br.com.sprint4.services.assembler;

import br.com.sprint4.entites.Associado;
import br.com.sprint4.services.dto.request.AssociadoInputDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AssociadoInputDisassembler {
    @Autowired
    private ModelMapper modelMapper;

    public Associado toDomainObject(AssociadoInputDTO associadoInput) {
        return modelMapper.map(associadoInput, Associado.class);
    }

    public void copyToDomainObject(AssociadoInputDTO associadoInput, Associado associado) {
        // Para evitar org.hibernate.HibernateException: identifier of an instance of
        // com.wantfood.domain.model.Estado was altered from 1 to 2
//        cidade.setEstado(new Estado());

        modelMapper.map(associadoInput, associado);
    }
}
