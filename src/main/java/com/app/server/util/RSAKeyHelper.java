package com.app.server.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qiaomengnan
 * @ClassName: RSAKeyHelper
 * @Description:
 * @date 2021/1/1
 */
@Slf4j
public class RSAKeyHelper {

    // Java密钥库(Java Key Store，JKS)
    private static final String KEY_STORE = "JKS";

    // 证书密码
    private static final String PASS_WORD = "mengnan";

    // 证书别名
    private static final String ALIAS = "mengnan";

    // 证书路径
    private static final String FILE_NAME = "/mengnan.keystore";

    private static final KeyStore keyStore;

    private static final PrivateKey privateKey;

    private static final PublicKey publicKey;

    private static final RSAPublicKey rsaPublicKey;

    private static final RSAPrivateKey rsaPrivateKey;

    static {
        keyStore = getKeyStore();
        privateKey = getPrivateKey();
        publicKey = getPublicKey();
        rsaPublicKey = getRSAPublicKey();
        rsaPrivateKey = getRSAPrivateKey();
    }

    // 加载证书
    private static KeyStore getKeyStore() {
        try(FileInputStream inputStream = new FileInputStream(new File(RSAKeyHelper.class.getResource(FILE_NAME).toURI()))) {
            KeyStore keyStore = KeyStore.getInstance(KEY_STORE);
            keyStore.load(inputStream, PASS_WORD.toCharArray());
            return keyStore;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new RuntimeException("获取KeyStore失败");
        }
    }

    public static PrivateKey getPrivateKey() {
        if (privateKey != null) {
            return privateKey;
        }
        try {
            return (PrivateKey)keyStore.getKey(ALIAS, PASS_WORD.toCharArray());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new RuntimeException("获取PrivateKey失败");
        }
    }

    public static PublicKey getPublicKey() {
        if (publicKey != null) {
            return publicKey;
        }
        try {
            return keyStore.getCertificate(ALIAS).getPublicKey();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new RuntimeException("获取PrivateKey失败");
        }
    }

    public static RSAPublicKey getRSAPublicKey() {
        if (rsaPublicKey != null) {
            return rsaPublicKey;
        }
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(getPublicKey().getEncoded());
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return (RSAPublicKey)keyFactory.generatePublic(keySpec);
        } catch (Exception ex) {
           log.error(ex.getMessage(), ex);
           throw new RuntimeException("获取RSAPublicKey失败");
        }
    }

    public static RSAPrivateKey getRSAPrivateKey() {
        if (rsaPrivateKey != null) {
            return rsaPrivateKey;
        }
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(getPrivateKey().getEncoded());
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return (RSAPrivateKey)keyFactory.generatePrivate(keySpec);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new RuntimeException("获取RSAPublicKey失败");
        }
    }

}
