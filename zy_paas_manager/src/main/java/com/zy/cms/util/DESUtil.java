package com.zy.cms.util;

/*
 * 类DESUtil.java的实现描述：TODO 类实现描述
 * @author ddp1j32 2015-2-28 下午2:24:47
 */
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import com.zy.cms.common.Constant;


public class DESUtil {

    private static final Logger log              = Logger.getLogger(DESUtil.class);
    // 算法名称
    public static final String  KEY_ALGORITHM    = "DES";
    // 算法名称/加密模式/填充方式
    // DES共有四种工作模式-->>ECB：电子密码本模式、CBC：加密分组链接模式、CFB：加密反馈模式、OFB：输出反馈模式
    public static final String  CIPHER_ALGORITHM = "DES/ECB/NoPadding";

    /**
     * 生成密钥key对象
     * 
     * @param KeyStr 密钥字符串
     * @return 密钥对象
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws Exception
     */
    private static SecretKey keyGenerator(String keyStr) throws Exception {
        byte input[] = HexString2Bytes(keyStr);
        DESKeySpec desKey = new DESKeySpec(input);
        // 创建一个密匙工厂，然后用它把DESKeySpec转换成
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(desKey);
        return securekey;
    }

    private static int parse(char c) {
        if (c >= 'a') return (c - 'a' + 10) & 0x0f;
        if (c >= 'A') return (c - 'A' + 10) & 0x0f;
        return (c - '0') & 0x0f;
    }

    // 从十六进制字符串到字节数组转换
    public static byte[] HexString2Bytes(String hexstr) {
        byte[] b = new byte[hexstr.length() / 2];
        int j = 0;
        for (int i = 0; i < b.length; i++) {
            char c0 = hexstr.charAt(j++);
            char c1 = hexstr.charAt(j++);
            b[i] = (byte) ((parse(c0) << 4) | parse(c1));
        }
        return b;
    }

    /**
     * 加密数据
     * 
     * @param data 待加密数据
     * @param key 密钥
     * @return 加密后的数据
     */
    public static String encrypt(String data, String key) {
        try {
            data = padding(data);
            Key deskey = keyGenerator(key);
            // 实例化Cipher对象，它用于完成实际的加密操作
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            SecureRandom random = new SecureRandom();
            // 初始化Cipher对象，设置为加密模式
            cipher.init(Cipher.ENCRYPT_MODE, deskey, random);
            byte[] results = cipher.doFinal(data.getBytes());
            // 执行加密操作。加密后的结果通常都会用Base64编码进行传输
            return Base64.encodeBase64String(results);
        } catch (Exception e) {
            log.error("DES encrypt exception happened:" + e.getMessage(), e);
            return null;
        }

    }

    /**
     * 解密数据
     * 
     * @param data 待解密数据
     * @param key 密钥
     * @return 解密后的数据
     */
    public static String decrypt(String data, String key) {
        try {
            Key deskey = keyGenerator(key);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            // 初始化Cipher对象，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, deskey);
            // 执行解密操作
            return new String(cipher.doFinal(Base64.decodeBase64(data))).trim();
        } catch (Exception e) {
            log.error("DES decrypt exception happened:" + e.getMessage(), e);
            return null;
        }
    }

    /**
     * 将字符串补齐为8的倍数位
     * 
     * @param str
     * @return
     */
    private static String padding(String str) {
        StringBuilder sb = new StringBuilder(str);
        int m = str.length() % 8;
        for (int i = m; i < 8; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }
    
    public static void main(String [] args){
    	String d="oO6x7GXLNUk=";
    	String d1="zy_paas2OI6";
    	System.out.println("d===="+DESUtil.decrypt(d, Constant.USER_SESSION_ENCRYPT_KEY));
    	System.out.println("22===="+DESUtil.encrypt(d1, Constant.USER_SESSION_ENCRYPT_KEY));
    }
}
