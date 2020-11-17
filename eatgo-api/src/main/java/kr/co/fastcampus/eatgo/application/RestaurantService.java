package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {


    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    MenuItemRepository menuItemRepository;

    public RestaurantService(RestaurantRepository restaurantRepository,
                             MenuItemRepository menuItemRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
    }

    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants;
    }
    public Restaurant getRestaurant(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
       //() -> : 들어올때만 실행이 되야하므로
        //Optional이  값이 없을때 null로 둔다. 실무에서 이렇게 쓰면 안되서 나중에 처리

        List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(id);
        restaurant.setMenuItems(menuItems);

        return restaurant;
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        //restaurant.setId(1234L); 윗 문장에 아이디도 들어갔기 때문에
        return restaurantRepository.save(restaurant); //saved를 Refactor-Inline해주어야 함.
    }

    public Restaurant updateRestaurant(Long id, String name, String address) {
        //TODO: update Restaurant...

        Restaurant restaurant = restaurantRepository.findById(id).orElse(null); //findById가 optional이니까 orElse처리해두기

        restaurant.updateInformation(name, address);

        return restaurant;
    }
}
