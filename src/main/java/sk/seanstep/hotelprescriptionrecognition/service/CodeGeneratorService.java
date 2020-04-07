package sk.seanstep.hotelprescriptionrecognition.service;

import org.springframework.stereotype.Service;

import java.util.UUID;
import lombok.AllArgsConstructor;

/**
 * Service for generating unique codes.
 *
 * @author dusan.petren
 */
@Service
@AllArgsConstructor
public class CodeGeneratorService {


	public String generate() {
		return UUID.randomUUID().toString();
	}

}
