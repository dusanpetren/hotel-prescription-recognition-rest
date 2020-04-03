package sk.seanstep.hotelprescriptionrecognition.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import sk.seanstep.hotelprescriptionrecognition.model.PrescriptionEntity;

/**
 * Data access object for {@link PrescriptionEntity}
 *
 * @author dusan.petren
 */
public interface PrescriptionRepository extends MongoRepository<PrescriptionEntity, String> {

}
