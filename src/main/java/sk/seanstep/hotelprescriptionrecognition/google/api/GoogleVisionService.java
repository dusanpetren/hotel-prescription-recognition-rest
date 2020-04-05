package sk.seanstep.hotelprescriptionrecognition.google.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Service for handling Rest api calls to Google Vision Api.
 *
 * @author dusan.petren
 */
@Service
public class GoogleVisionService {

	@Value("google.api.url")
	private String googleApiUrl;

	@Value("google.api.key")
	private String googleApiKey;

	@Autowired
	private RestTemplate restTemplate;


}
