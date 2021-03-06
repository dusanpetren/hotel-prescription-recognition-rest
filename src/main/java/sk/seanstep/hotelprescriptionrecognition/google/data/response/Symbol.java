
package sk.seanstep.hotelprescriptionrecognition.google.data.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"property",
		"boundingBox",
		"text"
})
public class Symbol {

	@JsonProperty("property")
	private Property property;
	@JsonProperty("boundingBox")
	private BoundingBox boundingBox;
	@JsonProperty("text")
	private String text;

}