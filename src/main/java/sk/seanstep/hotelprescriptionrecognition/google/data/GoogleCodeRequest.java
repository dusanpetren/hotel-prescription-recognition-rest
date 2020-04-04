package sk.seanstep.hotelprescriptionrecognition.google.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Base64 encoded String of picture.
 *
 * @author dusan.petren
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GoogleCodeRequest {

	private String imageBase64;

}
