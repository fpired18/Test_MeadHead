package com.architecteLogicielP11.Test_MeadHead;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.json.simple.parser.JSONParser;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class TestMeadHeadApplication {
	private static final String POSTS_API_URL = "http://localhost:9010/hospital";

	public static void main(String[] args) throws Exception {

		HttpClient client = HttpClient.newHttpClient();
		// HttpRequest request =
		// HttpRequest.newBuilder().uri(URI.create(String.format("http://localhost:9010/hospital")))
		// .GET().build();

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(POSTS_API_URL)).GET().build();
		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("\nle retour de response.body(): " + response.body());

			// parse JSON into objects
			ObjectMapper mapper = new ObjectMapper();

			List<Post> posts = mapper.readValue(response.body(), new TypeReference<List<Post>>() {
			});
			System.out.println("\nVoila ce que donne posts: " + posts);

			posts.forEach(System.out::println);

			if (response.statusCode() == 200) {
				System.out.println("------------------------------------");
				System.out.println("        Tout OK dans le main ");
				System.out.println("------------------------------------\n");
				System.out.println(response.body());
				System.out.println("\nVoila ce que donne response dans le main: " + response);

				String jsonString = response.body();

				System.out.println("\nVoila ce que donne jsonString: " + jsonString);
				Object obj = new JSONParser().parse(jsonString);
				System.out.println("\nVoila ce que donne posts: " + posts.size());
				System.out.println("\nVoila ce que donne obj.toString()): " + obj.toString());
				System.out.println("\nVoila ce que donne obj.toString().indexOf(\"speciality\"): "
						+ obj.toString().indexOf("speciality"));
				System.out.println("\nVoila ce que donne obj.toString().indexOf(\"specialityGroup\"): "
						+ obj.toString().indexOf("specialityGroup"));
			} else {
				System.out.println(response);
			}

		} catch (Exception e) {
			System.out.println("\n***********************************\n");
			System.out.println("Il y a un problème");
			System.out.println("\n***********************************\n");
			e.printStackTrace();

		}
		System.out.println("\n***********************************\n");
		System.out.println("           Tout est OK ");
		System.out.println("\n***********************************\n");

	}
	
}
