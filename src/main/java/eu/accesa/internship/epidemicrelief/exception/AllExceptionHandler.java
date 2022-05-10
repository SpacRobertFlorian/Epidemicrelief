package eu.accesa.internship.epidemicrelief.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AllExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public final ModelAndView handleIllegalArgumentException(CustomException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return new ModelAndView("error/internalserver/500");
    }
}
