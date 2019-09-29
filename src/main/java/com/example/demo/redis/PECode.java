package com.example.demo.redis;

/**
 * @author lingang.zhao
 * @version 1.0
 * @date 2019/9/25 2:29 下午
 */
public class PECode {

    private static final int DIGIT = 1 << 6;

    private static final int ZERO = 0;

    private static final char[] digits = {'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f',
            'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm', '8', '5', '2', '7', '3', '6', '4', '0',
            '9', '1', 'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K',
            'L', 'Z', 'X', 'C', 'V', 'B', 'N', 'M', '+', '-'};

    /**
     * @param number long 类型的10进制数,该数必须大于0
     * @return string类型的64进制数
     */
    public static String encode(long number) {
        if (number <= 0) {
            throw new RuntimeException("PECode.encode number Must be greater than 0");
        }
        char[] buf = new char[DIGIT];
        int charPos = DIGIT;
        int radix = 1 << 6;
        long mask = radix - 1;
        do {
            buf[--charPos] = digits[(int) (number & mask)];
            number >>>= 6;
        } while (number != ZERO);
        return new String(buf, charPos, (DIGIT - charPos));
    }

    /**
     * 支持范围是A-Z,a-z,0-9,+,-
     *
     * @param decoStr 64进制的数字
     * @return 10进制的数字
     */
    public static long decode(String decoStr) {
        if (decoStr == null || decoStr.length() == 0) {
            throw new RuntimeException("PECode.decode decoStr not null ");
        }
        long result = ZERO;
        for (int i = decoStr.length() - 1; i >= 0; i--) {
            for (int j = ZERO; j < digits.length; j++) {
                if (decoStr.charAt(i) == digits[j]) {
                    result += ((long) j) << 6 * (decoStr.length() - 1 - i);
                }
            }
        }
        return result;
    }


}
