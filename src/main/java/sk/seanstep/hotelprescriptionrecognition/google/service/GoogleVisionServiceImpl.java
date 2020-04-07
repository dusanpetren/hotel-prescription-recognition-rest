package sk.seanstep.hotelprescriptionrecognition.google.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import sk.seanstep.hotelprescriptionrecognition.google.data.response.GoogleVisionResponse;
import sk.seanstep.hotelprescriptionrecognition.google.factory.GoogleVisionRequestFactory;
import sk.seanstep.hotelprescriptionrecognition.google.predicates.GoogleVisionPredicate;
import static sk.seanstep.hotelprescriptionrecognition.google.predicates.GoogleVisionPredicate.filterResponse;

/**
 * Service for handling Rest api calls to Google Vision Api.
 *
 * @author dusan.petren
 */
@Log4j2
@Service
@AllArgsConstructor
@NoArgsConstructor
public class GoogleVisionServiceImpl implements GoogleVisionService {

	private static final String TARGET_URL = "https://vision.googleapis.com/v1/images:annotate";
	private static final String URL_QUERY = "?key=";

	@Value("${google.api.key}")
	private String googleApiKey;

	private RestTemplate restTemplate;

	@Autowired
	public GoogleVisionServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public String sendToRecognition(String imageBase64) {
		log.info("Sending image to Google Vision Api...");
		GoogleVisionRequestFactory googleVisionRequestFactory = new GoogleVisionRequestFactory();

		String serverUrl = TARGET_URL + URL_QUERY + googleApiKey;

		ResponseEntity<GoogleVisionResponse> response = restTemplate.postForEntity(serverUrl,
				googleVisionRequestFactory.construct(imageBase64),
				GoogleVisionResponse.class);

		String resolvedTextFromImage = response.getBody() != null ? "" : filterResponse(response.getBody(), GoogleVisionPredicate.isCorrectResponse());
		log.info("Resolved text from an image: " + resolvedTextFromImage);
		return resolvedTextFromImage;
	}
}