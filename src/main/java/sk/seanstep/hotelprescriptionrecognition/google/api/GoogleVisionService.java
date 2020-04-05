package sk.seanstep.hotelprescriptionrecognition.google.api;

public interface GoogleVisionService {

	/**
	 * Method sends encodec image with Base64 encoding,
	 * to google vision api to resolve text on the image.
	 *
	 * @param imageBase64 - image encoded into base 64
	 * @return String - text on the image.
	 */
	String sendToRecognition(String imageBase64);

}
