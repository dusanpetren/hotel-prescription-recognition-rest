
package sk.seanstep.hotelprescriptionrecognition.google.data.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"detectedLanguages"
})
public class Property {

	@JsonProperty("detectedLanguages")
	private List<DetectedLanguage> detectedLanguages = null;

}