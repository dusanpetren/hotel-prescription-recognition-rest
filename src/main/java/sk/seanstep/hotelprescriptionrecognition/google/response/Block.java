
package sk.seanstep.hotelprescriptionrecognition.google.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"property",
		"boundingBox",
		"paragraphs",
		"blockType"
})
public class Block {

	@JsonProperty("property")
	private Property property;
	@JsonProperty("boundingBox")
	private BoundingBox boundingBox;
	@JsonProperty("paragraphs")
	private List<Paragraph> paragraphs = null;
	@JsonProperty("blockType")
	private String blockType;


}
