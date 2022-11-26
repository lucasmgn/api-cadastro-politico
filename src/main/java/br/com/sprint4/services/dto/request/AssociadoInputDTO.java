package br.com.sprint4.services.dto.request;

import br.com.sprint4.enums.Cargo;
import br.com.sprint4.enums.Ideologia;
import br.com.sprint4.enums.Sexo;
import br.com.sprint4.services.dto.responses.PartidoRespostaDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Setter
@Getter
public class AssociadoInputDTO {

    @NotBlank
    private String nome;

    @NotNull
    private Cargo cargo;

    @NotNull
    private Sexo sexo;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate nascimento;
}
