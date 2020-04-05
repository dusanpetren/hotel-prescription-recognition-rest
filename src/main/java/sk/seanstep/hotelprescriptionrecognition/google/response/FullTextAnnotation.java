
package sk.seanstep.hotelprescriptionrecognition.google.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"pages",
		"text"
})
public class FullTextAnnotation {

	@JsonProperty("pages")
	private List<Page> pages = null;
	@JsonProperty("text")
	private String text;

}