import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class Q36_HTTPClientAPI {
    public static void main(String[] args) {
        System.out.println("=== HTTP Client API (Java 11+) ===");

        // Using a public, stable API endpoint
        String apiUri = "https://httpbin.org/get";
        System.out.println("Sending GET request to: " + apiUri);

        // 1. Build the HttpClient
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        // 2. Build the HttpRequest
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUri))
                .header("Accept", "application/json")
                .header("User-Agent", "Java HttpClient Demo")
                .GET()
                .build();

        // 3. Send request and print response
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("\n--- Response Status Code ---");
            System.out.println("Status: " + response.statusCode());

            System.out.println("\n--- Response Headers ---");
            response.headers().map().forEach((k, v) -> System.out.println(k + ": " + v));

            System.out.println("\n--- Response Body ---");
            System.out.println(response.body());

        } catch (Exception e) {
            System.err.println("HTTP Request failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
