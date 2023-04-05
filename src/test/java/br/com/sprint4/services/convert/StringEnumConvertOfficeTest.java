package br.com.sprint4.services.convert;

import br.com.sprint4.enums.Office;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StringEnumConvertOfficeTest {

    @InjectMocks
    private StringEnumConvertOffice convertOffice;
    @Test
    void convert() {
        var alderman = convertOffice.convert("aldeRmaN");
        assertEquals(Office.ALDERMAN, alderman);
    }
}