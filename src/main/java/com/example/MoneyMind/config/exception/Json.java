package com.example.MoneyMind.config.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;

public class Json {

    private static final ObjectMapper MAPPER_WITH_SIFAT_CONFIG;

    static {
        MAPPER_WITH_SIFAT_CONFIG = createMapperWithSifatConfig();
    }

    private Json() {
    }

    private static ObjectMapper createMapperWithSifatConfig() {

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return mapper;
    }

    public static String toJson(Object object) {
        try {
            return MAPPER_WITH_SIFAT_CONFIG.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao converter json para string", e);
        }
    }

    public static <T> T convertValue(Object value, Class<T> targetType) {
        try {
            return MAPPER_WITH_SIFAT_CONFIG.convertValue(value, targetType);
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao converter objeto", e);
        }
    }

    public static <T> T fromJson(String json, Class<T> type) {
        try {
            if (json == null)
                return null;



            return MAPPER_WITH_SIFAT_CONFIG.readValue(json, type);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao converter json para object", e);
        }
    }

    public static ObjectMapper getMapperWithSifatConfig() {
        return MAPPER_WITH_SIFAT_CONFIG;
    }
}