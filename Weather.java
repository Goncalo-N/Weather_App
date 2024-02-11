import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Weather {
    private static final String API_KEY = "API_KEY"; // Replace with your API key
    private static final String API_URL = "https://api.weatherapi.com/v1/current.json?key=" + API_KEY + "&q=";

    public static void main(String[] args) {
        String location = "Lisboa"; // Replace with the desired location

        try {
            URL url = new URL(API_URL + location);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            } 

            reader.close();
            connection.disconnect();
            JsonObject jsonObject = JsonParser.parseString(response.toString()).getAsJsonObject();
            JsonObject current = jsonObject.getAsJsonObject("current");
            String temperature = current.get("temp_c").getAsString();
            String condition = current.getAsJsonObject("condition").get("text").getAsString();

            // Display the weather information
            System.out.println("Temperature: " + temperature + "°C");
            System.out.println("Condition: " + condition);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
