package kiosk;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Kiosk {    //프로그램 순서 및 흐름 제어를 담당하는 클래스
    //속성
    //MenuItem을 관리하는 리스트가 필드로 존재
    List<MenuItem> menuItemList;
    static Scanner sc = new Scanner(System.in);             //Scanner 선언

    //생성자
    //List<MenuItem> menuItems 는 Kiosk 클래스 생성자를 통해 값을 할당
    public Kiosk(List<MenuItem> list) {
        this.menuItemList = list;
    }

    //기능
    /**
     * 키오스크 시작<p>
     * 메뉴를 출력하고 선택하는 로직을 가지는 메서드
     * main 함수에서 관리하던 입력과 반복문 로직은 이제 start 함수를 만들어 관리
     */
    public void start() {

        //2. 메뉴 출력하기
        int selectNum;
        do {
            //메뉴 출력
            System.out.println("[ 수원짱버거 MENU ]");
            for(int i = 0 ; i < menuItemList.size(); i++) { //반복문을 활용해 List 안에 있는 MenuItem을 하나씩 출력
                MenuItem mI = menuItemList.get(i);
                System.out.printf("%d. %-13s | W %5.1f | %s\n", i + 1, mI.getName(), (float)mI.getPrice() * 0.001, mI.getInfo());
            }
            System.out.printf("0. %-13s  | 종료\n", "종료");

            //메뉴 선택 - 숫자를 입력 받기
            selectNum = getIntInput();

            if(menuItemList.size() < selectNum) {   //유효하지 않은 입력에 대해 오류 메시지를 출력
                System.out.println("번호를 잘 보시고 입력해주세요.");
            } else if (0 != selectNum) {
                //입력된 숫자에 따른 처리
                MenuItem pickItem = menuItemList.get(selectNum - 1);
                System.out.println("선택한 메뉴 -> 이름: " + pickItem.getName() + " 가격: " + (float) pickItem.getPrice() * 0.001 + " 설명: " + pickItem.getInfo());
            }
        } while (0 != selectNum);

        //프로그램 종료
        sc.close(); //Scanner 종료
        System.out.println("프로그램을 종료합니다.");
    }

    /**
     * 입력 받아 숫자로 parse 해주는 메서드
     * @return 문자열에서 숫자로 파싱된 값
     */
    public int getIntInput() {
        String num;
        while (true) {
            try {
                num = sc.nextLine();
                return Integer.parseInt(num);
            } catch (InputMismatchException | NumberFormatException ignored) {
                System.out.println("다시 입력해 주세요.");
                sc.nextLine();
            }
        }
    }
}
