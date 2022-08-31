package app.web.pavelk.cryptography1;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class MainRSA {

    public static final String CRYPTOGRAPHY_1_RSA_SECRET_MESSAGE_1_TXT = "cryptography1/rsa/secretMessage1.txt";
    public static final String CRYPTOGRAPHY_1_RSA_PUBLIC_RSA_TXT = "cryptography1/rsa/publicRSA.txt";
    public static final String CRYPTOGRAPHY_1_RSA_PRIVATE_RSA_TXT = "cryptography1/rsa/privateRSA.txt";

    public static void main(String[] args) throws Exception {
//        generatedKeyFileTest();

//        saveSecret();

//        readSecret();

        testTempFile();

    }

    private static void testTempFile() throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        PublicKey publicKeyFile = getKey(CRYPTOGRAPHY_1_RSA_PUBLIC_RSA_TXT);
        PrivateKey privateKeyFile = getPrivateKey(CRYPTOGRAPHY_1_RSA_PRIVATE_RSA_TXT);
        Path tempFile = Files.createTempFile("temp_RSA_TEST_1", "txt");
        System.out.println(tempFile);
        Files.writeString(tempFile, "some secret message 89 89 89");
        System.out.println("some secret message 89 89 89");
        byte[] fileBytes = Files.readAllBytes(tempFile);
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKeyFile);
        byte[] encryptedFileBytes = encryptCipher.doFinal(fileBytes);
        try (FileOutputStream stream = new FileOutputStream(tempFile.toFile())) {
            stream.write(encryptedFileBytes);
        }

        byte[] encryptedFileBytes2 = Files.readAllBytes(tempFile);
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKeyFile);
        byte[] decryptedFileBytes = decryptCipher.doFinal(encryptedFileBytes2);
        try (FileOutputStream stream = new FileOutputStream(tempFile.toFile())) {
            stream.write(decryptedFileBytes);
        }
        String fileContent = Files.readString(tempFile);
        System.out.println(fileContent);
    }

    private static void readSecret() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException {
        PublicKey publicKeyFile = getKey(CRYPTOGRAPHY_1_RSA_PUBLIC_RSA_TXT);
        PrivateKey privateKeyFile = getPrivateKey(CRYPTOGRAPHY_1_RSA_PRIVATE_RSA_TXT);
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKeyFile);
        byte[] bytes = Files.readAllBytes(Path.of(CRYPTOGRAPHY_1_RSA_SECRET_MESSAGE_1_TXT));
        byte[] decode = Base64.getDecoder().decode(bytes);
        byte[] decryptedMessageBytes = decryptCipher.doFinal(decode);
        String decryptedMessage = new String(decryptedMessageBytes, StandardCharsets.UTF_8);
        System.out.println(decryptedMessage);
    }

    private static void saveSecret() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        PublicKey publicKeyFile = getKey(CRYPTOGRAPHY_1_RSA_PUBLIC_RSA_TXT);
        PrivateKey privateKeyFile = getPrivateKey(CRYPTOGRAPHY_1_RSA_PRIVATE_RSA_TXT);

        String secretMessage = "TEST secret message 789 789";

        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKeyFile);
        byte[] secretMessageBytes = secretMessage.getBytes(StandardCharsets.UTF_8);
        byte[] encryptedMessageBytes = encryptCipher.doFinal(secretMessageBytes);
        String encodedMessage = Base64.getEncoder().encodeToString(encryptedMessageBytes);
        saveString(encodedMessage, CRYPTOGRAPHY_1_RSA_SECRET_MESSAGE_1_TXT);
        System.out.println(secretMessage);

    }

    private static void generatedKeyFileTest() throws NoSuchAlgorithmException, IOException {
        Files.createDirectories(Path.of("cryptography1/rsa"));
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair pair = generator.generateKeyPair();

        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();


        saveKey(publicKey, CRYPTOGRAPHY_1_RSA_PUBLIC_RSA_TXT);
        saveKey(privateKey, CRYPTOGRAPHY_1_RSA_PRIVATE_RSA_TXT);
        PublicKey publicKeyFile = getKey(CRYPTOGRAPHY_1_RSA_PUBLIC_RSA_TXT);
        PrivateKey privateKeyFile = getPrivateKey(CRYPTOGRAPHY_1_RSA_PRIVATE_RSA_TXT);
        System.out.println(privateKey);
        System.out.println(privateKeyFile);
        System.out.println("------------------");
        System.out.println(publicKey);
        System.out.println(publicKeyFile);
    }

    private static void saveString(String s, String path) {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(s.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static PrivateKey getPrivateKey(String path) {
        try {
            File privateKeyFile = new File(path);
            byte[] privateKeyBytes = Files.readAllBytes(privateKeyFile.toPath());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privateKeyBytes);
            return keyFactory.generatePrivate(spec);

        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }

    }

    private static PublicKey getKey(String path) {
        try {
            File publicKeyFile = new File(path);
            byte[] publicKeyBytes = Files.readAllBytes(publicKeyFile.toPath());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
            PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
            return publicKey;

        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }

    }

    private static void saveKey(Key publicKey, String path) {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(publicKey.getEncoded());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
