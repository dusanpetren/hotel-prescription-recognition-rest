package sk.seanstep.hotelprescriptionrecognition.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import sk.seanstep.hotelprescriptionrecognition.service.CodeGeneratorService;

/**
 * Rest Controller for Codes manipulation
 *
 * @author dusan.petren
 */
@Log4j2
@RestController
@RequestMapping("/code")
@AllArgsConstructor
public class CodeRestController {

	private CodeGeneratorService codeGeneratorService;

	@GetMapping("/generate")
	public ResponseEntity<String> generate() {
		final String code = codeGeneratorService.generate();
		log.info("Generated new UUID: " + code);
		return ResponseEntity.ok(code);
	}
}
