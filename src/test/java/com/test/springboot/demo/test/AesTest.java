package com.test.springboot.demo.test;

import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.junit.Test;

public class AesTest {

    private static final String ALGORITHM = "AES";

    /**
     * 生成密钥
     * 
     * @return
     * @throws Exception
     */
    private SecretKey geneKey() throws Exception {
        // 获取一个密钥生成器实例
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        SecureRandom random = new SecureRandom();
        random.setSeed(123456);// 设置加密用的种子，密钥
        keyGenerator.init(random);
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey;
    }

    /**
     * 加密测试
     */
    @Test
    public void testEncrypt() throws Exception {
        // 1、指定算法、获取Cipher对象
        Cipher cipher = Cipher.getInstance(ALGORITHM);// 算法是AES
        // 2、生成/读取用于加解密的密钥
        SecretKey secretKey = this.geneKey();
        // 3、用指定的密钥初始化Cipher对象，指定是加密模式，还是解密模式
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        String content = "加密测试";// 需要加密的内容
        // 4、更新需要加密的内容
        cipher.update(content.getBytes());
        // 5、进行最终的加解密操作
        byte[] result = cipher.doFinal();// 加密后的字节数组
        // 也可以把4、5步组合到一起，但是如果保留了4步，同时又是如下这样使用的话，加密的内容将是之前update传递的内容和doFinal传递的内容的和。
        // byte[] result = cipher.doFinal(content.getBytes());
        String base64Result = Base64.getEncoder().encodeToString(result);// 对加密后的字节数组进行Base64编码,不进行Base64编码的话，那么这个字节数组对应的字符串就是乱码
        System.out.println("Result: " + base64Result);
    }

    /**
     * 解密测试
     */
    @Test
    public void testDecrpyt() throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);// 确定算法
        SecretKey secretKey = this.geneKey();
        cipher.init(Cipher.DECRYPT_MODE, secretKey);// 确定密钥
        String content = "ZKhyxJVb8ep/e3J4zVCWpQ==";// 经过Base64加密的待解密的内容
        byte[] encodedBytes = Base64.getDecoder().decode(content.getBytes());
        byte[] result = cipher.doFinal(encodedBytes);// 对加密后的字节数组进行解密
        System.out.println("Result: " + new String(result));
    }
    
    //========================================================================================================================================================
    /**
     * AES加密字符串
     *
     * @param content 需要被加密的字符串
     * @param password 加密需要的密码
     * @return 密文
     * @throws Exception
     */
    public static byte[] encrypt(String content, String password) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者

        kgen.init(128, new SecureRandom(password.getBytes()));// 利用用户密码作为随机数初始化出
                                                              // 128位的key生产者
        // 加密没关系，SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有password就行

        SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥

        byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥，如果此密钥不支持编码，则返回

        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥

        Cipher cipher = Cipher.getInstance("AES");// 创建密码器

        byte[] byteContent = content.getBytes("utf-8");

        cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化为加密模式的密码器

        byte[] result = cipher.doFinal(byteContent);// 加密

        return result;

    }
    
    /**
     * 解密AES加密过的字符串
     *
     * @param content
     *            AES加密过过的内容
     * @param password
     *            加密时的密码
     * @return 明文
     * @throws Exception
     */
    public static byte[] decrypt(byte[] content, String password) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者
        kgen.init(128, new SecureRandom(password.getBytes()));
        SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥
        byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥
        Cipher cipher = Cipher.getInstance("AES");// 创建密码器
        cipher.init(Cipher.DECRYPT_MODE, key);// 初始化为解密模式的密码器
        byte[] result = cipher.doFinal(content);
        return result; // 明文
    }
    
    @Test
    public void encDec(){
        String content = "这是需要加密的数据,我就是想看看这个数据加密后的密文有多长,这个是一个比较长的数据,到底有多长呢,你猜猜看,哈哈哈哈哈哈";
        String pwd = "这是密钥";
        System.out.println("原文：" + content);
        
        try {
            
            byte[] encrypt = encrypt(content, pwd);
            String ciphertext = byte2HexStr(encrypt);
            System.out.println("加密后的数据：" + ciphertext);
            System.out.println("加密后的数据：" + new String(Base64.getEncoder().encode(encrypt)));
            
            byte[] decrypt = decrypt(hexStr2Byte(ciphertext), pwd);
            String plaintext = new String(decrypt);
            System.out.println("解密后的数据：" + plaintext);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 将二进制转换成16进制
     * 
     * @param buf
     * @return
     */
    public static String byte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     * 
     * @param hexStr
     * @return
     */
    public static byte[] hexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

}
