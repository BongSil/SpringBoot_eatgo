package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.domain.RestaurantNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestaurantErrorAdvice { //Restaurant Error를 처리해줌

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND) //404에 관하여
    @ExceptionHandler(RestaurantNotFoundException.class)
    public String handleNotFound() {
        return "{}";

    }
}
