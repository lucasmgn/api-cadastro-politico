package br.com.sprint4.exceptions;

import br.com.sprint4.constant.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class PartyNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;
    private final String details;
    private final ErrorCode errorCode;
    private final HttpStatus httpStatus;

    public PartyNotFoundException() {
        super(ErrorCode.PARTY_NOT_FOUND.name());
        this.httpStatus = HttpStatus.NOT_FOUND;
        this.errorCode = ErrorCode.PARTY_NOT_FOUND;
        this.details = ErrorCode.PARTY_NOT_FOUND.getMessage();
    }
}
