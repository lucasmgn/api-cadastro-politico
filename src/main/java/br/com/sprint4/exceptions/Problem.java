package br.com.sprint4.exceptions;

import br.com.sprint4.constantes.ErroCode;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
public class Problem{

    private final String code;

    private final String message;

    private final List<String> details;

    public Problem(ErroCode erroCode, Throwable ex) {
        this(erroCode, ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage());
    }

    public Problem(ErroCode erroCode, String details) {
        this.code = erroCode.name();
        this.message = erroCode.getMessage();
        this.details = Collections.singletonList(details);
    }

    public Problem(ErroCode erroCode, List<String> details) {
        this.code = erroCode.name();
        this.message = erroCode.getMessage();
        this.details = details;
    }
}
