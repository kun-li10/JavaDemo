package com.jd.AsyEny;


import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.AsymmetricCrypto;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.crypto.symmetric.SymmetricCrypto;


/**
 * 非对称加密
 *
 * @author lk
 * @version 1.0
 * @date 2020/9/15 17:22
 */
public class Encrypt {

  //对称加密
  public static class Symmetric {
    public static enum TYPE {
      AES, ARCFOUR, Blowfish, DES, DESede, RC2, PBEWithMD5AndDES, PBEWithSHA1AndDESede,
      PBEWithSHA1AndRC2_40;
    }

    public static String generateKey(String algorithm, String key) {
      return HexUtil.encodeHexStr(SecureUtil.generateKey(algorithm, key == null ? null :
          key.getBytes()).getEncoded());
    }

    public static String encrypt(String algorithm, String key, String content) {
      //构建
      SymmetricCrypto sym = new SymmetricCrypto(algorithm, HexUtil.decodeHex(key));
      //加密为16进制表示
      String encryptHex = sym.encryptHex(content);
      return encryptHex;
    }

    public static String decrypt(String algorithm, String key, String encrypt) {
      //构建
      SymmetricCrypto sym = new SymmetricCrypto(algorithm, HexUtil.decodeHex(key));
      //解密为字符串
      String decryptStr = sym.decryptStr(encrypt, CharsetUtil.CHARSET_UTF_8);
      return decryptStr;
    }

  }

  //非对称加密
  public static class Asymmetric {

  }

  //摘要加密
  public static class Digest {
    public static enum TYPE {
      MD2, MD5, SHA1, SHA256, SHA384, SHA512;
    }

    public static String encrypt(String algorithm, String content) {
      Digester dgt = new Digester(DigestAlgorithm.valueOf(algorithm));
      return dgt.digestHex(content);
    }
  }

  public static void main(String[] args) {
    String aa = "123456";
    String k = null;
    String key = Encrypt.Symmetric.generateKey(Encrypt.Symmetric.TYPE.DES.toString(), k);
    System.out.println(key);
    String bb = Encrypt.Symmetric.encrypt(Encrypt.Symmetric.TYPE.DES.toString(), key, aa);
    System.out.println(bb);
    String cc = Encrypt.Symmetric.decrypt(Encrypt.Symmetric.TYPE.DES.toString(), key, bb);
    System.out.println(cc);

    String privateKey =
        "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJjf24urWBZeIhiTe3X0tZeb9dzk+khC" +
            "/B3Bru3FICfBcBigmJjWVBxD22ksmqEUZLhx0yidV3JnTezbajwjVHHwb2gK7tT/DwzkFB1tmr895wC2/Y" +
            "/fbd" +
            "/GVh4DVb09Y6kwLH7WE665XHAIyuh9WgrHqOHjUyEaxDi342IpNTjVAgMBAAECgYBEMUdLzEjk4WSwNcmS2m" +
            "/Oi0FnIbULWuX/mBAh/BbV5RHNyoWHV8P5P8O17LGlPOWY6R1aNqV1YON" +
            "/znhbDx9xajCoNCAro1QR1QUAtMiW0Z7sZACYuKZS1RSpEC/r0lVWYIbUuqQn8AHZ4kqqI/V0Fj" +
            "+3lNcNUinAiC5aUGO+mQJBAN5AIoBtIhv2dJBuUqs1rAn/NQ/KWkcXg5jESC7olvayN9fSgX" +
            "+mgowSGVZCqacfuRYjpTZUbi7J6xyJAFkFTssCQQCwFsQtRvEeQquwAiUdfZSTZKtQf3DZ3XLvCDpIJjfkkgRFINS1yBl0A1+cVly6PoWkYwQ0yrLpQPKs7eEh6ALfAkAZhbfwk5fFKko8g87Ohn1ZMIuBYrV3UIX2NyQq7t7XOaQcDrp8VDzNpQ5vz3v4CzaQCkvgr1Vv3hQ31KvLjUZ9AkAw4+FMAOppUHGCyNWtPnTGB6lZDEk09Ds5CrvD1HioSbJNzzO/1PLcNyOQsJnGTB2m6qb8UVsjUBkQ0mszkstZAkEAgRyfBRy8Z1ZkufvGEME16Wgu/fU42UcNqWq5jGcEhsdjN17ddtxIBltSyLo1Apjpqw8ZOha6SBdHr/JiStdrwA==";

    String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCY39uLq1gWXiIYk3t19LWXm" +
        "/Xc5PpIQvwdwa7txSAnwXAYoJiY1lQcQ9tpLJqhFGS4cdMonVdyZ03s22o8I1Rx8G9oCu7U/w8M5BQdbZq" +
        "/PecAtv2P323fxlYeA1W9PWOpMCx+1hOuuVxwCMrofVoKx6jh41MhGsQ4t+NiKTU41QIDAQAB";
  }
}
