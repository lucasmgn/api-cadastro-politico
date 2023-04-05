package br.com.sprint4.dto.request;

import br.com.sprint4.enums.Ideology;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Setter
@Getter
public class PartyInputDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String acronym;

    @NotNull
    private Ideology ideology;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate foundation;
}
