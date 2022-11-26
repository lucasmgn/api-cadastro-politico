package br.com.sprint4.services;

import br.com.sprint4.enums.Ideologia;
import org.springframework.core.convert.converter.Converter;

public class StringEnumConverteIdeologia implements Converter<String, Ideologia> {

    @Override
    public Ideologia convert(String source) {
        return Ideologia.valueOf(source.toUpperCase());
    }
}
