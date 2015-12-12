package com.zooplus.challange.util;

import java.nio.charset.Charset;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;

public class CryptoUtil {
    private static final Charset UTF8 = Charset.forName("UTF-8");

    public static String getBase64ofFIMDigest(String data) {
        return base64Encode(getFIMDigest(data));
    }

    public static byte[] getFIMDigest(String data) {
        return getFIMDigest(data, "UTF-8", "SHA-256");
    }

    public static byte[] getFIMDigest(String data, String charsetName, String algorithm) {
        if (data == null) {
            return null;
        }
        if ("".equals(data)) {
            return data.getBytes();
        }
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            digest.update(data.getBytes(charsetName));
            return digest.digest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String base64Encode(String dataStr) {
        if (dataStr == null) {
            return null;
        }
        return base64Encode(dataStr.getBytes(UTF8));
    }

    public static String base64Encode(byte[] data) {
        if (data == null) {
            return null;
        }
        if (data.length == 0) {
            return "";
        }
        return new String(Base64.encodeBase64(data), UTF8);
    }
}
