package com.jd.AsyEny;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.AsymmetricCrypto;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.symmetric.AES;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author lk
 * @version 1.0
 * @date 2020/9/15 17:51
 */
public class AsymmetricUtils {

  private static final String PRIVATE_KEY =
      "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJjf24urWBZeIhiTe3X0tZeb9dzk+khC"
          + "/B3Bru3FICfBcBigmJjWVBxD22ksmqEUZLhx0yidV3JnTezbajwjVHHwb2gK7tT/DwzkFB1tmr895wC2/Y"
          + "/fbd"
          + "/GVh4DVb09Y6kwLH7WE665XHAIyuh9WgrHqOHjUyEaxDi342IpNTjVAgMBAAECgYBEMUdLzEjk4WSwNcmS2m"
          + "/Oi0FnIbULWuX/mBAh/BbV5RHNyoWHV8P5P8O17LGlPOWY6R1aNqV1YON"
          + "/znhbDx9xajCoNCAro1QR1QUAtMiW0Z7sZACYuKZS1RSpEC/r0lVWYIbUuqQn8AHZ4kqqI/V0Fj"
          + "+3lNcNUinAiC5aUGO+mQJBAN5AIoBtIhv2dJBuUqs1rAn/NQ/KWkcXg5jESC7olvayN9fSgX"
          + "+mgowSGVZCqacfuRYjpTZUbi7J6xyJAFkFTssCQQCwFsQtRvEeQquwAiUdfZSTZKtQf3DZ3XLvCDpIJjfkkgRFINS1yBl0A1"
          + "+cVly6PoWkYwQ0yrLpQPKs7eEh6ALfAkAZhbfwk5fFKko8g87Ohn1ZMIuBYrV3UIX2NyQq7t7XOaQcDrp8VDzNpQ5vz3v4CzaQCkvgr1V"
          + "v3hQ31KvLjUZ9AkAw4+FMAOppUHGCyNWtPnTGB6lZDEk09Ds5CrvD1HioSbJNzzO"
          + "/1PLcNyOQsJnGTB2m6qb8UVsjUBkQ0mszkstZAkEAgR"
          + "yfBRy8Z1ZkufvGEME16Wgu/fU42UcNqWq5jGcEhsdjN17ddtxIBltSyLo1Apjpqw8ZOha6SBdHr/JiStdrwA"
          + "==";

  public static enum TYPE {
    /** algorithmType */
    RSA,
    DSA;
  }

  public static String[] generateKey(String algorithm) {
    AsymmetricCrypto asy = new AsymmetricCrypto(algorithm);
    return new String[] {asy.getPrivateKeyBase64(), asy.getPublicKeyBase64()};
  }

  /**
   * 公钥加密
   *
   * @param publicKey
   * @param content
   * @return
   */
  public static String publicEncrypt(String publicKey, String content) {
    AsymmetricCrypto asy = new AsymmetricCrypto(TYPE.RSA.toString(), null, publicKey);
    byte[] encrypt = asy.encrypt(content, KeyType.PublicKey);
    return Base64.encode(encrypt);
  }

  /**
   * 私钥解密
   *
   * @param privateKey
   * @param encrypt
   * @return
   */
  public static String privateDecrypt(String privateKey, String encrypt) {
    AsymmetricCrypto asy = new AsymmetricCrypto(TYPE.RSA.toString(), privateKey, null);
    byte[] aByte = Base64.decode(encrypt);
    byte[] decrypt = asy.decrypt(aByte, KeyType.PrivateKey);
    return StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8);
  }

  /** 私钥加密 */
  public static String privateEncrypt(String algorithm, String privateKey, String content) {
    AsymmetricCrypto asy = new AsymmetricCrypto(TYPE.RSA.toString(), privateKey, null);
    byte[] encrypt = asy.encrypt(content, KeyType.PrivateKey);
    return Base64.encode(encrypt);
  }

  /**
   * 公钥解密
   *
   * @param publicKey
   * @param encrypt
   * @return
   */
  public static String publicDecrypt(String publicKey, String encrypt) {
    AsymmetricCrypto asy = new AsymmetricCrypto(TYPE.RSA.toString(), null, publicKey);
    byte[] aByte = Base64.decode(encrypt);
    byte[] decrypt = asy.decrypt(aByte, KeyType.PublicKey);
    return StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8);
  }

  public static String base64Encode(byte[] key) {
    return java.util.Base64.getEncoder().encodeToString(key);
  }

  private static byte[] base64Decode(String key) {
    return java.util.Base64.getDecoder().decode(key);
  }

  public static String aesEncript(String content, String key) {
    AES aes = SecureUtil.aes(base64Decode(key));
    return aes.encryptHex(content);
  }

  public static String aesDecript(String encryptHex, String key) {
    AES aes = SecureUtil.aes(base64Decode(key));
    return aes.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
  }

  /**
   * 对map进行加签，添加 signature 属性 加签规则: 加签前添加：uuid,timestamp字段
   * 将map的value按照key的字典顺序排序，合成一个待签名字符串toSignStr toSignStr+signKey 将该字符串使用sha-1(md5())计算出签名 put
   *
   * @param map
   */
  public static void signatureMap(Map<String, String> map, String signKey) {
    if (map != null) {
      map.put("timestamp", System.currentTimeMillis() + "");
      map.put("uuid", UUID.randomUUID().toString());
      String signature = signMap(map, signKey);
      map.put("signature", signature);
    }
  }

  /**
   * 验签
   *
   * @param map
   * @param signKey
   * @return
   */
  public static Boolean validSignaturedMap(Map<String, String> map, String signKey) {
    Boolean ressult = true;
    if (map != null) {
      String signaturedStr = signMap(map, signKey);
      String signature = map.get("signature");
      ressult = signature != null && signature.equals(signaturedStr);
    }
    return ressult;
  }

  private static String signMap(Map<String, String> map, String signKey) {
    String toSignStr = getToSignStr(map);
    return signStr(toSignStr, signKey);
  }

  /** 签名 */
  private static String signStr(String toSignStr, String signKey) {
    return SecureUtil.sha1(SecureUtil.md5(toSignStr + signKey));
  }

  private static String getToSignStr(Map<String, String> map) {
    Set<String> keySet = map.keySet();
    List<String> list = new ArrayList<>();
    list.addAll(keySet);
    // 字典排序
    Collections.sort(list);
    // value相加
    StringBuilder sbd = new StringBuilder();
    for (String key : list) {
      // 忽略signature 字段
      if ("signature".equals(key)) {
        continue;
      }
      String value = map.get(key);
      sbd.append(value);
    }
    return sbd.toString();
  }
}
