package org.example.daochu.utils;

import java.math.BigInteger;

/**
 * 编号规则
 * １：视力残疾
 * ２：听力残疾
 * ３：言语残疾
 * ４：肢体残疾
 * ５：智力残疾
 * ６：精神残疾
 * ７：多重残疾：存在2项或2项以上残疾。
 */


public class HandicapCategoryCode {
    public static BigInteger getHandicapCategoryCode(String category) {
        BigInteger state = BigInteger.valueOf(0);
        if ("视力残疾".equals(category)) state = BigInteger.valueOf(1);
        if ("听力残疾".equals(category)) state = BigInteger.valueOf(2);
        if ("言语残疾".equals(category)) state = BigInteger.valueOf(3);
        if ("肢体残疾".equals(category)) state = BigInteger.valueOf(4);
        if ("智力残疾".equals(category)) state = BigInteger.valueOf(5);
        if ("精神残疾".equals(category)) state = BigInteger.valueOf(6);
        if ("多重残疾".equals(category)) state = BigInteger.valueOf(7);
        return state;
    }

    public static String code2Category(BigInteger bigInteger) {
        String res = "";
        if (BigInteger.valueOf(1).equals(bigInteger)) res = "视力残疾";
        if (BigInteger.valueOf(2).equals(bigInteger)) res = "听力残疾";
        if (BigInteger.valueOf(3).equals(bigInteger)) res = "言语残疾";
        if (BigInteger.valueOf(4).equals(bigInteger)) res = "肢体残疾";
        if (BigInteger.valueOf(5).equals(bigInteger)) res = "智力残疾";
        if (BigInteger.valueOf(6).equals(bigInteger)) res = "精神残疾";
        if (BigInteger.valueOf(7).equals(bigInteger)) res = "多重残疾";
        return res;
    }
}
