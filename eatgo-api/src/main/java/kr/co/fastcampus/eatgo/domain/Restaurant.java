package kr.co.fastcampus.eatgo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import junit.runner.Version;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity //persistence
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue //id에 어떤값을 넣어야할지
    @Setter //setId에 대해
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String address;

    @Transient //임시로 처리했다
    @JsonInclude(JsonInclude.Include.NON_NULL) //NULL이 아닐때만 menuItems를 넣어줘라
    private List<MenuItem> menuItems;

    public String getInformation() {
        return name + " in " + address;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = new ArrayList<>(menuItems); //20강

        //for (MenuItem menuItem : menuItems) { //리스트 menuitems에 menuitem을 하나씩 넣어준다
        //    addMenuItem(menuItem);
            // for(A:B) B에서 차례대로 객체를 꺼내서 a에 넣겠다.(B에 더이상 꺼낼 객체가 없을 때까지)
        //}
    }

    public void updateInformation(String name, String address) {
        this.name = name;
        this.address = address;
    }
}