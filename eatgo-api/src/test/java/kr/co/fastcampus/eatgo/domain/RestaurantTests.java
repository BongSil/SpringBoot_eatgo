package kr.co.fastcampus.eatgo.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RestaurantTests {
    @Test
    public void creation() {
        Restaurant restaurant = new Restaurant(1004L,"Bob zip", "Seoul");
        assertThat(restaurant.getId(), is(1004L));
        assertThat(restaurant.getName(), is("Bob zip"));
        assertThat(restaurant.getAddress(), is("Seoul"));
        assertThat(restaurant.getMenuItems(), is("Kimchi"));
    }
    @Test
    public void information() {
       Restaurant restaurant = new Restaurant(1004L,"Bob zip", "Seoul");
       assertThat(restaurant.getInformation(), is("Bob zip in Seoul"));
    }
}