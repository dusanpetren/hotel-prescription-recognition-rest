package sk.seanstep.hotelprescriptionrecognition.google.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Service for handling Rest api calls to Google Vision Api.
 *
 * @author dusan.petren
 */
@Service
public class GoogleVisionService {

	@Autowired
	private RestTemplate restTemplate;


}
