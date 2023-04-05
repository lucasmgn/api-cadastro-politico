package br.com.sprint4.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Office {
    @JsonProperty("Alderman")
    ALDERMAN,
    @JsonProperty("Mayor")
    MAYOR,
    @JsonProperty("Deputy State")
    STATE_DEPUTY,
    @JsonProperty("Deputy Federal")
    FEDERAL_DEPUTY,
    @JsonProperty("Senator")
    SENATOR,
    @JsonProperty("Governor")
    GOVERNOR,
    @JsonProperty("President")
    PRESIDENT,
    @JsonProperty("None")
    NONE;
}
