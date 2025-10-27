package kiosk;

import java.util.HashMap;
import java.util.Map;

public class Cart { //MenuItem을 장바구니에 담는 기능을 가진 클래스
    //속성
    private final Map<MenuItem, Integer> cart;  //장바구니

    //생성자
    public Cart() {
        this.cart = new HashMap<>();
    }

    //기능

    /**
     * 장바구니에 메뉴아이템 추가 기능
     * @param menuItem 메뉴 아이템
     * @param ea       수량
     */
    public void addMenuItem(MenuItem menuItem, int ea) {
        //장바구니 추가 기능
        if (0 == ea) cart.remove(menuItem); //수량을 0으로 바꾸면 메뉴아이템 삭제
        else cart.put(menuItem, ea);        //메뉴 아이템 수량 수정
    }

    /**
     * 메뉴아이템이 장바구니에 담겨있는지 확인
     * @param menuItem 메뉴아이템 <- 키 값
     * @return 있으면 value를 반환 없으면 0 반환
     */
    public int checkMenuItemEa(MenuItem menuItem) {
        return cart.getOrDefault(menuItem, 0);
    }

    /**
     * 장바구니에 물건 확인
     * @return 물건 없으면 true
     */
    public boolean checkCart() {
        return cart.isEmpty();
    }

    /**
     * 장바구니에 담긴 모든 메뉴 총 금액 계산
     * @return 총 금액
     */
    public int getTotal() {
        //총 가격
        int total = 0;

        for (MenuItem menuItem : cart.keySet()) {
            int ea = cart.get(menuItem);
            total += menuItem.getPrice() * ea;
        }
        return total;
    }

    /**
     * 장바구니(Map)에 들어있는 MenuItem을 순차적으로 보여주는 메서드
     */
    public void printMenuItemList() {
        for (MenuItem menuItem : cart.keySet()) {
            int ea = cart.get(menuItem);
            // 메뉴아이템 출력 메뉴 이름 | 가격 | 설명
            System.out.printf("%-13s | W %5.1f | %5dea | %s\n", menuItem.getName(), (float) menuItem.getPrice() * 0.001 * ea, ea, menuItem.getInfo());
        }
    }

    /**
     * 장바구니 초기화 기능
     */
    public void clearCart() {
        cart.clear();
    }

}
