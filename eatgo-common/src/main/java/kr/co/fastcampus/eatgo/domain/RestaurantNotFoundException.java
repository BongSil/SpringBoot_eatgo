package kr.co.fastcampus.eatgo.domain;

public class RestaurantNotFoundException extends RuntimeException {// 기존의 예외를 확장함

    public RestaurantNotFoundException(Long id) {
        super("Could not find restaurant" + id);
        //아이디에 해당하는 것이 없다는 메세지
    }
}
