package br.com.sprint4.services.dto.responses;

import br.com.sprint4.entites.Partido;
import br.com.sprint4.enums.Cargo;
import br.com.sprint4.enums.Sexo;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class AssociadoResponseDTO {

    private Long id;

    private String nome;

    private Cargo cargo;

    private Sexo sexo;

    private OffsetDateTime nascimento;

    private Partido partido;
}
