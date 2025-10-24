package kiosk;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Kiosk {    //프로그램 순서 및 흐름 제어를 담당하는 클래스
    //속성
    //MenuItem을 관리하는 리스트가 필드로 존재
    private final Scanner sc = new Scanner(System.in);             //Scanner 선언
    private final List<Menu> category;

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
        int selectNumber = 0;             //유저가 고른 숫자
        Menu menu;
        List<MenuItem> itemList;
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
                    System.out.println("0. 종료   | 종료");

                    //숫자 입력 받기 - 메뉴 선택
                    selectNumber = getIntInput();

                    //해당 메뉴로 전환
                    if(selectNumber == 1) currentMenu = "BURGER";
                    else if (selectNumber == 2) currentMenu = "DRINK";
                    else if (selectNumber == 3) currentMenu = "SIDE";
                    else if (selectNumber == 0) System.out.println("종료하겠습니다.");
                    else System.out.println("잘못된 숫자입니다.");
                    break;

                case "BURGER":  //버거 메뉴

                    System.out.println("[ BURGER MENU ]");
                    //입력받은 숫자가 올바르다면 인덱스로 활용하여 List에 접근하기
                    //List<Menu>에 인덱스로 접근하면 Menu만 추출 가능
                    menu =  category.get(selectNumber -1);

                    //Menu가 가진 List<MenuItem>을 반복문을 활용하여 BURGER 메뉴 출력
                    itemList = menu.getMenuItemList();
                    menu.printMenuItem(itemList);

                    //숫자 입력 받기 - 메뉴 선택
                    selectNumber = getIntInput();

                    if(itemList.size() < selectNumber || selectNumber < 0) {
                        System.out.println("번호를 잘 보시고 입력해주세요.");
                    } else if (0 == selectNumber) {
                        currentMenu = "MAIN";
                        selectNumber = -1;
                    } else {
                        MenuItem pickItem = itemList.get(selectNumber - 1);
                        System.out.println("선택한 메뉴 -> 이름: " + pickItem.getName() + " 가격: " + (float) pickItem.getPrice() * 0.001 + " 설명: " + pickItem.getInfo());
                        currentMenu = "MAIN";
                    }
                    break;
                case "DRINK":

                    System.out.println("[ DRINK MENU ]");
                    //입력받은 숫자가 올바르다면 인덱스로 활용하여 List에 접근하기
                    menu =  category.get(selectNumber -1);

                    //Menu가 가진 List<MenuItem>을 반복문을 활용하여 DRINK 메뉴 출력
                    itemList = menu.getMenuItemList();
                    menu.printMenuItem(itemList);


                    //숫자 입력 받기 - 메뉴 선택
                    selectNumber = getIntInput();

                    if(itemList.size() < selectNumber || selectNumber < 0) {
                        System.out.println("번호를 잘 보시고 입력해주세요.");
                    } else if (0 == selectNumber) {
                        currentMenu = "MAIN";
                        selectNumber = -1;
                    } else {
                        MenuItem pickItem = itemList.get(selectNumber - 1);
                        System.out.println("선택한 메뉴 -> 이름: " + pickItem.getName() + " 가격: " + (float) pickItem.getPrice() * 0.001 + " 설명: " + pickItem.getInfo());
                        currentMenu = "MAIN";
                    }
                    break;
                case "SIDE":

                    System.out.println("[ SIDE MENU ]");
                    //입력받은 숫자가 올바르다면 인덱스로 활용하여 List에 접근하기
                    menu =  category.get(selectNumber -1);

                    //Menu가 가진 List<MenuItem>을 반복문을 활용하여 SIDE 메뉴 출력
                    itemList = menu.getMenuItemList();
                    menu.printMenuItem(itemList);

                    //숫자 입력 받기 - 메뉴 선택
                    selectNumber = getIntInput();

                    if(itemList.size() < selectNumber || selectNumber < 0) {
                        System.out.println("번호를 잘 보시고 입력해주세요.");
                    } else if (0 == selectNumber) {
                        currentMenu = "MAIN";
                        selectNumber = -1;
                    } else {
                        MenuItem pickItem = itemList.get(selectNumber - 1);
                        System.out.println("선택한 메뉴 -> 이름: " + pickItem.getName() + " 가격: " + (float) pickItem.getPrice() * 0.001 + " 설명: " + pickItem.getInfo());
                        currentMenu = "MAIN";
                    }
                    break;
                default:
                    System.out.println("접근 불가능한 menu입니다.");
                    System.out.println(" main 메뉴로 돌아갑니다. ");
                    currentMenu = "MAIN";
                    break;
            }
        } while (0 != selectNumber || !"MAIN".equals(currentMenu));

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
