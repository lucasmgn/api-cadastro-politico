package br.com.sprint4.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Cargo {
    @JsonProperty("Vereador")
    VEREADOR,
    @JsonProperty("Prefeito")
    PREFEITO,
    @JsonProperty("Deputado Estadual")
    DEPUTADO_ESTADUAL,
    @JsonProperty("Deputado Federal")
    DEPUTADO_FEDERAL,
    @JsonProperty("Senador")
    SENADOR,
    @JsonProperty("Governador")
    GOVERNADOR,
    @JsonProperty("Presidente")
    PRESIDENTE,
    @JsonProperty("Nenhum")
    NENHUM;
}
