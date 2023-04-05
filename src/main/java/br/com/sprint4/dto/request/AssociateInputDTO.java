package br.com.sprint4.dto.request;


import br.com.sprint4.enums.Office;
import br.com.sprint4.enums.Sex;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Setter
@Getter
public class AssociateInputDTO {

    @NotBlank
    private String name;

    @NotNull
    private Office office;

    @NotNull
    private Sex sex;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birth;
}
