package br.com.sprint4.services.convert;

import br.com.sprint4.enums.Ideology;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class StringEnumConvertIdeologyTest {

    @InjectMocks
    private StringEnumConvertIdeology convertIdeology;

    @Test
    void convert() {
        var ideology = convertIdeology.convert("ceNter");
        assertEquals(Ideology.CENTER, ideology);
    }
}