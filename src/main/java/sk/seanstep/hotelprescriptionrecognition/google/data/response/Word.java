
package sk.seanstep.hotelprescriptionrecognition.google.data.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"property",
		"boundingBox",
		"symbols"
})
public class Word {

	@JsonProperty("property")
	private Property property;
	@JsonProperty("boundingBox")
	private BoundingBox boundingBox;
	@JsonProperty("symbols")
	private List<Symbol> symbols = null;


}