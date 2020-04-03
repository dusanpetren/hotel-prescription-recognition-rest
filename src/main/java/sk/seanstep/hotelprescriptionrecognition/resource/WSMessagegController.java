package sk.seanstep.hotelprescriptionrecognition.resource;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import sk.seanstep.hotelprescriptionrecognition.model.PrescriptionEntity;
import sk.seanstep.hotelprescriptionrecognition.repository.PrescriptionRepository;

/**
 * Websocket controller.
 *
 * @author dusan.petren
 */
@Log4j2
@Controller
@AllArgsConstructor
public class WSMessagegController {

	private PrescriptionRepository prescriptionRepository;

	@MessageMapping("/add")
	@SendTo("/prescription/queue")
	public PrescriptionEntity add(String code) throws Exception {
		log.info("Creating new Prescription with code:" + code);
		return prescriptionRepository.save(new PrescriptionEntity(null, code, null, null));
	}

}