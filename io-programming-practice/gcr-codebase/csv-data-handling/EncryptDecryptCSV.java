package csv_data_handling;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.util.Base64;

public class EncryptDecryptCSV {

    private static final String KEY = "1234567890123456"; // 16 chars
    private static final String ALGO = "AES";

    // Encrypt
    public static String encrypt(String value) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGO);
        SecretKeySpec key = new SecretKeySpec(KEY.getBytes(), ALGO);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return Base64.getEncoder().encodeToString(cipher.doFinal(value.getBytes()));
    }

    // Decrypt
    public static String decrypt(String value) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGO);
        SecretKeySpec key = new SecretKeySpec(KEY.getBytes(), ALGO);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(Base64.getDecoder().decode(value)));
    }

    // Encrypt CSV
    public static void encryptCSV(String input, String output) throws Exception {

        BufferedReader br = new BufferedReader(new FileReader(input));
        BufferedWriter bw = new BufferedWriter(new FileWriter(output));

        String line = br.readLine(); // header
        bw.write(line);
        bw.newLine();

        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");

            if (data.length < 5) continue; // safety

            data[2] = encrypt(data[2]); // email
            data[3] = encrypt(data[3]); // salary

            bw.write(String.join(",", data));
            bw.newLine();
        }

        br.close();
        bw.close();
    }

    // Read encrypted CSV & decrypt
    public static void readDecryptedCSV(String file) throws Exception {

        BufferedReader br = new BufferedReader(new FileReader(file));
        br.readLine(); // skip header

        String line;
        while ((line = br.readLine()) != null) {

            String[] data = line.split(",");

            if (data.length < 5) {
                System.out.println("❌ Invalid row skipped: " + line);
                continue;
            }

            System.out.println(
                "ID: " + data[0] +
                ", Name: " + data[1] +
                ", Email: " + decrypt(data[2]) +
                ", Salary: " + decrypt(data[3]) +
                ", Dept: " + data[4]
            );
        }
        br.close();
    }

    public static void main(String[] args) throws Exception {

        encryptCSV("employees.csv", "encrypted_employees.csv");

        System.out.println("🔐 Encrypted CSV created");
        System.out.println("🔓 Decrypted output:\n");

        readDecryptedCSV("encrypted_employees.csv");
    }
}
