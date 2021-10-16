package org.example.daochu.utils;

import java.math.BigInteger;

public class StateCode {
    public static BigInteger getStateCode(String state) {
        BigInteger code = BigInteger.valueOf(-1);
        if ("已申请".equals(state)) code = BigInteger.valueOf(1);
        if ("已接受".equals(state)) code = BigInteger.valueOf(2);
        if ("已完成".equals(state)) code = BigInteger.valueOf(3);
        if ("未接受".equals(state)) code = BigInteger.valueOf(4);
        return code;
    }

    public static String code2State(BigInteger code) {
        String res = "";
        if (code.equals(BigInteger.valueOf(1))) res = "已申请";
        if (code.equals(BigInteger.valueOf(2))) res = "已接受";
        if (code.equals(BigInteger.valueOf(3))) res = "已完成";
        if (code.equals(BigInteger.valueOf(4))) res = "未接受";
        return res;
    }
}
