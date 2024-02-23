import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class DiffieHellmanAlgorithm {
    public static void main(String[] args) {
        // вариант 19
        int P = 59;
        int G = 61;
        //случайным образом выбираем приватные ключи
        Random random = new Random();
        int privateBob = random.nextInt(100);
        System.out.println(privateBob + " - приватный ключ Боба");
        int privateAlice = random.nextInt(100);
        System.out.println(privateAlice + " - приватный ключ Алисы");
        //вычисляем публичные ключи
        //при возведении в степень получаются очень большие числа, поэтому используем BigInteger
        BigInteger public1 = new BigInteger(String.valueOf(G));
        public1 = public1.pow(privateBob).mod(BigInteger.valueOf(P));
        System.out.println(public1 + " - публичный ключ Боба");

        BigInteger public2 = new BigInteger(String.valueOf(G));
        public2 = public2.pow(privateAlice).mod(BigInteger.valueOf(P));
        System.out.println(public2 + " - публичный ключ Алисы");

        //Вычисляем секретный код
        BigInteger sharedSecret1 = public2.pow(privateBob).mod(BigInteger.valueOf(P));
        BigInteger sharedSecret2 = public1.pow(privateAlice).mod(BigInteger.valueOf(P));

        System.out.println(sharedSecret1 + " - секретный код получил Боб");
        System.out.println(sharedSecret2 + " - секретный код получила Алиса");

        // шифруем сообщение по алгоритму цезаря
        System.out.println("Введите фразу, которую нужно зашифровать (используя строчные латинские буквы)");
        Scanner scanner = new Scanner(System.in);
        String cipherMessage = cipher(scanner.nextLine(), sharedSecret1.intValue());
        System.out.println(cipherMessage + " - зашифрованная фраза");

        // расшифровываем соообщение
        System.out.println(decipher(cipherMessage, sharedSecret2.intValue()) + " - расшифрованная фраза");
    }

    public static String decipher(String message, int offset) {
        return cipher (message, 26 - (offset % 26));
    }

    public static String cipher(String message, int offset) {
        StringBuilder result = new StringBuilder();
        for (char character : message.toCharArray()) {
            if (character != ' ') {
                int originalAlphabetPosition = character - 'a';
                int newAlphabetPosition = (originalAlphabetPosition + offset) % 26;
                char newCharacter = (char) ('a' + newAlphabetPosition);
                result.append(newCharacter);
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }
}
