package com.example.quanlygiasu_md18202_duan1.MoMoController;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Formatter;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class MoMoUtils {
    private static final String HMAC_SHA256 = "HmacSHA256";

    private String toHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        Formatter formatter = new Formatter(sb);
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }
        return sb.toString();
    }

    public String signHmacSHA256(String data, String secretKey) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), HMAC_SHA256);
        Mac mac = Mac.getInstance(HMAC_SHA256);
        mac.init(secretKeySpec);
        byte[] rawHmac = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return toHexString(rawHmac);
    }


    // Phương thức mã hóa dữ liệu JSON bằng publicKey RSA
    public String encryptDataWithRSA(String jsonData, String publicKey) {
        try {
            byte[] publicKeyBytes = Base64.decode(publicKey, Base64.DEFAULT);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKeyS = keyFactory.generatePublic(keySpec);

            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKeyS);
            byte[] encryptedBytes = cipher.doFinal(jsonData.getBytes("UTF-8"));


            return Base64.encodeToString(encryptedBytes, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
