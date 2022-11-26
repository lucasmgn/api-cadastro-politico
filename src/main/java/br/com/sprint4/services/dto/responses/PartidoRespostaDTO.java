package br.com.sprint4.services.dto.responses;

import br.com.sprint4.enums.Ideologia;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PartidoRespostaDTO {

    private Long id;

    private String nome;

    private String sigla;

    private Ideologia ideologia;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fundacao;

}
