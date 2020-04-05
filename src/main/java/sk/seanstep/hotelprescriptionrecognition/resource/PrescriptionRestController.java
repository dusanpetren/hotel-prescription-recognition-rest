package sk.seanstep.hotelprescriptionrecognition.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import sk.seanstep.hotelprescriptionrecognition.google.api.GoogleVisionService;
import sk.seanstep.hotelprescriptionrecognition.model.PrescriptionEntity;
import sk.seanstep.hotelprescriptionrecognition.repository.PrescriptionRepository;
import sk.seanstep.hotelprescriptionrecognition.resource.data.request.AddPrescriptionRequest;

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

	private PrescriptionRepository prescriptionRepository;
	private GoogleVisionService googleVisionService;

	@GetMapping("/all")
	public ResponseEntity<List<PrescriptionEntity>> getAll() {
		log.info("Getting all prescriptions");
		return ResponseEntity.ok(prescriptionRepository.findAll());
	}

	@GetMapping("/{codeId}")
	public ResponseEntity<List<PrescriptionEntity>> getAllForCodeId(@PathVariable String codeId) {
		log.info("Getting all prescription for code:" + codeId);
		return ResponseEntity.ok(prescriptionRepository.findAllByCode(codeId));
	}

	@PostMapping("/add")
	public String recognizeImage(@RequestBody AddPrescriptionRequest addPrescriptionRequest) {
		log.info("Sending image to recognition.");
		return this.googleVisionService.sendToRecognition(addPrescriptionRequest.getImageBase64());
	}
}