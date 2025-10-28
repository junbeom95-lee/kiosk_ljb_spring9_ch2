import kiosk.Cart;
import kiosk.Kiosk;
import kiosk.Menu;
import kiosk.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Menu 객체 생성을 통해 이름 설정
        //Menu 클래스 내 있는 List<MenuItem> 에 MenuItem 객체 생성하면서 삽입
        Menu burgerMenu = new Menu("BURGER", makeBurgerList());
        Menu drinkMenu = new Menu("DRINK", makeDrinkList());
        Menu sideMenu = new Menu("SIDE", makeSideList());

        List<Menu> menuList = new ArrayList<>(List.of(burgerMenu, drinkMenu, sideMenu));
        Scanner scanner = new Scanner(System.in);
        Cart cart = new Cart();

        //Kiosk 객체 생성
        Kiosk kiosk = new Kiosk(menuList, cart, scanner);

        //Kiosk 내 시작하는 함수 호출
        kiosk.start();
    }

    private static List<MenuItem> makeBurgerList() {
        List<MenuItem> burgerItemList = new ArrayList<>(); //List 선언 및 초기화

        //add 함수를 통해 new MenuItem(이름, 가격, 설명) List에 삽입
        //메뉴아이템 생성 및 리스트에 추가
        burgerItemList.add(new MenuItem("치즈버거", 10000, "이것이 버거인가 치즈인가 수원짱치즈버거"));
        burgerItemList.add(new MenuItem("치킨버거", 11000, "이것이 버거인가 치킨인가 수원짱치킨버거"));
        burgerItemList.add(new MenuItem("갈비버거", 12000, "이것이 버거인가 갈비인가 수원짱갈비버거"));
        burgerItemList.add(new MenuItem("새우버거", 13000, "이것이 버거인가 새우인가 수원짱새우버거"));
        burgerItemList.add(new MenuItem("버섯버거", 9500, "이것이 버거인가 버섯인가 수원짱버섯버거"));
        return burgerItemList;
    }

    private static List<MenuItem> makeDrinkList() {
        List<MenuItem> drinkItemList = new ArrayList<>();

        drinkItemList.add(new MenuItem("우롱밀크티", 5900, "최고의 버블티는 좋은 차에서 시작됩니다."));
        drinkItemList.add(new MenuItem("아메리카노", 4500, "시즌에 어울리는 원두 종류를 선정합니다."));
        drinkItemList.add(new MenuItem("딸기스무디", 4500, "딸기 본연의 진한 맛! "));
        return drinkItemList;
    }

    private static List<MenuItem> makeSideList() {
        List<MenuItem> sideItemList = new ArrayList<>();

        sideItemList.add(new MenuItem("치킨스낵랩", 3600, "매콤 바삭한  치킨 텐더와 고소한 치즈스틱의 완벽한 만남"));
        sideItemList.add(new MenuItem("애플파이", 2500, "바삭한 파이 속 은은한 시나몬 향과 달콤 사과 과육이 가득"));
        sideItemList.add(new MenuItem("프렌치 후라이", 3000, "통으로 썰어낸 감자를 튀겨내 남다른 맛과 바삭함!"));
        return sideItemList;
    }
}
