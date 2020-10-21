package io.github.talelin.latticy.util;

import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Map;
import java.util.TreeMap;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created By Burt
 * Date: 2017/08/28 14:33
 * description:
 */
public class MD5Util {
    private static final String DES = "DES";
    private static final String MD5 = "MD5";
    private static final String KEY = "elemedev";

    /**
     * md5加密
     *
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String EncoderByMd5(String sourceStr, int data) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            if (data == 32) {
                result = buf.toString();
            } else if (data == 16) {
                result = buf.toString().substring(8, 24);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String byte2hexString(byte[] bytes) {
        StringBuffer bf = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            if ((bytes[i] & 0xFF) < 16) {
                bf.append("T0");
            }
            bf.append(Long.toString(bytes[i] & 0xFF, 16));
        }
        return bf.toString();
    }

    public static String desEncrypt(String data, String key)
            throws Exception {
        if (key == null) {
            key = "elemedev";
        }
        byte[] bt = encrypt(data.getBytes(), key.getBytes());
        String strs = Base64.getEncoder().encodeToString(bt);
        return strs;
    }

    public static String desDecrypt(String data, String key)
            throws IOException, Exception {
        if (data == null) {
            return null;
        }
        if (key == null) {
            key = "elemedev";
        }

        byte[] buf = Base64.getDecoder().decode(data);
        byte[] bt = decrypt(buf, key.getBytes());
        return new String(bt);
    }

    private static byte[] encrypt(byte[] data, byte[] key)
            throws Exception {
        SecureRandom sr = new SecureRandom();

        DESKeySpec dks = new DESKeySpec(key);

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(dks);

        Cipher cipher = Cipher.getInstance("DES");

        cipher.init(1, securekey, sr);
        return cipher.doFinal(data);
    }

    private static byte[] decrypt(byte[] data, byte[] key)
            throws Exception {
        SecureRandom sr = new SecureRandom();

        DESKeySpec dks = new DESKeySpec(key);

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(dks);

        Cipher cipher = Cipher.getInstance("DES");

        cipher.init(2, securekey, sr);
        return cipher.doFinal(data);
    }

    public static EnumSIGN checkSignature(String secret, TreeMap<String, String> params, int seconds) {
        Long cut = Long.valueOf(System.currentTimeMillis() - seconds * 1000);

        if ((params == null) || (params.size() < 1) || (!params.containsKey("sign"))) {
            return EnumSIGN.NOSIGN;
        }

        if (seconds > 15) {
            if (!params.containsKey("timespan")) {
                return EnumSIGN.NOTIME;
            }

            String timespan = (String) params.get("timespan");
            Long times = Long.valueOf((timespan == null) && (timespan.length() < 1) ? 0L : Long.parseLong(timespan));
            times = Long.valueOf(times.toString().length() == 10 ? times.longValue() * 1000L : times.longValue());
            if ((times.toString().length() < 13) || (times.longValue() < cut.longValue())) {
                return EnumSIGN.TIMEOUT;
            }
        }

        String sign = (String) params.get("sign");
        params.remove("sign");

        String signature = getSignature(secret, params);
        if (sign.equals(signature)) {
            return EnumSIGN.VALID;
        }
        return EnumSIGN.INVALID;
    }

    public static String getSignature(String secret, TreeMap<String, String> params) {
        String signature = "";
        try {
            String paraStr = "";
            for (String pName : params.keySet()) {
                paraStr = paraStr + "&" + pName + "=" + (String) params.get(pName);
            }
            byte[] secBytes = secret.getBytes("UTF-8");
            SecretKeySpec signingKey = new SecretKeySpec(secBytes, "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(paraStr.substring(1).getBytes("UTF-8"));
            signature = Base64.getEncoder().encodeToString(rawHmac);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return signature;
    }

    public static String getSignatureQueres(String secret, TreeMap<String, String> params) {
        String url = "";
        try {
            String paraStr = "";
            for (String pName : params.keySet()) {
                paraStr = paraStr + "&" + pName + "=" + (String) params.get(pName);
            }
            byte[] secBytes = secret.getBytes("UTF-8");
            SecretKeySpec signingKey = new SecretKeySpec(secBytes, "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(paraStr.substring(1).getBytes("UTF-8"));
            String signature = Base64.getEncoder().encodeToString(rawHmac);

            url = url + "sign=" + signature + paraStr;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static enum EnumSIGN {
        VALID("签名有效 ", 0), NOSIGN("签名缺失", 1), NOTIME("时间戳不合法", 2), INVALID("签名无效", 3), TIMEOUT("签名已失效", 4);

        private int code;
        private String msg;

        private EnumSIGN(String msg, int code) {
            this.msg = msg;
            this.code = code;
        }

        public String getMessage() {
            return this.msg;
        }
    }
}
