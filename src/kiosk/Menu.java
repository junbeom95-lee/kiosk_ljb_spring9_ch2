package kiosk;

import java.util.List;

public class Menu { //MenuItem 클래스를 관리하는 클래스
    //속성
    //MenuItem 클래스를 List로 관리
    List<MenuItem> menuItemList;

    //생성자
    public Menu(List<MenuItem> list) {
        this.menuItemList = list;
    }

    //기능
    /**
     * List에 들어있는 MenuItem을 순차적으로 보여주는 함수
     */
    public void printMenuItemList() {
        System.out.println("[ 수원짱버거 MENU ]");
        for(int i = 0 ; i < menuItemList.size(); i++) { //반복문을 활용해 List 안에 있는 MenuItem을 하나씩 출력
            MenuItem mI = menuItemList.get(i);
            System.out.printf("%d. %-13s | W %5.1f | %s\n", i + 1, mI.getName(), (float)mI.getPrice() * 0.001, mI.getInfo());
        }
        System.out.printf("0. %-13s  | 종료\n", "종료");
    }

    /**
     * List를 리턴하는 함수
     */
    public List<MenuItem> getMenuItemList() {
        return menuItemList;
    }

    //TODO 구조에 맞게 함수를 선언해놓고 가져다 사용할 것
}
