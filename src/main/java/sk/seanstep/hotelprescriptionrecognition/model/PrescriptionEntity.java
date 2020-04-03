package sk.seanstep.hotelprescriptionrecognition.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity representation of table prescripiton.
 *
 * @author dusan.petren
 */
@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionEntity {

	@Id
	private String id;
	private String code;
	private Date created_at;
}
