
package sk.seanstep.hotelprescriptionrecognition.google.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"locale",
		"description",
		"boundingPoly"
})
public class TextAnnotation {

	@JsonProperty("locale")
	private String locale;
	@JsonProperty("description")
	private String description;
	@JsonProperty("boundingPoly")
	private BoundingPoly boundingPoly;

}