package sk.seanstep.hotelprescriptionrecognition.google.factory;

import java.util.Collections;
import sk.seanstep.hotelprescriptionrecognition.google.data.request.Feature;
import sk.seanstep.hotelprescriptionrecognition.google.data.request.FeatureTypeEnum;
import sk.seanstep.hotelprescriptionrecognition.google.data.request.GoogleVisionRequest;
import sk.seanstep.hotelprescriptionrecognition.google.data.request.GoogleVisionRequestBody;
import sk.seanstep.hotelprescriptionrecognition.google.data.request.Image;

/**
 * Factory class for Google Vision request.
 * Creates request for google api with image in base64 to send for recognition.
 *
 * @author dusan.petren
 */
public class GoogleVisionRequestFactory {

	public GoogleVisionRequest construct(String imageBase64) {
		GoogleVisionRequest googleVisionRequest = new GoogleVisionRequest();

		Image image = new Image();
		image.setContent(imageBase64);

		Feature feature = new Feature();
		feature.setType(FeatureTypeEnum.TEXT_DETECTION);

		GoogleVisionRequestBody request = new GoogleVisionRequestBody();
		request.setImage(image);
		request.setFeatures(Collections.singletonList(feature));

		googleVisionRequest.getRequests().add(request);
		return googleVisionRequest;
	}

}
