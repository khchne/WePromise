package org.example.daochu.utils;

import java.math.BigInteger;

public class HandicapLevelCode {
    public static BigInteger getHandicapLevelCode(String level) {
        BigInteger state = BigInteger.valueOf(0);
        if ("一级".equals(level)) state = BigInteger.valueOf(1);
        if ("二级".equals(level)) state = BigInteger.valueOf(2);
        if ("三级".equals(level)) state = BigInteger.valueOf(3);
        if ("四级".equals(level)) state = BigInteger.valueOf(4);
        return state;
    }

    public static String code2Level(BigInteger bigInteger) {
        String res = "";
        if (BigInteger.valueOf(1).equals(bigInteger)) res = "一级";
        if (BigInteger.valueOf(2).equals(bigInteger)) res = "二级";
        if (BigInteger.valueOf(3).equals(bigInteger)) res = "三级";
        if (BigInteger.valueOf(4).equals(bigInteger)) res = "四级";

        return res;
    }
}
