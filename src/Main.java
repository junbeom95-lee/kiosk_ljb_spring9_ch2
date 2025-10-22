import kiosk.MenuItem;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    //객체 생성
    static Scanner sc = new Scanner(System.in);
    static List<MenuItem> menuItemList = new ArrayList<>();

    public static void main(String[] args) {

        //메뉴아이템 생성 및 리스트에 추가
        menuItemList.add(new MenuItem("치즈햄버거", 10000, "이것이 버거인가 치즈인가 수원짱치즈버거"));
        menuItemList.add(new MenuItem("치킨햄버거", 11000, "이것이 버거인가 치킨인가 수원짱치킨버거"));
        menuItemList.add(new MenuItem("갈비햄버거", 12000, "이것이 버거인가 갈비인가 수원짱치즈버거"));
        menuItemList.add(new MenuItem("새우햄버거", 13000, "이것이 버거인가 새우인가 수원짱치즈버거"));

        //TODO
        //1. 메뉴 입력받기
        while (true) {
            //1. 메뉴 설정
            System.out.print("메뉴의 이름을 입력해주세요: ");
            String name = sc.nextLine();
            System.out.print("메뉴의 가격을 입력해주세요: ");
            int price = getIntInput();
            System.out.print("메뉴의 설명을 입력해주세요: ");
            String info = sc.nextLine();
            //2. 입력 받은 값을 객체에 담아 리스트에 저장
            MenuItem menuItem = new MenuItem(name, price, info);
            menuItemList.add(menuItem);

            //3. 더 저장할 지 물어봄
            System.out.print("입력을 종료하시겠습니까? ");
            int n = getIntInput();
            if (0 == n) break;
        }

        //2. 메뉴 출력하기
        int exit;
        do {
            //메뉴 출력
            System.out.println("[ SHAKESHACK MENU ]");
            for(int i = 0 ; i < menuItemList.size(); i++) {
                MenuItem mI = menuItemList.get(i);
                System.out.printf("%d. %-13s | W %1.1f | %s\n", i + 1, mI.getName(), (float)mI.getPrice() * 0.001, mI.getInfo());
            }
            System.out.printf("0. %-13s   | 종료\n", "종료");

            //메뉴 선택
            exit = getIntInput();   //InputMismatchException 오류 발생 가능

        } while (0 != exit);

        sc.close(); //Scanner 종료
        System.out.println("프로그램을 종료합니다.");

    }
    public static int getIntInput() {
        String num;
        while (true) {
            try {
                num = sc.nextLine();
                return Integer.parseInt(num);
            } catch (InputMismatchException ignored) {
                System.out.println("다시 입력해 주세요.");
            } catch (NumberFormatException ignored) {
                System.out.println("다시 입력해 주세요.");
                sc.nextLine();
            }
        }
    }
}
