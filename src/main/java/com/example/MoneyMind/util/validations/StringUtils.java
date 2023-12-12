package com.example.MoneyMind.util.validations;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

public class StringUtils {

    private static final String UTF8_BOM = "\uFEFF";

    public static String encodeBase64(String conteudo) {
        return Base64.getEncoder().encodeToString(conteudo.getBytes());
    }

    public static String decodeBase64(String conteudo) {
        try {
            return new String(Base64.getDecoder().decode(conteudo));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static String SHA_1(String input) {
        try {
            MessageDigest mDigest = MessageDigest.getInstance("SHA1");
            byte[] result = mDigest.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : result) {
                sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            return UUID.randomUUID().toString();
        }
    }

    public static boolean isNotBlank(String string) {
        return !isBlank(string);
    }

    public static String md5(String strToEncde) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        BigInteger hash = new BigInteger(1, md.digest(strToEncde.getBytes()));
        StringBuilder sen = new StringBuilder(hash.toString(16));
        if (sen.length() != 32) {
            for (int i = sen.length(); i < 32; i++) {
                sen.insert(0, "0");
            }
        }
        return sen.toString().toUpperCase().trim();
    }


    public static boolean isBlank(String str) {
        return (str == null || str.isEmpty());
    }

}
