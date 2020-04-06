package cz.mailmuni.andirs.recipeapp.controllers;

import cz.mailmuni.andirs.recipeapp.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception) {
        return genericExceptionMethod(exception, "404 Not Found");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleBadRequest(Exception exception) {
        return genericExceptionMethod(exception, "400 Bad Request");
    }

    private ModelAndView genericExceptionMethod(Exception exception, String response) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("response", response);
        mav.addObject("message", exception.getMessage());
        mav.setViewName("error");
        return mav;
    }
}
