package kr.co.fastcampus.eatgo.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long>{
    List<Restaurant> findAll();
    Optional<Restaurant> findById(Long id);
    //Optional은 null을 처리하지 않고 Restaurant가 있냐없냐를 구분하도록 만들어짐

    Restaurant save(Restaurant restaurant);

}
