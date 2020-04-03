package sk.seanstep.hotelprescriptionrecognition;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;
import sk.seanstep.hotelprescriptionrecognition.model.PrescriptionEntity;
import sk.seanstep.hotelprescriptionrecognition.repository.PrescriptionRepository;

@SpringBootApplication
public class HotelPrescriptionRecognitionApplication {

	@Bean
	public CommandLineRunner DbInitializer(PrescriptionRepository prescriptionRepository) {
		return args -> {
			prescriptionRepository
					.deleteAll();
			Stream.of(new PrescriptionEntity(UUID.randomUUID().toString(), "Peter", new Date()),
					new PrescriptionEntity(UUID.randomUUID().toString(), "Martin", new Date()),
					new PrescriptionEntity(UUID.randomUUID().toString(), "Sam", new Date()),
					new PrescriptionEntity(UUID.randomUUID().toString(), "Alex", new Date()))
					.forEach(prescriptionRepository::save);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(HotelPrescriptionRecognitionApplication.class, args);
	}

}
