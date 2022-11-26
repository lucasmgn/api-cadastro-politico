package br.com.sprint4.services.dto.responses;

import br.com.sprint4.entites.Partido;
import br.com.sprint4.enums.Cargo;
import br.com.sprint4.enums.Sexo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AssociadoRespostaDTO {

    private Long id;

    private String nome;

    private Cargo cargo;

    private Sexo sexo;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate nascimento;

    private Partido partido;
}
