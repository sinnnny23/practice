package practice.http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpServerCon2 {
    public static void main(String[] args) {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString("{\"command\":\"SPASwipeLogin\", \"usid\":\"sinnnny23\", \"passwd\":\"tlsltlsl@23\"}"))
                    .uri(new URI("https://member.swipepay.co.kr/Command"))
                    .header("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            String statusText = response.version().toString();

            System.out.println("HTTP Response Code: " + statusCode);
            System.out.println("HTTP Response Status: " + statusText);

            String responseBody = response.body();
            System.out.println("Response from server: ");
            System.out.println(responseBody);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
