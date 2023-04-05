package br.com.sprint4.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    PARTY_NOT_FOUND("Party not found"),
    ASSOCIATE_NOT_FOUND("Associate not found"),
    BAD_REQUEST("Invalid Request"),
    PARAMETER_INVALID("Invalid Parameter"),
    SYSTEM_ERROR("System Error"),
    ENTITY_IN_USE("Entity in Use");

    private final String message;

}