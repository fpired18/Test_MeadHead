package com.architecteLogicielP11.Test_MeadHead;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class TestMeadHeadApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testListJsonTruePath() {
		HttpClient client = HttpClient.newHttpClient();
		String truePath = "hospital";
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(String.format("http://localhost:9010/" + truePath))).GET().build();
		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() == 200) {
				String jsonString = response.body();
				ObjectMapper mapper = new ObjectMapper();

				List<Post> posts = mapper.readValue(response.body(), new TypeReference<List<Post>>() {
				});

				int numberRecord = 0;
				numberRecord = posts.size();
				System.out.println("Voila ce que donne numberRecord: " + numberRecord);

				posts.forEach(System.out::println);

				System.out.println("\n-------------------------------------------------");
				System.out.println("       Tout est OK dans testListJsonTruePath");
				System.out.println("-------------------------------------------------");
				System.out.println("Response.statusCode dans testListJson = " + response.statusCode());
				System.out.println("Voila ce que donne un jsonString: " + jsonString);

			} else {
				System.out.println("--------------------------------------------------------");
				System.out.println("     Il y a un code 404 dans testListJsonTruePath() ");
				System.out.println("--------------------------------------------------------");
				System.out.println(response);
			}
			if (response.body().contains("numberOfBeds")) {
				System.out.println("------------------------------------");
				System.out.println("  Y a t'il un nombre de lits? " + response.body().contains("numberOfBeds"));
				System.out.println("------------------------------------");
			}
		} catch (Exception e) {
			System.out.println("\n***********************************\n");
			System.out.println("    Il y a un probl??me dans testListJsonTruePath! :" + e + "ligne 63 ");
			System.out.println("\n***********************************");
			e.printStackTrace();
		}
	}

	@Test
	void testListJsonFalsePath() {
		HttpClient client = HttpClient.newHttpClient();
		String falsePath = "hospital";
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(String.format("http://localhost:9010/" + falsePath))).GET().build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() == 200) {
				String jsonString = response.body();
				ObjectMapper mapper = new ObjectMapper();

				List<Post> posts = mapper.readValue(response.body(), new TypeReference<List<Post>>() {
				});

				int numberRecord = 0;
				numberRecord = posts.size();
				System.out.println("Voila ce que donne numberRecord: " + numberRecord);

				posts.forEach(System.out::println);

				System.out.println("\n---------------------------------------------------");
				System.out.println("       Tout est OK dans testListJsonFalsePath");
				System.out.println("---------------------------------------------------");
				System.out.println("Response.statusCode dans testListJson = " + response.statusCode());
				System.out.println("Voila ce que donne un jsonString: " + jsonString);

			} else {
				System.out.println("--------------------------------------------------------");
				System.out.println("     Il y a un code 404 dans testListJsonFalsePath ");
				System.out.println("--------------------------------------------------------");
				System.out.println(response);
			}
			if (response.body().contains("numberOfBeds")) {
				System.out.println("------------------------------------");
				System.out.println("  Y a t'il un nombre de lits? " + response.body().contains("numberOfBeds"));
				System.out.println("------------------------------------");
			}
		} catch (Exception e) {
			System.out.println("\n*********************************************\n");
			System.out.println("    Il y a un probl??me dans testListJsonFalsePath! :" + e + "ligne 110 ");
			System.out.println("\n*********************************************");
			e.printStackTrace();
		}
	}

	@Test
	public void testWhichTheNearestHospitalWithThisSpeciality() {
		String lookForSpeciality = "Cardiologie";
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(String.format("http://localhost:9010/hospital/speciality/" + lookForSpeciality))).GET()
				.build();
		// String inputJson =
		// "{\"specialityRequest\":\"Cardiologie\",\"numberOfBedsAvailableRequest\":1\",\"latPatient\":5.1\",\"lonPatient\":1.6\"}";
		// var request1 = HttpRequest.newBuilder().uri(URI.create(postEndpoint))
		// .POST(HttpRequest.BodyPublishers.ofString(inputJson)).build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("\nle retour de response2.body(): " + response.body());
			ObjectMapper mapper = new ObjectMapper();
			List<Post> posts = mapper.readValue(response.body(), new TypeReference<List<Post>>() {
			});
			if (posts.size() == 0) {
				System.out.println(
						"\n******************************************************************************************************************\n");
				System.out.println("    Il n'y a pas d'h??pital assez proche ");
				System.out.println(
						"\n******************************************************************************************************************");
			} else {
				System.out.println(
						"\n*****************************************************************************************************");
				System.out.println("   Voici les ??tablissements qui ont la sp??cialit?? : ");
				System.out.println(
						"*****************************************************************************************************\n");
				for (Post item : posts) {

					System.out.println(
							"   L'??tablissement de " + item.getHospitalCenter() + " est ?? " + item.getDistance());
				}
				System.out.println(
						"\n*****************************************************************************************************");
				System.out.println("\n*************************************************************\n");
				System.out.println("   Voila posts.size(): " + posts.size() + " dans testIfSpecialityExistsTrue");
				System.out.println("\n*************************************************************");
			}
		} catch (Exception e) {
			System.out.println("\n**********************************************************************\n");
			System.out.println(
					"    Il y a un probl??me dans \n" + "	dans testIfSpecialityExistsTrue!: " + e + "ligne 160 ");
			System.out.println("\n**********************************************************************");
			e.printStackTrace();
		}
	}

	@Test
	public void testIfSpecialityExistsTrue() {
		String lookForSpeciality = "Cardiologie";
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(String.format("http://localhost:9010/hospital/speciality/" + lookForSpeciality))).GET()
				.build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("\nle retour de response.body(): " + response.body());
			ObjectMapper mapper = new ObjectMapper();
			List<Post> posts = mapper.readValue(response.body(), new TypeReference<List<Post>>() {
			});
			if (posts.size() == 0) {
				System.out.println(
						"\n******************************************************************************************\n");
				System.out.println("  Pas d'h??pital qui assume la sp??cialit??! : " + lookForSpeciality
						+ " dans testIfSpecialityExistsTrue");
				System.out.println(
						"\n******************************************************************************************");
			} else {
				System.out.println(
						"\n******************************************************************************************************************\n");
				System.out.println("   Voici les ??tablissements qui ont la sp??cialit??: " + lookForSpeciality
						+ " dans testIfSpecialityExistsTrue");
				System.out.println(
						"\n******************************************************************************************************************");
				posts.forEach(System.out::println);
				System.out.println("\n*************************************************************\n");
				System.out.println("   Voila posts.size(): " + posts.size() + " dans testIfSpecialityExistsTrue");
				System.out.println("\n*************************************************************");
			}
		} catch (Exception e) {
			System.out.println("\n**********************************************************************\n");
			System.out.println(
					"    Il y a un probl??me dans \n" + "	dans testIfSpecialityExistsTrue! : " + e + "ligne 201 ");
			System.out.println("\n**********************************************************************");
			e.printStackTrace();
		}
	}

	@Test
	public void testIfSpecialityExistsFalse() {
		String lookForSpeciality = "Orthop??die";
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(String.format("http://localhost:9010/hospital/speciality/" + lookForSpeciality))).GET()
				.build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("\nle retour de response.body(): " + response.body());
			ObjectMapper mapper = new ObjectMapper();
			List<Post> posts = mapper.readValue(response.body(), new TypeReference<List<Post>>() {
			});
			if (posts.size() == 0) {
				System.out.println(
						"\n******************************************************************************************\n");
				System.out.println("  Pas d'h??pital qui assume la sp??cialit??! : " + lookForSpeciality
						+ " dans testIfSpecialityExistsFalse");
				System.out.println(
						"\n******************************************************************************************");
			} else {
				System.out.println(
						"\n******************************************************************************************************************\n");
				System.out.println("   Voici les ??tablissements qui ont la sp??cialit??: " + lookForSpeciality
						+ " dans testIfSpecialityExistsFalse");
				System.out.println(
						"\n******************************************************************************************************************");
				posts.forEach(System.out::println);
				System.out.println("\n*************************************************************\n");
				System.out.println("   Voila posts.size(): " + posts.size() + " dans testIfSpecialityExistsFalse");
				System.out.println("\n*************************************************************");
			}
		} catch (Exception e) {
			System.out.println("\n**********************************************************************\n");
			System.out.println(
					"    Il y a un probl??me dans \n" + "	dans testIfSpecialityExistsFalse! : " + e + "ligne 242 ");
			System.out.println("\n**********************************************************************");
			e.printStackTrace();
		}
	}

	@Test
	public void testIfSpecialityGroupExistsTrue() {
		String lookForSpecialityGroup = "Anesthesie";
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(
						String.format("http://localhost:9010/hospital/specialityGroup/" + lookForSpecialityGroup)))
				.GET().build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("\nle retour de response2.body(): " + response.body());
			ObjectMapper mapper = new ObjectMapper();
			List<Post> posts = mapper.readValue(response.body(), new TypeReference<List<Post>>() {
			});
			if (posts.size() == 0) {
				System.out.println(
						"\n****************************************************************************************************\n");
				System.out.println("   Pas d'h??pitaux qui ont le Groupe de sp??cialit??s! : " + lookForSpecialityGroup
						+ " dans testIfSpecialityGroupExistsTrue");
				System.out.println(
						"\n****************************************************************************************************");
			} else {
				int i = 1;
				System.out.println(
						"\n*************************************************************************************************************************");
				System.out.println("   Voici la liste des ??tablissements qui ont le Groupe de sp??cialit??: "
						+ lookForSpecialityGroup + " dans testIfSpecialityGroupExistsTrue");
				System.out.println(
						"*************************************************************************************************************************\n");
				for (Post item : posts) {

					System.out.println("   L'??tablissement de n??: " + i + " " + item.getHospitalCenter()
							+ " Assure le groupe de sp??cialit??s: " + lookForSpecialityGroup
							+ " dans testIfSpecialityGroupExistsTrue");
					i += 1;
				}
				System.out.println(
						"\n*************************************************************************************************************************");
				System.out.println(
						"\n**********************************************************************************\n");
				System.out.println("   Voici la valeur de posts.size() : " + posts.size()
						+ " dans testIfSpecialityGroupExistsTrue");
				System.out.println(
						"\n**********************************************************************************");
			}
		} catch (Exception e) {
			System.out.println("\n**********************************************************************\n");
			System.out.println("    Il y a un probl??me dans \n" + "	dans testIfSpecialityGroupExistsTrue! : " + e
					+ "ligne 296 ");
			System.out.println("\n**********************************************************************");
			e.printStackTrace();
		}
	}

	@Test
	public void testIfSpecialityGroupExistsFalse() {
		String lookForSpecialityGroup = "V??r??rinaire";
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(
						String.format("http://localhost:9010/hospital/specialityGroup/" + lookForSpecialityGroup)))
				.GET().build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("\nle retour de response2.body(): " + response.body());
			ObjectMapper mapper = new ObjectMapper();
			List<Post> posts = mapper.readValue(response.body(), new TypeReference<List<Post>>() {
			});
			if (posts.size() == 0) {
				System.out.println(
						"\n*******************************************************************************************************************\n");
				System.out.println("   Il n'y a pas d'h??pitaux qui ont le Groupe de sp??cialit??s! : "
						+ lookForSpecialityGroup + " dans testIfSpecialityGroupExistsFalse");
				System.out.println(
						"\n*******************************************************************************************************************");
			} else {
				System.out.println(
						"\n********************************************************************************************************************\n");
				System.out.println("   Voici les ??tablissements qui ont le Groupe de sp??cialit??s: "
						+ lookForSpecialityGroup + " dans testIfSpecialityGroupExistsFalse");
				System.out.println(
						"\n********************************************************************************************************************");
				posts.forEach(System.out::println);
				System.out.println(
						"\nn**********************************************************************************\n");
				System.out
						.println("   Voila posts.size() : " + posts.size() + " dans testIfSpecialityGroupExistsFalse");
				System.out.println(
						"\nn**********************************************************************************");
			}
		} catch (Exception e) {
			System.out.println("\n**********************************************************************\n");
			System.out.println("    Il y a un probl??me dans \n" + "	dans testIfSpecialityGroupExistsFalse! : " + e
					+ "ligne 341 ");
			System.out.println("\n**********************************************************************");
			e.printStackTrace();
		}
	}

	@Test
	public void testIfHopitalCenterExistsTrue() {
		String lookForHopitalCenter = "Orl??ans";
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(String.format("http://localhost:9010/hospital/hospitalCenter/" + lookForHopitalCenter)))
				.GET().build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("\nle retour de response2.body(): " + response.body());
			ObjectMapper mapper = new ObjectMapper();
			List<Post> posts = mapper.readValue(response.body(), new TypeReference<List<Post>>() {
			});
			if (posts.size() == 0) {
				System.out.println(
						"\n**********************************************************************************************\n");
				System.out.println("   Il n'y a pas d'h??pitaux dans la ville de : " + lookForHopitalCenter
						+ " dans testIfHopitalCenterExistsTrue");
				System.out.println(
						"\n**********************************************************************************************");
			} else {
				System.out.println(
						"\n**********************************************************************************************\n");
				System.out.println("   La ville de : " + lookForHopitalCenter
						+ " a un ??tablissement hospitalier dans testIfHopitalCenterExistsTrue");
				System.out.println(
						"\n**********************************************************************************************");
				System.out.println("\n*****************************************************************************\n");
				System.out.println(
						"   Voici la valeur de posts.size() : " + posts.size() + " dans testIfHopitalCenterExistsTrue");
				System.out.println("\n*****************************************************************************");
			}
		} catch (Exception e) {
			System.out.println("\n**********************************************************************\n");
			System.out.println(
					"    Il y a un probl??me dans \n" + "	dans testIfHopitalCenterExistsTrue! : " + e + "ligne 382 ");
			System.out.println("\n**********************************************************************");
			e.printStackTrace();
		}
	}

	@Test
	public void testIfHopitalCenterExistsFalse() {
		String lookForHopitalCenter = "Ailleurs";
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(String.format("http://localhost:9010/hospital/hospitalCenter/" + lookForHopitalCenter)))
				.GET().build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("\nle retour de response2.body(): " + response.body());
			ObjectMapper mapper = new ObjectMapper();
			List<Post> posts = mapper.readValue(response.body(), new TypeReference<List<Post>>() {
			});
			if (posts.size() == 0) {
				System.out.println(
						"\n**********************************************************************************************\n");
				System.out.println("   Il n'y a pas d'h??pitaux dans la ville de : " + lookForHopitalCenter
						+ " dans testIfHopitalCenterExistsFalse");
				System.out.println(
						"\n**********************************************************************************************");
			} else {
				System.out.println(
						"\n**********************************************************************************************\n");
				System.out.println("   La ville de : " + lookForHopitalCenter
						+ " a un ??tablissement hospitalier dans testIfHopitalCenterExistsFalse");
				System.out.println(
						"\n**********************************************************************************************");
				System.out.println("\n*****************************************************************************\n");
				System.out.println("   Voici la valeur de posts.size() : " + posts.size()
						+ " dans testIfHopitalCenterExistsFalse");
				System.out.println("\n*****************************************************************************");
			}
		} catch (Exception e) {
			System.out.println("\n**********************************************************************\n");
			System.out.println("    Il y a un probl??me dans \n" + "	dans testIfHopitalCenterExistsFalse! : " + e
					+ "ligne 423 ");
			System.out.println("\n**********************************************************************");
			e.printStackTrace();
		}
	}

	@Test
	public void testNumberOfBedsInThisHospital() {
		String numberOfBedsInThisHospital = "Toulouse";
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(
						String.format("http://localhost:9010/hospital/hospitalCenter/" + numberOfBedsInThisHospital)))
				.GET().build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("\nle retour de response2.body(): " + response.body());
			ObjectMapper mapper = new ObjectMapper();
			List<Post> posts = mapper.readValue(response.body(), new TypeReference<List<Post>>() {
			});
			if (posts.size() == 0) {
				System.out.println("\n**************************************************************************\n");
				System.out.println("   Il n'y a pas d'h??pitaux dans la ville de : " + numberOfBedsInThisHospital
						+ " dans testNumberOfBedsInThisHospital");
				System.out.println("\n**************************************************************************");
			} else {
				int numberOfTurn = 0;
				for (Post item : posts) {
					if (item.getNumberOfBeds() != -1 && numberOfTurn == 0) {
						System.out.println(
								"\n******************************************************************************************");
						System.out.println("\nLe nombre de lit dans la ville de " + numberOfBedsInThisHospital
								+ " est de " + item.getNumberOfBeds() + " dans testNumberOfBedsInThisHospital");
						System.out.println(
								"\n******************************************************************************************\n");
						numberOfTurn += 1;
					}
				}
			}
		} catch (Exception e) {
			System.out.println("\n**********************************************************************\n");
			System.out.println("    Il y a un probl??me	dans testNumberOfBedsInThisHospital : " + e + "ligne 465 ");
			System.out.println("\n**********************************************************************");
			e.printStackTrace();
		}
	}

	@Test
	public void testWhatSpecialitiesHaveThisHospital() {
		String whatSpecialitiesHaveThisHospital = "Orl??ans";
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(String
						.format("http://localhost:9010/hospital/hospitalCenter/" + whatSpecialitiesHaveThisHospital)))
				.GET().build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("\nle retour de response2.body(): " + response.body());
			ObjectMapper mapper = new ObjectMapper();
			List<Post> posts = mapper.readValue(response.body(), new TypeReference<List<Post>>() {
			});
			if (posts.size() == 0) {
				System.out.println(
						"\n******************************************************************************************************************\n");
				System.out.println("    Pas d'h??pital dans la ville de !: " + whatSpecialitiesHaveThisHospital
						+ " dans testWhatSpecialitiesHaveThisHospital");
				System.out.println(
						"\n******************************************************************************************************************");
			} else {
				int i = 1;
				System.out.println(
						"\n*************************************************************************************************************************");
				System.out.println("   Voici la liste des sp??cialit??s de : " + whatSpecialitiesHaveThisHospital
						+ " dans testWhatSpecialitiesHaveThisHospital");
				System.out.println(
						"*************************************************************************************************************************\n");
				for (Post item : posts) {

					System.out.println("  Sp??cialisation n??: " + i + " " + item.getSpeciality()
							+ " dans testIfSpecialityGroupExistsTrue");
					i += 1;
				}
				System.out.println(
						"\n*************************************************************************************************************************");
				System.out.println(
						"   Voila posts.size(): " + posts.size() + " dans testWhatSpecialitiesHaveThisHospital");
				System.out.println("\n*********************************************************************");
			}
		} catch (Exception e) {
			System.out.println("\n**********************************************************************\n");
			System.out.println("    Il y a un probl??me dans \n" + "	dans testWhatSpecialitiesHaveThisHospital!: " + e
					+ "ligne 515 ");
			System.out.println("\n**********************************************************************");
			e.printStackTrace();
		}
	}

	@Test
	public void testThisHospitalHasThisSpeciality() {
		String numberOfBedsInThisHospital = "Orl??ans";
		String speciality = "Cardiologie";
		Boolean specialityExist = false;
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(
						String.format("http://localhost:9010/hospital/hospitalCenter/" + numberOfBedsInThisHospital)))
				.GET().build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("\nle retour de response2.body(): " + response.body());
			ObjectMapper mapper = new ObjectMapper();
			List<Post> posts = mapper.readValue(response.body(), new TypeReference<List<Post>>() {
			});
			if (posts.size() == 0) {
				System.out.println("\n**************************************************************************\n");
				System.out.println("   Il n'y a pas d'h??pitaux dans la ville de : " + numberOfBedsInThisHospital
						+ " dans testThisHospitalHasThisSpeciality");
				System.out.println("\n**************************************************************************");
			} else {
				for (Post item : posts) {
					if (item.getSpeciality().equals(speciality)
							&& (item.getHospitalCenter().equals(numberOfBedsInThisHospital))) {
						specialityExist = true;
					}
				}
				// posts.forEach(System.out::println);
				if (specialityExist) {
					System.out.println(
							"\n****************************************************************************************************");
					System.out.println("\nLa ville de " + numberOfBedsInThisHospital + " assure la sp??ciality "
							+ speciality + " dans testThisHospitalHasThisSpeciality");
					System.out.println(
							"\n****************************************************************************************************\n");

				} else {
					System.out.println(
							"\n****************************************************************************************************");
					System.out.println("\nLa ville de " + numberOfBedsInThisHospital + " n'assure pas la sp??ciality "
							+ speciality + " dans testThisHospitalHasThisSpeciality");
					System.out.println(
							"\n****************************************************************************************************\n");
				}
			}
		} catch (Exception e) {
			System.out.println("\n**********************************************************************\n");
			System.out.println("    Il y a un probl??me	dans testThisHospitalHasThisSpeciality : " + e + "ligne 560 ");
			System.out.println("\n**********************************************************************");
			e.printStackTrace();
		}
	}

}
