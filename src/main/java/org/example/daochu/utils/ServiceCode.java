package org.example.daochu.utils;

import java.math.BigInteger;

public class ServiceCode {
    public static BigInteger getServiceCode(String service) {
        BigInteger state = BigInteger.valueOf(0);
        if ("康复治疗".equals(service)) state = BigInteger.valueOf(1);
        if ("生活照料".equals(service)) state = BigInteger.valueOf(2);
        if ("交通出行".equals(service)) state = BigInteger.valueOf(3);
        if ("就业培训".equals(service)) state = BigInteger.valueOf(4);
        return state;
    }
    public static String code2Service(BigInteger code) {
        String service = "";
        if (code.equals(BigInteger.valueOf(1))) service = "康复治疗";
        if (code.equals(BigInteger.valueOf(2))) service = "生活照料";
        if (code.equals(BigInteger.valueOf(3))) service = "交通出行";
        if (code.equals(BigInteger.valueOf(4))) service = "就业培训";
        return service;
    }
}
