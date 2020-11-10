package kr.co.fastcampus.eatgo.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MenuItemRepository extends CrudRepository<MenuItem, Long>  {
    //CrudRepository를 쓰면 이에 대한 구현없이도 MenuItemRepository 사용할 수 있다
    List<MenuItem> findAllByRestaurantId(Long restaurantId);
}