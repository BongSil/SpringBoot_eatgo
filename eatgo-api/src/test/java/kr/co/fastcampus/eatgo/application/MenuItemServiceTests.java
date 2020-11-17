package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.MenuItem;
import kr.co.fastcampus.eatgo.domain.MenuItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;



public class MenuItemServiceTests {

    private MenuItemService menuItemService;

    @Mock
    private MenuItemRepository menuItemRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        menuItemService = new MenuItemService(menuItemRepository);
    }

    @Test
    public void bulkUpdate() {
        List<MenuItem> menuItems = new ArrayList<MenuItem>();

        menuItems.add(MenuItem.builder()  //새로추가
                .name("Kimchi")
                .build());
        menuItems.add(MenuItem.builder() //업데이트
                .id(12L).name("Gukbob")
                .build());
        menuItems.add(MenuItem.builder() //삭제
                .id(1004L).destroy(true).name("Gukbob")
                .build());

        menuItemService.bulkUpdate(1L,menuItems);

        verify(menuItemRepository, times(2)).save(any());
        verify(menuItemRepository, times(1)).deleteById(eq(1004L));
    }

}