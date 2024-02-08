import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CardValidation {
    public static void main(String[] args) {

        try {
            String bin = JOptionPane.showInputDialog("Введите первые 6 цифр банковской карты:");
            if (bin != null){
                String URL = "https://api.bincodes.com/bin/?format=json&api_key=45c7faa37f0729d7579adef41b5a1f25&bin=" + bin;

                URL url = new URL(URL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = bufferedReader.readLine()) != null) {
                    response.append(inputLine+"\n");
                }
                bufferedReader.close();

                JOptionPane.showMessageDialog(null, response.toString() );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
