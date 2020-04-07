package sk.seanstep.hotelprescriptionrecognition.resource;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import sk.seanstep.hotelprescriptionrecognition.google.service.GoogleVisionService;
import sk.seanstep.hotelprescriptionrecognition.resource.data.request.ResolveImageRequest;

/**
 * Rest Controller for Prescription manipulation.
 *
 * @author dusan.petren
 */
@Log4j2
@RestController
@RequestMapping("/prescription")
@AllArgsConstructor
public class PrescriptionRestController {

	private GoogleVisionService googleVisionService;


	@PostMapping(value = "/resolve", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> recognizeImage(@RequestBody ResolveImageRequest resolveImage) {
		log.info("Sending image to recognition.");
		return ResponseEntity.ok(this.googleVisionService.sendToRecognition(resolveImage.getImageBase64()));
	}
}