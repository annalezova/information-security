import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CardValidation2 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Введите первые 6 цифр банковской карты: ");
        String bin = in.nextLine();
        try {
                String URL = "https://api.bincodes.com/bin/?format=json&api_key=45c7faa37f0729d7579adef41b5a1f25&bin=" + bin;

                URL url = new URL(URL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;


                while ((inputLine = bufferedReader.readLine()) != null) {
                    System.out.println(inputLine);
                }
                bufferedReader.close();
            System.out.println(connection.getHeaderField(1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
