package com.example.blowalgo.comtroller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import javax.crypto.Cipher;
// import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
// import java.security.MessageDigest;

// import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Controller
public class IndexController {

    private static final String ALGORITHM = "Blowfish";
    private static final String SECRET_KEY = "KeyThisASecretIs";

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/encrypt")
    public String encrypt(String message, Model model) {
        try {
            String encryptedMessage = encryptMessage(message);
            model.addAttribute("encryptedMessage", encryptedMessage);
        } catch (Exception e) {
            model.addAttribute("error", "Error encrypting message: " + e.getMessage());
        }
        return "index";
    }

    @PostMapping("/decrypt")
    public String decrypt(String encryptedMessage, Model model) {
        try {
            String decryptedMessage = decryptMessage(encryptedMessage);
            model.addAttribute("decryptedMessage", decryptedMessage);
        } catch (Exception e) {
            model.addAttribute("error", "Error decrypting message: " + e.getMessage());
        }
        return "index";
    }

    private String encryptMessage(String message) throws Exception {
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(message.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    private String decryptMessage(String encryptedMessage) throws Exception {
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedMessage));
        return new String(decryptedBytes);
    }

    //     private static SecretKey generateSecretKey(String passphrase) throws NoSuchAlgorithmException {
    //     // Use a secure hash function (e.g., SHA-256) as the Key Derivation Function (KDF)
    //     MessageDigest digest = MessageDigest.getInstance("SHA-256");
    //     byte[] hashedPassphrase = digest.digest(passphrase.getBytes());
    //     // Use the first 128 bits (16 bytes) as the secret key for Blowfish
    //     byte[] keyBytes = new byte[16];
    //     System.arraycopy(hashedPassphrase, 0, keyBytes, 0, keyBytes.length);
    //     return new SecretKeySpec(keyBytes, ALGORITHM);
    // }
}
