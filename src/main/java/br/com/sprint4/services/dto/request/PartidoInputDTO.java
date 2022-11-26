package br.com.sprint4.services.dto.request;

import br.com.sprint4.enums.Ideologia;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Setter
@Getter
public class PartidoInputDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String sigla;

    @NotNull
    private Ideologia ideologia;

    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fundacao;
}
