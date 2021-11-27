package app.web.pavelk.cryptography1;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Arrays;

class Main1 {
    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {

        Security.addProvider(new BouncyCastleProvider());
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        SecretKey secretKey = keyGenerator.generateKey();
        System.out.println(new String(secretKey.getEncoded()) + " " + Arrays.toString(secretKey.getEncoded()));

        byte[] bytesIV = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(bytesIV);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(bytesIV);

        Cipher cipher1 = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher1.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
        byte[] plainText = "just text text test ".getBytes(StandardCharsets.UTF_8);
        System.out.println(new String(plainText) + " " + Arrays.toString(plainText));

        byte[] cipherText = cipher1.doFinal(plainText);
        System.out.println(new String(cipherText) + " " + Arrays.toString(cipherText));

        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");
        Cipher cipher2 = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher2.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] plain = cipher2.doFinal(cipherText);
        System.out.println(new String(plain) + " " + Arrays.toString(plain));

    }
}
