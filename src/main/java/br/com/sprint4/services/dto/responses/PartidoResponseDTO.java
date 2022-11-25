package br.com.sprint4.services.dto.responses;

import br.com.sprint4.enums.Ideologia;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class PartidoResponseDTO {

    private Long id;

    private String nome;

    private String sigla;

    private Ideologia ideologia;

    private OffsetDateTime fundacao;
}
