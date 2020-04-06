package sk.seanstep.hotelprescriptionrecognition.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Web MVC configuration
 *
 * @author dusan.petren
 */
@Component
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

	@Autowired
	private RequestLoggingInterceptor requestLoggingInterceptor;


	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(requestLoggingInterceptor);
	}

}
