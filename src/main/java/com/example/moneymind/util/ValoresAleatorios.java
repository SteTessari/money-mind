package com.example.moneymind.util;

import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.Random;

@Profile("teste")
public class ValoresAleatorios {

    private static final Random random = new Random();

    private static <E> E pegarEnumAleatorio(E[] arrayEnuns) {
        int numero = random.nextInt(arrayEnuns.length);
        return arrayEnuns[numero];
    }

    public static Integer getInteiro() {
        return random.nextInt();
    }

    public static Integer getInteiro(Integer max) {
        return random.nextInt(max);
    }

    public static Integer getInteiro(Integer min,Integer max) {
        return random.nextInt(max - min) + min;
    }
    public static Integer getInteiroPositivo(Integer min,Integer max) {
        return random.nextInt((max - min + 1)) + min;
    }

    public static Long getLong() {
        return random.nextLong();
    }

    public static BigDecimal getBigDecimal(Integer max) {
        return BigDecimal.valueOf(random.nextInt(max));
    }

    public static BigDecimal getBigDecimal() {
        return getBigDecimal(500);
    }

    public static Byte getByte(Integer max) {
        return getInteiro(max).byteValue();
    }

    public static Byte getByte() {
        return getByte(125);
    }

    public static Boolean getBoolean() {
        return random.nextBoolean();
    }

    public static String getCNPJ() {

        String[] values = {
                "00689700000135",
                "19538417000130",
                "36253052000117",
                "16003000000185",
                "94542719000106"

        };

        return pegarEnumAleatorio(values);
    }

    public static LocalDate getLocalDateDepoisDe(LocalDate data) {
        return data.plusDays(getInteiro(1,365));
    }

    public static LocalDate getLocalDate() {
        return LocalDate.of(Year.now().getValue(), Month.of(getInteiroPositivo(1, 12)), getInteiroPositivo(1, 30));
    }

    public static LocalDateTime getLocalDateTimeDepoisDe(LocalDateTime data) {
        return data.plusDays(getInteiro(1,365));
    }
}
