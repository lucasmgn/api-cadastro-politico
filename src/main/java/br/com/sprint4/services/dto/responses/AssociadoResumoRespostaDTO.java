package br.com.sprint4.services.dto.responses;

import br.com.sprint4.enums.Cargo;
import br.com.sprint4.enums.Sexo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AssociadoResumoRespostaDTO {

    private Long id;

    private String nome;

    private Cargo cargo;

    private Sexo sexo;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate nascimento;
}
