package br.com.sprint4.exceptions.handler;

import br.com.sprint4.constant.ErrorCode;
import br.com.sprint4.exceptions.AssociateNotFoundException;
import br.com.sprint4.exceptions.EntityInUseException;
import br.com.sprint4.exceptions.PartyNotFoundException;
import br.com.sprint4.exceptions.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var exceptionResponse = new Problem(ErrorCode.PARAMETER_INVALID, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var exceptionResponse = new Problem(ErrorCode.BAD_REQUEST, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var fieldErrors = ex.getBindingResult().getFieldErrors();

        var errors = new ArrayList<>();
        fieldErrors.forEach(error ->
                errors.add(String.format("%s : %s", error.getField(), error.getDefaultMessage()))
        );

        var exceptionResponse = new Problem(ErrorCode.BAD_REQUEST, String.valueOf(errors));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var exceptionResponse = new Problem(ErrorCode.BAD_REQUEST, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var exceptionResponse = new Problem(ErrorCode.BAD_REQUEST, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(PartyNotFoundException.class)
    public final ResponseEntity<Object> handlePartyNotFound(PartyNotFoundException ex) {
        var exceptionResponse = new Problem(ErrorCode.PARTY_NOT_FOUND, ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(AssociateNotFoundException.class)
    public final ResponseEntity<Object> handleAssociateNotFound(AssociateNotFoundException ex) {
        var exceptionResponse = new Problem(ErrorCode.ASSOCIATE_NOT_FOUND, ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(EntityInUseException.class)
    public ResponseEntity<?> handleEntityInUse(EntityInUseException e, WebRequest request){

        var status = HttpStatus.CONFLICT;
        var typeError = ErrorCode.ENTITY_IN_USE;
        var detail = e.getMessage();

        var problem = new Problem(typeError,detail);

        return handleExceptionInternal(e, problem, new HttpHeaders(),status, request);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
        var exceptionResponse = new Problem(ErrorCode.SYSTEM_ERROR, ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
    }
}
