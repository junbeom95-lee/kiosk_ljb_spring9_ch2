import kiosk.Kiosk;
import kiosk.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //add 함수를 통해 new MenuItem(이름, 가격, 설명) List에 삽입
        //메뉴아이템 생성 및 리스트에 추가
        menuItemList.add(new MenuItem("치즈햄버거", 10000, "이것이 버거인가 치즈인가 수원짱치즈버거"));
        menuItemList.add(new MenuItem("치킨햄버거", 11000, "이것이 버거인가 치킨인가 수원짱치킨버거"));
        menuItemList.add(new MenuItem("갈비햄버거", 12000, "이것이 버거인가 갈비인가 수원짱치즈버거"));
        menuItemList.add(new MenuItem("새우햄버거", 13000, "이것이 버거인가 새우인가 수원짱치즈버거"));

        //1. 메뉴 입력받기
//        while (true) {
//            //1. 메뉴 설정
//            System.out.print("메뉴의 이름을 입력해주세요: ");
//            String name = sc.nextLine();
//            System.out.print("메뉴의 가격을 입력해주세요: ");
//            int price = getIntInput();
//            System.out.print("메뉴의 설명을 입력해주세요: ");
//            String info = sc.nextLine();
//            //2. 입력 받은 값을 객체에 담아 리스트에 저장
//            MenuItem menuItem = new MenuItem(name, price, info);
//            menuItemList.add(menuItem);
//
//            //3. 더 저장할 지 물어봄
//            System.out.print("입력을 종료하시겠습니까? ");
//            int n = getIntInput();
//            if (0 == n) break;
//        }


    }
}
