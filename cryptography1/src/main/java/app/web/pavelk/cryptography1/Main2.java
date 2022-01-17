package app.web.pavelk.cryptography1;


import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Scanner;

//Симметричное шифрование RC4, AES, DES, 3DES, QUAD
//Асимметричное шифрование RSA, Diffie-Hellman, ECC, El Gamal, DSA
public class Main2 {
    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException, InvalidKeySpecException, IOException {

        String password1 = "trerwewrw";
        String password = new Scanner(System.in).next();
        String salt = "12";
        String algorithm = "AES/CBC/PKCS5Padding";
        IvParameterSpec ivParameterSpec = generateIv();
        IvParameterSpec ivParameterSpec2 = generateIv2(password);
        SecretKey secretKey = getKeyFromPassword(password, salt);

        File f1 = new File("cryptography1/f1.zip");
        File f2 = new File("cryptography1/f2.zip");
        File f3 = new File("cryptography1/f3.zip");

        encryptFile(algorithm, secretKey, ivParameterSpec , f1, f2);
//        decryptFile(algorithm, secretKey, ivParameterSpec2, f2, f3);
    }

    public static void decryptFile(String algorithm, SecretKey key, IvParameterSpec iv, File inputFile, File outputFile)
            throws IOException, NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        fileConvert(inputFile, outputFile, cipher);
    }

    public static void encryptFile(String algorithm, SecretKey key, IvParameterSpec iv, File inputFile, File outputFile)
            throws IOException, NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        fileConvert(inputFile, outputFile, cipher);
    }

    private static void fileConvert(File inputFile, File outputFile, Cipher cipher)
            throws IOException, IllegalBlockSizeException, BadPaddingException {
        FileInputStream inputStream = new FileInputStream(inputFile);
        FileOutputStream outputStream = new FileOutputStream(outputFile);
        byte[] buffer = new byte[64];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            byte[] output = cipher.update(buffer, 0, bytesRead);
            if (output != null) {
                outputStream.write(output);
            }
        }
        byte[] outputBytes = cipher.doFinal();
        if (outputBytes != null) {
            outputStream.write(outputBytes);
        }
        inputStream.close();
        outputStream.close();
    }

    public static SecretKey getKeyFromPassword(String password, String salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
        return new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
    }

    public static IvParameterSpec generateIv() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    public static IvParameterSpec generateIv2(String password) {
        IvParameterSpec result;
        byte[] bytes = password.getBytes();
        if (bytes.length > 16) {
            byte[] iv = new byte[16];
            System.arraycopy(bytes, 0, iv, 0, 16);
            result = new IvParameterSpec(iv);
        } else if (bytes.length < 16) {
            byte[] iv = new byte[16];
            System.arraycopy(bytes, 0, iv, 0, bytes.length);
            result = new IvParameterSpec(iv);
        } else {
            result = new IvParameterSpec(bytes);
        }
        return result;
    }

}
