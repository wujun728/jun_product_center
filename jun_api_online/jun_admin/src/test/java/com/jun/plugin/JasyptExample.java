package com.jun.plugin;

import org.jasypt.util.text.BasicTextEncryptor;

public class JasyptExample {
    public static void main(String[] args) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();

        // 设置加密密钥
        textEncryptor.setPassword("123456@@");
//        textEncryptor.setPassword("myEncryptionPassword");

        // 要加密的数据
        String myText = "This is a text to encrypt";

        // 加密
        String encryptedText = textEncryptor.encrypt(myText);
        System.out.println("Encrypted text: " + encryptedText);

        // 解密
        String decryptedText = textEncryptor.decrypt(encryptedText);
        System.out.println("Decrypted text: " + decryptedText);
    }
}