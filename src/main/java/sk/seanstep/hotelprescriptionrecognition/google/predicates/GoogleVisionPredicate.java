package sk.seanstep.hotelprescriptionrecognition.google.predicates;

import java.util.function.Predicate;
import sk.seanstep.hotelprescriptionrecognition.google.data.response.GoogleVisionResponse;
import sk.seanstep.hotelprescriptionrecognition.google.data.response.Response;

/**
 * Predicate for google vision api response.
 *
 * @author dusan.petren
 */

public class GoogleVisionPredicate {
	public static Predicate<Response> isCorrectResponse() {
		return p -> p.getFullTextAnnotation() != null &&
				p.getFullTextAnnotation().getText() != null &&
				!p.getFullTextAnnotation().getText().isEmpty();
	}

	public static String filterResponse(GoogleVisionResponse response,
	                                    Predicate<Response> predicate) {
		return response.getResponses()
				.stream().filter(predicate)
				.findFirst().map(e -> e.getFullTextAnnotation().getText()).orElse("");
	}
}
