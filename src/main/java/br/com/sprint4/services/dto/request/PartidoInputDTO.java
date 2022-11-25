package br.com.sprint4.services.dto.request;

import br.com.sprint4.enums.Ideologia;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Setter
@Getter
public class PartidoInputDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String sigla;

    @NotBlank
    private Ideologia ideologia;

    @NotNull
    private OffsetDateTime fundacao;
}
