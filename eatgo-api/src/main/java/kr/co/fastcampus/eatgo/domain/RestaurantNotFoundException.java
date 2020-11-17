package kr.co.fastcampus.eatgo.domain;

public class RestaurantNotFoundException extends RuntimeException { //기존 Exception를 확장하는게 좋다
    public RestaurantNotFoundException(Long id) {
        super("Could not find restaurant " +id);
        //이 id에 해당하는 게 없다는 메세지
    }

}
