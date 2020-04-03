package sk.seanstep.hotelprescriptionrecognition.resource;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import lombok.extern.log4j.Log4j2;

/**
 * Popis
 *
 * @author dusan.petren
 */
@Log4j2
@Controller
public class GreetingController {


	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public String greeting(String message) throws Exception {
		log.info("Creating new Prescription with code:" + "code");
		Thread.sleep(1000); // simulated delay
		return "Hello, " + HtmlUtils.htmlEscape(message) + "!";
	}

}