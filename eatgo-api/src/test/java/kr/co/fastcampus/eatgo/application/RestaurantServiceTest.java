package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RestaurantServiceTest {

    private RestaurantRepository restaurantRepository;
    private RestaurantService restaurantService;
    private MenuItemRepository menuItemRepository;

    @Before //일반적인 테스트에서 의존성 주입이 안되서 repository를 직접 넣기
    public void setUp() {
            restaurantRepository = new RestaurantRepositoryImpl();
            menuItemRepository = new MenuItemRepositoryImpl();
            restaurantService =new RestaurantService(restaurantRepository, menuItemRepository);
    }

    @Test
    public void getRestaurants() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();

        Restaurant restaurant = restaurants.get(0);

        assertThat(restaurant.getId(), is(1004L));
    }
    @Test
    public void getRestaurant() {
        Restaurant restaurant = restaurantService.getRestaurant(1004L);

        assertThat(restaurant.getId(), is(1004L));

        MenuItem menuItem = restaurant.getMenuItems().get(0);
                assertThat(menuItem.getName(), is("Kimchi"));
    }

}