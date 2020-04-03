package sk.seanstep.hotelprescriptionrecognition.service;

import org.springframework.stereotype.Service;

import java.util.UUID;
import lombok.AllArgsConstructor;
import sk.seanstep.hotelprescriptionrecognition.repository.PrescriptionRepository;

/**
 * Service for generating unique codes.
 *
 * @author dusan.petren
 */
@Service
@AllArgsConstructor
public class CodeGeneratorService {

	private final PrescriptionRepository prescriptionRepository;

	public String generate() {
		String generateCode = UUID.randomUUID().toString();

		return this.prescriptionRepository.findAllByCode(generateCode).isEmpty() ? generateCode : generate();
	}

}
