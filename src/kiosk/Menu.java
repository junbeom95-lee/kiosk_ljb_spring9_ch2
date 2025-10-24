package kiosk;

import java.util.List;

public class Menu { //MenuItem 클래스를 관리하는 클래스
    //속성
    //MenuItem 클래스를 List로 관리
    private String name;                    //메뉴 이름
    private List<MenuItem> menuItemList;    //사이드 메뉴 리스트

    //생성자
    public Menu(String name, List<MenuItem> menuItemList) {
        this.name = name;
        this.menuItemList = menuItemList;
    }

    //기능
    /**
     * MenuItemList 리턴하는 함수
     */
    public List<MenuItem> getMenuItemList() {
        return menuItemList;
    }

    /**
     * Menu의 이름을 리턴하는 함수
     */
    public String getName() {
        return name;
    }
}
