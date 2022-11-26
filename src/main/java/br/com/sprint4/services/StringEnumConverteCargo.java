package br.com.sprint4.services;

import br.com.sprint4.enums.Cargo;
import org.springframework.core.convert.converter.Converter;

public class StringEnumConverteCargo implements Converter<String, Cargo> {

    @Override
    public Cargo convert(String source) {
        return Cargo.valueOf(source.toUpperCase());
    }
}
