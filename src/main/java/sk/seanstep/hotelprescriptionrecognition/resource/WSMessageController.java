package sk.seanstep.hotelprescriptionrecognition.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import sk.seanstep.hotelprescriptionrecognition.google.service.GoogleVisionService;
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
public class WSMessageController {

	private PrescriptionRepository prescriptionRepository;
	private GoogleVisionService googleVisionService;

	// TODO: 4/4/2020 petrend Add unique websocket mapping to specific users...
	@MessageMapping("/add")
	@SendTo("/socket/prescription")
	public PrescriptionEntity add(@NotNull String code) throws Exception {
		log.info("Creating new Prescription with code:" + code);
		return prescriptionRepository.save(new PrescriptionEntity(null, code, null, null));
	}

	// TODO: 4/4/2020 petrend Add unique websocket mapping to specific users...@destination variable
	@MessageMapping("/resolve")
	@SendTo("/socket/prescription")
	public ResponseEntity<String> generate(@NotNull String encodedImage) {
		log.info("Accepted encoded picture.");
//		googleVisionService.
		return ResponseEntity.ok("code");
	}

}