package kiosk;

import java.util.HashMap;
import java.util.Map;

public class Cart { //장바구니 기능
    //속성
    private Map<Menu, Integer> cart = new HashMap<>();

    //생성자

    //기능

    //TODO
    //장바구니 추가 기능 (Menu 메뉴 (메뉴명, 수량, 가격 ,정보), Int 수량)HashMap.put()사용
    //장바구니 수정 기능 (Menu 메뉴 (키 값), int 수정 수량) -> 0이면 장바구니 삭제 기능 HashMap.remove()(Menu 메뉴 (키 값))
    //유효하지 않은 메뉴 번호 입력 -> Kiosk 클래스의 printSelectMenuItem 이미 구현
    //장바구니에 물건 확인 <- 물건이 들어있으면 Order Menu 추가로 출력
    //장바구니에 담긴 모든 메뉴와 총 금액 출력

}
