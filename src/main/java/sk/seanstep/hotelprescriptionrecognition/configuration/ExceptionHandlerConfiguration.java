package sk.seanstep.hotelprescriptionrecognition.configuration;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Global exception handler.
 *
 * @author dusan.petren
 */
@ControllerAdvice
public class ExceptionHandlerConfiguration extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(NullPointerException ex) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error");
		modelAndView.addObject("errorMsg", ex.getClass());
		//Do something additional if required
		return modelAndView;
	}
}
