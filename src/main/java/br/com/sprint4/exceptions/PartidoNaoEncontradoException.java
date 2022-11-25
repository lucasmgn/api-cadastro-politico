package br.com.sprint4.exceptions;

import br.com.sprint4.constantes.ErroCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class PartidoNaoEncontradoException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String details;

    private final ErroCode erroCode;
    private final HttpStatus httpStatus;

    public PartidoNaoEncontradoException() {
        super(ErroCode.PARTIDO_NAO_ENCONTRADO.name());
        this.httpStatus = HttpStatus.NOT_FOUND;
        this.erroCode = ErroCode.PARTIDO_NAO_ENCONTRADO;
        this.details = ErroCode.PARTIDO_NAO_ENCONTRADO.getMessage();
    }
}
