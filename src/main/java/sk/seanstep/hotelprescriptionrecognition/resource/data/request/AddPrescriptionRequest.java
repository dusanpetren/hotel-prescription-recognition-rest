package sk.seanstep.hotelprescriptionrecognition.resource.data.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddPrescriptionRequest {

	private String imageBase64;

}
