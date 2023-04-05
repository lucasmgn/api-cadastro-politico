package br.com.sprint4.services.convert;

import br.com.sprint4.enums.Ideology;
import org.springframework.core.convert.converter.Converter;

public class StringEnumConvertIdeology implements Converter<String, Ideology> {

    @Override
    public Ideology convert(String source) {
        return Ideology.valueOf(source.toUpperCase());
    }
}
