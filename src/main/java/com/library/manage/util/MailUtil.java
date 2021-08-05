package com.library.manage.util;

public class MailUtil {

    /**
     * 生成验证码
     * @return 验证码
     */
    public static String code(){
        String code = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int index = (int)(Math.random() * 20);
            str.append(code.charAt(index));
        }
        return str.toString();
    }
}
