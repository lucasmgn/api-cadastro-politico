package br.com.sprint4.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Ideologia {
    @JsonProperty("Direita")
    DIREITA,
    @JsonProperty("Centro")
    CENTRO,
    @JsonProperty("Esquerda")
    ESQUERDA;
}
