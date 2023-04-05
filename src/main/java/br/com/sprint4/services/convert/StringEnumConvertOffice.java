package br.com.sprint4.services.convert;

import br.com.sprint4.enums.Office;
import org.springframework.core.convert.converter.Converter;

public class StringEnumConvertOffice implements Converter<String, Office> {

    @Override
    public Office convert(String source) {
        return Office.valueOf(source.toUpperCase());
    }
}
