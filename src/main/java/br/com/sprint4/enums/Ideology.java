package br.com.sprint4.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Ideology {
    @JsonProperty("Right")
    RIGHT,
    @JsonProperty("Center")
    CENTER,
    @JsonProperty("Left")
    LEFT;
}
