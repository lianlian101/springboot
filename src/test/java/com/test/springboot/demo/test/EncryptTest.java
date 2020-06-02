package com.test.springboot.demo.test;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

public class EncryptTest {

    @Test
    public void base64() throws UnsupportedEncodingException {
        String cryp = Base64.encodeBase64String("哈哈哈".getBytes("utf-8"));
        System.out.println(cryp);
        byte[] bs = Base64.decodeBase64(cryp.getBytes("utf-8"));
        String proc = new String(bs);
        System.out.println(proc);
    }

    @Test
    public void aes() throws Exception {
        // 生成key
        KeyGenerator kg = KeyGenerator.getInstance("AES");
        kg.init(128); // 确定密钥长度
        byte[] keyBytes = kg.generateKey().getEncoded();
        // 格式化key
        Key key = new SecretKeySpec(keyBytes, "AES");

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); // 确定算法
        cipher.init(Cipher.ENCRYPT_MODE, key); // 确定密钥
        byte[] result = cipher.doFinal("原文".getBytes()); // 加密
        System.out.println(Base64.encodeBase64String(result)); // 不进行Base64编码的话，那么这个字节数组对应的字符串就是乱码

        cipher.init(Cipher.DECRYPT_MODE, key); // 进入解密模式
        System.out.println(new String(cipher.doFinal(result))); // 解密
    }
    
    @Test
    public void PBEWithMD5AndDES() throws Exception {
        String pwd = "hogen";     // 口令
        PBEKeySpec keySpec = new PBEKeySpec(pwd.toCharArray());  // 密钥格式化
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");  // 密钥工厂
        Key key = factory.generateSecret(keySpec);

        SecureRandom random = new SecureRandom();  // 强随机数生成器
        byte[] salt = random.generateSeed(8);  // 盐
        PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, 100);  // PBE参数格式化

        Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");  // 确定算法
        cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec); // 确定口令 和 盐
        byte[] result = cipher.doFinal("原文".getBytes()); // 加密
        System.out.println(Base64.encodeBase64String(result));

        cipher.init(Cipher.DECRYPT_MODE, key, parameterSpec); // 进入解密模式
        System.out.println(new String(cipher.doFinal(result))); // 解密
    }
    
}
