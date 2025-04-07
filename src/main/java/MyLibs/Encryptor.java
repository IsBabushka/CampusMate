/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyLibs;

import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 *
 * @author allen
 */
public class Encryptor {
    public static String encryptPassword(String unhashedPassword) {
        //    encrypt string via bcrypt 
        return BCrypt.withDefaults().hashToString(12, unhashedPassword.toCharArray());
    }
    
    public static boolean verifyPassword(String plainTextPassword, String hashedPassword) {
        //    verify if correct, true if same, false if not
        BCrypt.Result result = BCrypt.verifyer().verify(plainTextPassword.toCharArray(), hashedPassword);
        return result.verified;
    }
}
