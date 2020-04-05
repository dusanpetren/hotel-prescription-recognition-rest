
package sk.seanstep.hotelprescriptionrecognition.google.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"textAnnotations",
		"fullTextAnnotation"
})
public class Response {

	@JsonProperty("textAnnotations")
	private List<TextAnnotation> textAnnotations = null;
	@JsonProperty("fullTextAnnotation")
	private FullTextAnnotation fullTextAnnotation;


}