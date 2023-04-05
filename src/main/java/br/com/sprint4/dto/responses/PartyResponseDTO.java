package br.com.sprint4.dto.responses;

import br.com.sprint4.enums.Ideology;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PartyResponseDTO {

    private Long id;

    private String name;

    private String acronym;

    private Ideology ideology;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate foundation;

}
