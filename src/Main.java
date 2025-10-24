import kiosk.Kiosk;
import kiosk.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<MenuItem> menuItemList = new ArrayList<>(); //List 선언 및 초기화

        //add 함수를 통해 new MenuItem(이름, 가격, 설명) List에 삽입
        //메뉴아이템 생성 및 리스트에 추가
        menuItemList.add(new MenuItem("치즈햄버거", 10000, "이것이 버거인가 치즈인가 수원짱치즈버거"));
        menuItemList.add(new MenuItem("치킨햄버거", 11000, "이것이 버거인가 치킨인가 수원짱치킨버거"));
        menuItemList.add(new MenuItem("갈비햄버거", 12000, "이것이 버거인가 갈비인가 수원짱갈비버거"));
        menuItemList.add(new MenuItem("새우햄버거", 13000, "이것이 버거인가 새우인가 수원짱새우버거"));
        menuItemList.add(new MenuItem("버섯햄버거", 9500, "이것이 버거인가 버섯인가 수원짱버섯버거"));

        //Kiosk 객체를 생성하고 사용하는 main 함수에서 객체를 생성할 때 값을 넘겨줌
        Kiosk kiosk = new Kiosk(menuItemList);

        //키오스크 시작
        kiosk.start();
    }
}
