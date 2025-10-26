package kiosk;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Kiosk {    //프로그램 순서 및 흐름 제어를 담당하는 클래스
    //속성
    private final Scanner sc = new Scanner(System.in);  //Scanner 선언
    private final List<Menu> category;                  //여러 메뉴를 담은 리스트

    //생성자
    public Kiosk(List<Menu> category) {
        this.category = category;
    }

    //기능
    /**
     * 키오스크 시작<p>
     * 메뉴를 출력하고 선택하는 로직을 가지는 메서드
     * main 함수에서 관리하던 입력과 반복문 로직은 이제 start 함수를 만들어 관리
     */
    public void start() {

        String currentMenu = "MAIN";    //현재 ~~ 메뉴 ENUM
        int menuNumber = 0;             //카테고리 선택 숫자
        int selectNumber;               //메뉴 선택 숫자 유저가 고른 메뉴 아이템 넘버
        Menu menu;                      //메뉴
        List<MenuItem> itemList;        //메뉴 아이템 리스트

        //반복문 시작
        do {
            //메뉴 출력
            switch (currentMenu) {
                case "MAIN":    //메인 메뉴
                    //List와 Menu 클래스 활용하여 상위 카테고리 메뉴 출력
                    System.out.println("[ MAIN MENU ]");
                    for(int i = 0; i < category.size(); i++) {
                        System.out.printf("%d. %-13s\n", i + 1,  category.get(i).getName());
                    }

                    //TODO
                    //장바구니에 물건이 들어 있으면 아래와 같이 [ ORDER MENU ] 가 추가로 출력됩니다.
                    //만약에 장바구니에 물건이 들어 있지 않다면 [ ORDER MENU ] 가 출력되지 않습니다.
                    //미출력일 때 4,5 번을 누르면 예외를 던저줘야 합니다

                    System.out.println("0. 종료   | 종료");

                    //숫자 입력 받기 - 메뉴 선택
                    menuNumber = getIntInput();

                    //해당 메뉴로 전환
                    if(menuNumber == 1) currentMenu = "BURGER";
                    else if (menuNumber == 2) currentMenu = "DRINK";
                    else if (menuNumber == 3) currentMenu = "SIDE";
                    else if (menuNumber == 0) System.out.println("종료하겠습니다.");
                    else System.out.println("잘못된 숫자입니다.");
                    break;

                case "BURGER":  //버거 메뉴

                    System.out.println("[ BURGER MENU ]");
                    //입력받은 숫자가 올바르다면 인덱스로 활용하여 List에 접근하기
                    //List<Menu>에 인덱스로 접근하면 Menu만 추출 가능
                    menu =  category.get(menuNumber -1);

                    //Menu가 가진 List<MenuItem>을 반복문을 활용하여 BURGER 메뉴 출력
                    menu.printMenuItem();

                    //숫자 입력 받기 - 메뉴 선택
                    selectNumber = getIntInput();

                    //선택한 메뉴 아이템을 출력하고 메인 메뉴로 돌아가게끔 MAIN문자열을 넘겨주는 메서드
                    currentMenu = printSelectMenuItem(menu, currentMenu, selectNumber);
                    break;
                case "DRINK":

                    System.out.println("[ DRINK MENU ]");
                    //입력받은 숫자가 올바르다면 인덱스로 활용하여 List에 접근하기
                    menu =  category.get(menuNumber - 1);

                    //Menu가 가진 List<MenuItem>을 반복문을 활용하여 DRINK 메뉴 출력
                    menu.printMenuItem();

                    //숫자 입력 받기 - 메뉴 선택
                    selectNumber = getIntInput();

                    //선택한 메뉴 아이템을 출력하고 메인 메뉴로 돌아가게끔 MAIN문자열을 넘겨주는 메서드
                    currentMenu = printSelectMenuItem(menu, currentMenu, selectNumber);
                    break;
                case "SIDE":

                    System.out.println("[ SIDE MENU ]");
                    //입력받은 숫자가 올바르다면 인덱스로 활용하여 List에 접근하기
                    menu =  category.get(menuNumber -1);

                    //Menu가 가진 List<MenuItem>을 반복문을 활용하여 SIDE 메뉴 출력
                    menu.printMenuItem();

                    //숫자 입력 받기 - 메뉴 선택
                    selectNumber = getIntInput();

                    currentMenu = printSelectMenuItem(menu, currentMenu, selectNumber);
                    break;

                default:
                    System.out.println("접근 불가능한 menu입니다.");
                    System.out.println(" main 메뉴로 돌아갑니다. ");
                    currentMenu = "MAIN";
                    break;

                //TODO
                //장바구니를 확인 후 주문하는 카테고리 추가
                //아래와 같이 주문하시겠습니까?   <- print
                //[ Orders ] <- print

                // 메뉴아이템 출력 메뉴 이름 | 가격 | 설명 <- print

                //[ Total ] < <- print
                //total 가격 프린트

                //1. 주문  2. 메뉴판

                //1번 입력 시 주문이 완료되었습니다. 금액은 total 금액 출력

                //주문 후 장바구니 비우기 기능 추가

            }
        } while (0 != menuNumber);

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

    /**
     * 선택한 메뉴 아이템을 출력하고 메인 메뉴로 돌아가게끔 MAIN문자열을 넘겨주는 메서드
     * @param menu 선택한 메뉴아이템을 보여주기 위한 메뉴
     * @param currentMenu 현재 메뉴
     * @param selectNumber 유저가 고른 메뉴 아이템 넘버
     * @return 메뉴를 고른 후의 현재 메뉴 상태를 나타내는 문자열
     */
    public String printSelectMenuItem(Menu menu, String currentMenu, int selectNumber) {
        //메뉴 아이템을 담은 리스트
        List<MenuItem> itemList = menu.getMenuItemList();

        //메뉴 아이템을 고르기
        if(itemList.size() < selectNumber || selectNumber < 0) {    //범위를 벗어났을 때
            System.out.println("번호를 잘 보시고 입력해주세요.");
        } else if (0 == selectNumber) {                             //종료할 때
            currentMenu = "MAIN";   //메인 메뉴로 돌아가기
        } else {                                                    //메뉴 아이템을 골랐을 때
            //선택한 메뉴 출력
            menu.printPickMenuItem(selectNumber);
            currentMenu = "MAIN";   //메인 메뉴로 돌아가기
        }
        return currentMenu;
    }
}
