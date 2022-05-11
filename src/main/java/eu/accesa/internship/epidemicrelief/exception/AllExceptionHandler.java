package eu.accesa.internship.epidemicrelief.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AllExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle all Custom Exceptions and return a ModelAndView
     *
     * @param e     is a {@link CustomException} to be handled
     * @param model is a {@link Model} to add attributes to a JSP page
     * @return a {@link ModelAndView}
     */
    @ExceptionHandler(CustomException.class)
    public final ModelAndView handleCustomException(CustomException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return new ModelAndView("error/500");
    }
}
