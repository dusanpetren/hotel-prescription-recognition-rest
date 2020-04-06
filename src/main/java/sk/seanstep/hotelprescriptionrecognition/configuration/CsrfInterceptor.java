package sk.seanstep.hotelprescriptionrecognition.configuration;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interceptor preventing XSS attacks.
 *
 * @author dusan.petren
 */
@Component
public class CsrfInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

		if (null != request.getHeader("Content-Type") && (request.getMethod().equals("POST") || request.getMethod().equals("PUT"))) {
			if (MediaType.APPLICATION_JSON_VALUE.equals(request.getHeader("Content-Type")))
				return true;
		} else {
			response.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
			return false;
		}
		return true;
	}
}