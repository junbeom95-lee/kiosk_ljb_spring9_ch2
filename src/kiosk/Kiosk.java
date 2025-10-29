package kiosk;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Kiosk {    //프로그램 순서 및 흐름 제어를 담당하는 클래스
    //속성
    private final Scanner sc;                   //Scanner
    private final List<Menu> category;          //여러 메뉴를 담은 리스트
    private final Cart cart;                    //장바구니 클래스
    private MenuType menuType = MenuType.MAIN;  //메뉴 상태 (현재 메뉴의 상태)

    //생성자
    public Kiosk(List<Menu> category, Cart cart, Scanner scanner) {
        this.sc = scanner;
        this.category = category;
        this.cart = cart;
    }

    //기능
    /**
     * 키오스크 시작<p>
     * 메뉴를 출력하고 선택하는 로직을 가지는 메서드
     * main 함수에서 관리하던 입력과 반복문 로직은 이제 start 함수를 만들어 관리
     */
    public void start() {

        int menuNumber = 0;     //카테고리 선택 숫자 0이면 종료
        boolean running = true; //키오스크 동작 상태 true 면 동작 false면 종료

        //반복문 시작
        do {
            try {
                //메뉴 출력
                switch (menuType) {
                    case MAIN:    //메인 메뉴
                        printMain();                    //메인 메뉴 출력
                        menuNumber = handleMain();      //메인 메뉴 선택
                        if(0 == menuNumber) running = false;
                        break;
                    case MENU:    //메뉴를 골랐을 때
                        Menu menu = printMenu(menuNumber);  //메뉴 출력 (메뉴 아이템 리스트 출력)
                        handleMenu(menu);                   //메뉴 선택 (메뉴 아이템 선택)
                        break;
                    case ORDER:   //장바구니를 확인 후 주문할 때
                        int total = printOrderCart();   //장바구니 출력
                        handleOrderCart(total);         //사용자 유형별 주문 선택
                        break;
                    case CANCEL:  //진행중인 주문을 취소할 때
                        printCancel();                  //Cancel 화면 출력
                        handleCancel();                 //Cancel 화면 선택
                        break;
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            } catch (NullPointerException e) {
                System.out.println("존재하지 않은 메뉴입니다.");
            }

        } while (running);

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
     * 메인 메뉴 화면 출력 1. 카테고리 출력
     */
    private void printMain() {

        //1. List와 Menu 클래스 활용하여 상위 카테고리 메뉴 출력
        System.out.println("[ MAIN MENU ]");
        for (int i = 0; i < category.size(); i++) {
            System.out.printf("%d. %-13s\n", i + 1, category.get(i).getName());
        }
        System.out.println("0. 종료   | 종료");

        //1-a. ORDERS, CANCEL 출력
        //장바구니에 물건이 들어 있으면 아래와 같이 [ ORDER MENU ] 가 추가로 출력됩니다.
        //만약에 장바구니에 물건이 들어 있지 않다면 [ ORDER MENU ] 가 출력되지 않습니다.
        if (!cart.checkCart()) {
            int categorySize = category.size();
            System.out.println();
            System.out.println("[ ORDER MENU ] ");
            System.out.printf("%d. %-13s | %s\n", (categorySize + 1), "ORDERS", "장바구니를 확인 후 주문합니다.");
            System.out.printf("%d. %-13s | %s\n", (categorySize + 2), "CANCEL", "진행중인 주문을 취소합니다.");
        }
    }

    /**
     * 메인 메뉴 선택 - 2. 해당 메뉴를 선택하여 해당 메뉴로 전환
     */
    private int handleMain() {
        //1. 숫자 입력 받기 - 메뉴 선택
        int menuNumber = getIntInput();

        //2. 해당 메뉴 전환
        //2-a. 미출력일 때 4,5 번을 누르면 예외를 던저줘야 합니다.
        if(0 == menuNumber) return menuNumber;
        if (cart.checkCart()) {
            if (category.size() < menuNumber && category.size() + 2 < menuNumber) {
                throw new IndexOutOfBoundsException("잘못된 숫자를 선택하셨습니다.");
            }
        } else {
            //장바구니 확인 메뉴로 전환
            if (menuNumber == category.size() + 1) {
                menuType = MenuType.ORDER;
                return menuNumber;
            }
            //진행중인 주문 취소 메뉴
            if (menuNumber == category.size() + 2) {
                menuType = MenuType.CANCEL;
                return menuNumber;
            }
        }

        //2-b. 종료할 때 (0을 입력시)
        if (category.size() >= menuNumber) { //카테고리 선택 시
            //2-c. 메뉴로 전환
            menuType = MenuType.MENU;
            return menuNumber;
        } else {
            throw new IndexOutOfBoundsException("잘못된 숫자를 선택하셨습니다.");
        }
    }

    /**
     * 메뉴 화면 출력 - 1. 메뉴아이템 리스트 출력
     * @param menuNumber 선택한 카테고리 번호
     * @return 선택한 메뉴
     */
    private Menu printMenu(int menuNumber) {

        //1. 입력받은 인덱스로 활용하여 List에 접근하기
        Menu menu = category.get(menuNumber - 1);

        //2. 현재 메뉴 출력
        System.out.println("[ " + menu.getName()  + " MENU ]");

        //3. Menu가 가진 List<MenuItem>을 반복문을 활용하여 MenuItem 출력
        menu.printMenuItemList();

        return menu;
    }

    /**
     * 메뉴 선택 - 2. 메뉴 선택과 장바구니 추가
     */
    private void handleMenu(Menu menu) {
        //1. 숫자 입력 받기 - 메뉴 선택
        int selectNumber = getIntInput();

        //2. 선택한 메뉴아이템 리스트 불러오기
        List<MenuItem> menuItemList = menu.getMenuItemList();

        //3-a. 범위 밖의 번호일 때 throw 예외
        if (menuItemList.size() < selectNumber || selectNumber < 0) {
            throw new IndexOutOfBoundsException("번호를 잘 보시고 입력해주세요.");
        }

        //3-b. 0을 입력했을 떄 메인으로 돌아가기
        if(0 == selectNumber) {
            //0을 누르면 메인으로 돌아가기
            menuType = MenuType.MAIN;
            return;
        }

        //3-c. 고른 메뉴 아이템 객체화
        MenuItem pickItem = menuItemList.get(selectNumber - 1);

        //4. 메뉴아이템을 고를 시 메뉴 출력
        System.out.println("선택한 메뉴 -> 이름: " + pickItem.getName() + " 가격: W " + (float) pickItem.getPrice() * 0.001 + " 설명: " + pickItem.getInfo());

        //5. 장바구니에 담겨있는지 확인하여 수량을 반환
        int addedEa = cart.checkMenuItemEa(pickItem);

        //6. 장바구니에 담겨있으면 달라지는 내용 출력
        if (0 == addedEa) {
            System.out.print("수량을 정해주세요: ");
        } else {
            System.out.print("현재 담겨있는 개수는: " + addedEa + " 입니다. 새로운 수량을 정해주세요: ");
        }

        //7. 장바구니에 메뉴아이템을 몇개 넣을지 수량 입력 받기
        int ea = getIntInput();

        //8. 장바구니에 담기
        cart.addMenuItem(pickItem, ea);

        //9. 메인으로 돌아가기
        menuType = MenuType.MAIN;
    }

    /**
     * Order 화면 출력 - 1. 화면 출력
     * @return total - 총 가격
     */
    private int printOrderCart() {

        //1. 장바구니에 담은 메뉴들을 출력하기
        System.out.println("아래와 같이 주문하시겠습니까?");
        System.out.println(" [ ORDERS ]");
        cart.printMenuItemList();

        //2. 총 가격(total) 구하기
        int total = cart.getTotal();

        //3. 총 가격(total) 가격 출력
        System.out.println();
        System.out.println(" [ Total ]");
        System.out.println("W " + total);
        System.out.println("1. 주문           2. 메뉴판");

        return total;
    }

    /**
     * 사용자의 유형에 맞게 주문 - 2. 주문하기
     * @param total 총 금액
     */
    private void handleOrderCart(int total) {
        //1. 숫자 입력 받기 - 1. 주문 2. 메뉴판으로 돌아가기
        int selectNumber = getIntInput();

        //2. 1번 입력 시 주문이 완료되었습니다. 금액은 total 금액 출력 후 장바구니 비우기
        if (1 == selectNumber) {

            //3. 할인 정보 제공
            CustomerType.printCustomerType();

            //4. 할인 정보 입력 받기
            int customerTypeNum = getIntInput();

            //5. 할인 적용되는 가격 계산
            CustomerType customerType = CustomerType.fromCode(customerTypeNum);
            total = customerType.applyDiscount(total);

            //6. 총 금액 출력 및 주문 완료
            System.out.println("주문이 완료되었습니다. 금액은 W " + total + " 입니다.");
            cart.clearCart();       //주문 후 장바구니 비우기 기능 추가
            menuType = MenuType.MAIN;   //MAIN으로 돌아가기
        } else if (2 == selectNumber) { //5-b. 2번 입력시 메뉴판으로 돌아가기
            System.out.println("메뉴판으로 돌아갑니다.");
            menuType = MenuType.MAIN;   //MAIN으로 돌아가기
        } else throw new IndexOutOfBoundsException("잘못된 숫자를 선택하셨습니다.");
    }

    /**
     * Cancel 화면 출력 - 1. 현재 주문 취소 화면 출력
     */
    private void printCancel() {
        //1. CANCEL 메뉴얼 출력
        System.out.println(" [ CANCEL MENU ] ");
        System.out.println(" 장바구니를 비우시겠습니까?");
        System.out.println("1. 삭제           2. 메뉴판");
    }

    /**
     * Cancel 화면 선택 - 2. 모두 취소하거나 메인메뉴로 돌아가기
     */
    private void handleCancel() {
        //1. 숫자 입력 받기 - 1. 주문 2. 메뉴판으로 돌아가기
        int selectNumber = getIntInput();
        //2. 1번 입력시 장바구니 비우기, 2번 입력시 메뉴판으로 돌아가기
        if (1 == selectNumber) {
            System.out.println("장바구니를 비웠습니다.");
            cart.clearCart();       //장바구니 비우기
            menuType = MenuType.MAIN;   //MAIN으로 돌아가기
        } else if (2 == selectNumber) {
            System.out.println("메뉴판으로 돌아갑니다.");
            menuType = MenuType.MAIN;   //MAIN으로 돌아가기
        } else throw new IndexOutOfBoundsException("잘못된 숫자를 선택하셨습니다.");
    }
}
