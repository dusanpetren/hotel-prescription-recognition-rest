package sk.seanstep.hotelprescriptionrecognition.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import sk.seanstep.hotelprescriptionrecognition.google.service.GoogleVisionService;
import sk.seanstep.hotelprescriptionrecognition.resource.data.request.AddPrescriptionRequest;

/**
 * Websocket controller.
 *
 * @author dusan.petren
 */
@Log4j2
@Controller
@AllArgsConstructor
public class WSMessageController {

	private GoogleVisionService googleVisionService;

	@MessageMapping("/add/{generateCode}")
	@SendTo("/socket/prescription/{generateCode}")
	public ResponseEntity<String> add(@DestinationVariable String generateCode, @Payload AddPrescriptionRequest code) throws Exception {
		log.info("Destination variable is: " + generateCode);
		log.info("Creating new Prescription with code:" + code);
		return ResponseEntity.ok(code.getImageBase64());
	}

	@MessageMapping("/join")
	@SendTo("/socket/prescription")
	public ResponseEntity<String> join(@Payload AddPrescriptionRequest code) throws Exception {
//		log.info("Joining for code:" + generateCode);
		return ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT).body(code.getImageBase64());
	}

	@MessageMapping(value = "/resolve")
	@SendTo("/socket/prescription")
	public String resolve(@Payload AddPrescriptionRequest addPrescriptionRequest) {
		log.info("Accepted encoded picture.");
		return this.googleVisionService.sendToRecognition(addPrescriptionRequest.getImageBase64());
	}

}