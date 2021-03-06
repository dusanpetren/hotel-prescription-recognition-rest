
package sk.seanstep.hotelprescriptionrecognition.google.data.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"type"
})
public class Feature {

	@JsonProperty("type")
	private FeatureTypeEnum type;

}
