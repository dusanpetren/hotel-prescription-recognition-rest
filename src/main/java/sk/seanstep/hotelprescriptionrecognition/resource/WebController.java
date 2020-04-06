package sk.seanstep.hotelprescriptionrecognition.resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping("/mobile/{code}")
	public String mobile(@PathVariable String code, Model model) {
		model.addAttribute("generatedCode", code);
		return "mobile-index";
	}

	@GetMapping("/code/{code}")
	public String codes(@PathVariable String code, Model model) {
		model.addAttribute("generatedCode", code);
		return "prescription-list";
	}
}
