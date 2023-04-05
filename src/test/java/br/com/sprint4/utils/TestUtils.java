package br.com.sprint4.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class TestUtils {
    public static ObjectMapper mapper = new ObjectMapper();

    public static String mapToJson(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }
}
