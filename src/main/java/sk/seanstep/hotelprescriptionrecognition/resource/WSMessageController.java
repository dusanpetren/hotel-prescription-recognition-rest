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

	@MessageMapping("/add/{generateCode}")
	@SendTo("/socket/prescription/{generateCode}")
	public ResponseEntity<String> add(@DestinationVariable String generateCode, @Payload AddPrescriptionRequest code) throws Exception {
		log.info("Sending message: " + code.getMessage() + " to the destination socket: " + generateCode);
		return ResponseEntity.ok(code.getMessage());
	}

	@MessageMapping("/join/{generateCode}")
	@SendTo("/socket/prescription/{generateCode}")
	public ResponseEntity<String> join(@DestinationVariable String generateCode) throws Exception {
		log.info("Joining for code:" + generateCode);
		return ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT).body("");
	}
}