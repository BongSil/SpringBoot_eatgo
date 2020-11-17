package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.RestaurantService;
import kr.co.fastcampus.eatgo.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin //프론트엔드?
@RestController //컨트롤러 객체를 만들지 않아도 됨
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<Restaurant> list() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        return restaurants;
        }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id) {
        Restaurant restaurant = restaurantService.getRestaurant(id);
        //기본 정보 + 메뉴 정보

        //Restaurant restaurant = restaurantRepository.findById(id);
        //List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(id);
        //restaurant.setMenuItems(menuItems);

        return restaurant;
    }
    @PostMapping("/restaurants")
    public ResponseEntity<?> create(@Valid @RequestBody Restaurant resource) //RequestBody는 content를 가져오는 것
            throws URISyntaxException {

                Restaurant restaurant = restaurantService.addRestaurant(
                Restaurant.builder()
                    .name(resource.getName())
                    .address(resource.getAddress())
                    .build());

        URI location = new URI("/restaurants/"+restaurant.getId());
        return ResponseEntity.created(location).body("{}");
    }
    @PatchMapping("/restaurants/{id}")
    public String update(@PathVariable("id") Long id,
                         @Valid @RequestBody Restaurant resource) {
        String name = resource.getName();
        String address = resource.getAddress();
        restaurantService.updateRestaurant(id, name, address);
        return "{}";
    }

}
