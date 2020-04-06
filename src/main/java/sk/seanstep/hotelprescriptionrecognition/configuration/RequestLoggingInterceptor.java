package sk.seanstep.hotelprescriptionrecognition.configuration;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.log4j.Log4j2;

/**
 * Filter for Requests for logging
 *
 * @author dusan.petren
 */
@Log4j2
@Component
public class RequestLoggingInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (HttpMethod.POST.matches(request.getMethod())) {
			log.info("Method: " + request.getMethod() +
					" on url: " + request.getRequestURI() +
					" with body: " + request.getInputStream().toString());
		}
		return true;
	}
}
