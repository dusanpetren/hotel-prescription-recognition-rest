package sk.seanstep.hotelprescriptionrecognition.service;

import org.easymock.EasyMock;
import org.easymock.TestSubject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.ArrayList;
import sk.seanstep.hotelprescriptionrecognition.repository.PrescriptionRepository;

class CodeGeneratorServiceTest {

	private static PrescriptionRepository prescriptionRepository;

	@TestSubject
	private static CodeGeneratorService codeGeneratorService;

	@BeforeAll
	public static void init() {
		prescriptionRepository = EasyMock.createMock(PrescriptionRepository.class);
		codeGeneratorService = new CodeGeneratorService(prescriptionRepository);
	}

	@Test
	void successfullyGenerateCode() {
		EasyMock.expect(prescriptionRepository.findAllByCode(EasyMock.anyString())).andReturn(new ArrayList<>());
		EasyMock.replay(prescriptionRepository);

		String code = codeGeneratorService.generate();

		EasyMock.verify(prescriptionRepository);
		Assert.notNull(code, "Code is null");
	}
}