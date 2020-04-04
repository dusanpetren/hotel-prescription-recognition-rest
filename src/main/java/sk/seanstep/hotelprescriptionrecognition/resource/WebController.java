package sk.seanstep.hotelprescriptionrecognition.resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Thymeleaf Web Controller.
 *
 * @author dusan.petren
 */
@Controller
@RequestMapping("/web")
public class WebController {


	@GetMapping("/")
	public String index() {
		return "index";
	}
}
