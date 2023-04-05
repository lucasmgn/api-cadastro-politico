package br.com.sprint4.dto.responses;

import br.com.sprint4.enums.Office;
import br.com.sprint4.enums.Sex;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AssociateResponseDTO {

    private Long id;

    private String name;

    private Office office;

    private Sex sex;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birth;

    private PartyResponseDTO party;
}
