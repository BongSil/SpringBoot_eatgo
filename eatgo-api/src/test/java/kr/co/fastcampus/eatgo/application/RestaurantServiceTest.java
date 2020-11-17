package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class RestaurantServiceTest {

    private RestaurantService restaurantService;

    @Mock //RestaurantService와 상관없으니 가짜로
    private RestaurantRepository restaurantRepository;

    @Mock//RestaurantService와 상관없으니 가짜로
    private MenuItemRepository menuItemRepository;

    @Before //일반적인 테스트에서 의존성 주입이 안되서 repository를 직접 넣기
    public void setUp() {
        MockitoAnnotations.initMocks(this); //위 Mock 초기화

        mockRestaurantRepository();
        mockMenuItemRepository();

        restaurantService =new RestaurantService(restaurantRepository, menuItemRepository);
    }
    private void mockRestaurantRepository() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();

        restaurants.add(restaurant);

        given(restaurantRepository.findAll()).willReturn(restaurants);
        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));
    }
    private void mockMenuItemRepository() {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(MenuItem.builder()
                .name("Kimchi")
                .build());
        given(menuItemRepository.findAllByRestaurantId(1004L)).willReturn(menuItems);

    }

    @Test
    public void getRestaurants() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();

        Restaurant restaurant = restaurants.get(0);

        assertThat(restaurant.getId(), is(1004L));
    }
    @Test
    public void getRestaurantWithExisted() {
        Restaurant restaurant = restaurantService.getRestaurant(1004L);

        assertThat(restaurant.getId(), is(1004L));

        MenuItem menuItem = restaurant.getMenuItems().get(0);

        assertThat(menuItem.getName(), is("Kimchi"));
    }
    @Test(expected = RestaurantNotFoundException.class)
    public void getRestaurantWithNotExisted() {
        restaurantService.getRestaurant(404L);

    }

    @Test
    public void addRestaurant() {
        given(restaurantRepository.save(any())).will(invocation -> {
           Restaurant restaurant = invocation.getArgument(0);
           restaurant.setId(1234L);
           return restaurant;
        });

        Restaurant restaurant = Restaurant.builder()
                .name("BeRyong")
                .address("Busan")
                .build();

        //given(restaurantRepository.save(any())).willReturn(saved);
        //restaurantRepository에서 save를 실행하고 나온 결과인 1234를 넣어줘야 함

        Restaurant created  = restaurantService.addRestaurant(restaurant);
        assertThat(created.getId(), is(1234L));
    }
    @Test
    public void updateRestaurant() {
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();

        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));//findById가 Optional이라서

        restaurantService.updateRestaurant(1004L, "Sool zip","Busan");

        assertThat(restaurant.getName(), is("Sool zip"));
        assertThat(restaurant.getAddress(), is("Busan"));
    }
}