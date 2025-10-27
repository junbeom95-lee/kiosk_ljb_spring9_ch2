package kiosk;

import java.util.List;

public class Menu { //MenuItem 클래스를 관리하는 클래스
    //속성
    private final String name;                    //메뉴 이름
    private final List<MenuItem> menuItemList;    //사이드 메뉴 리스트 (MenuItem 클래스를 List로 관리)

    //생성자
    public Menu(String name, List<MenuItem> menuItemList) {
        this.name = name;
        this.menuItemList = menuItemList;
    }

    //기능
    /**
     * List에 들어있는 MenuItem을 순차적으로 보여주는 메서드
     */
    public void printMenuItemList() {
        for(int i = 0; i < menuItemList.size(); i++) {
            MenuItem mI = menuItemList.get(i);
            System.out.printf("%d. %-13s | W %5.1f | %s\n", i + 1, mI.getName(), (float)mI.getPrice() * 0.001, mI.getInfo());
        }
        System.out.printf("0. %-13s  | 종료\n", "종료");

    }

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
