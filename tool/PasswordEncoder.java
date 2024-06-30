package com.lc.lcserve.tool;

import cn.hutool.crypto.digest.BCrypt;

public class PasswordEncoder {
    public static String hashPassword(String plainTextPassword) {
        // 生成一个随机的盐
        String salt = BCrypt.gensalt();

        // 使用盐和明文密码来生成哈希值
        String hashedPassword = BCrypt.hashpw(plainTextPassword, salt);

        // 返回哈希值
        return hashedPassword;
    }

    public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
        // 使用哈希值来检查明文密码是否匹配
        boolean passwordMatch = BCrypt.checkpw(plainTextPassword, hashedPassword);

        // 返回检查结果
        return passwordMatch;
    }
}
