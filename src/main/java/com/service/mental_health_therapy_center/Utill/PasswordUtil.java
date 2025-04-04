package com.service.mental_health_therapy_center.Utill;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil{


     public static String encryptPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(10));
    }

    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }


}
