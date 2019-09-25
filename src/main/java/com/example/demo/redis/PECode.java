package com.example.demo.redis;

/**
 * @author lingang.zhao
 * @version 1.0
 * @date 2019/9/25 2:29 下午
 */
public class PECode {
    /**
     * 打乱改字符数组的组合顺序，可以得到不同的转换结果
     */
    private static final char[] digits = {'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm', '8', '5', '2', '7', '3', '6', '4', '0', '9', '1', 'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Z', 'X', 'C', 'V', 'B', 'N', 'M', '+', '-'};

    /**
     * @param number double类型的10进制数,该数必须大于0
     * @return string类型的64进制数
     */
    public static String encode(long number) {
        char[] buf = new char[64];
        int charPos = 64;
        int radix = 1 << 6;
        long mask = radix - 1;
        do {
            buf[--charPos] = digits[(int) (number & mask)];
            number >>>= 6;
        } while (number != 0);
        return new String(buf, charPos, (64 - charPos));
    }

    /**
     * 支持范围是A-Z,a-z,0-9,+,-
     *
     * @param decompStr 64进制的数字
     * @return 10进制的数字
     */
    public static long decode(String decompStr) {
        long result = 0;
        for (int i = decompStr.length() - 1; i >= 0; i--) {
            for (int j = 0; j < digits.length; j++) {
                if (decompStr.charAt(i) == digits[j]) {
                    result += ((long) j) << 6 * (decompStr.length() - 1 - i);
                }
            }
        }
        return result;
    }


}
