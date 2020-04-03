package sk.seanstep.hotelprescriptionrecognition.resource;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import sk.seanstep.hotelprescriptionrecognition.model.PrescriptionEntity;
import sk.seanstep.hotelprescriptionrecognition.repository.PrescriptionRepository;

/**
 * Rest Controller for Prescription Generation
 *
 * @author dusan.petren
 */
@Log4j2
@RestController
@RequestMapping("/code")
@AllArgsConstructor
public class GeneratorRestController {

	private PrescriptionRepository prescriptionRepository;

	@GetMapping("/all")
	@SendTo("/topic/greetings")
	public List<PrescriptionEntity> getAll() {
		return prescriptionRepository.findAll();
	}

	@MessageMapping("/add")
	@SendTo("/topic/greetings")
	public String add(@PathVariable String code) {
		log.info("Creating new Prescription with code:" + code);
		return prescriptionRepository.save(new PrescriptionEntity(null, code, null)).getId();
	}

	@GetMapping("/generate")
	public String generate() {
		final String uuid = UUID.randomUUID().toString();

		log.info("Generated new UUID: " + uuid);
		return uuid;
	}
}
