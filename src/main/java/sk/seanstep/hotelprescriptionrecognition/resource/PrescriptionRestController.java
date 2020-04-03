package sk.seanstep.hotelprescriptionrecognition.resource;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import sk.seanstep.hotelprescriptionrecognition.model.PrescriptionEntity;
import sk.seanstep.hotelprescriptionrecognition.repository.PrescriptionRepository;

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

	@GetMapping("/all")
	public List<PrescriptionEntity> getAll() {
		log.info("Getting all prescriptions");
		return prescriptionRepository.findAll();
	}

	@GetMapping("/{codeId}")
	@SendTo("/topic/greetings")
	public List<PrescriptionEntity> getAllForCodeId(@PathVariable String codeId) {
		log.info("Getting all prescription for code:" + codeId);
		return prescriptionRepository.findAllByCode(codeId);
	}

	@MessageMapping("/add")
	public PrescriptionEntity add(@PathVariable String code) {
		log.info("Creating new Prescription with code:" + code);
		return prescriptionRepository.save(new PrescriptionEntity(null, code, null, null));
	}
}
