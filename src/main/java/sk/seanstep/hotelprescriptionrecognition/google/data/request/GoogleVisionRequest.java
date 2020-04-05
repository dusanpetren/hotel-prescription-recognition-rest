
package sk.seanstep.hotelprescriptionrecognition.google.data.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"requests"
})
public class GoogleVisionRequest {

	@JsonProperty("requests")
	private List<GoogleVisionRequestBody> requests = new ArrayList<>();


}
