package kiosk;

import java.util.Arrays;

public enum CustomerType {    //사용자 유형의 Enum 정의 및 각 사용자 유형에 따른 할인율 적용

    PATRIOT(1, "국가유공자", 0.1),  //국가유공자
    SOLDIER(2, "군인", 0.05),     //군인
    STUDENT(3, "학생", 0.03),     //학생
    PERSON(4, "일반", 0);         //일반

    //속성
    private final int code;         //유저가 고를 수 있는 수 (매핑하기 위한 속성)
    private final String name;      //유저가 얻을 수 있는 할인 선택지 이름
    private final double discount;  //할인율

    //생성자
    CustomerType(int code, String name, double discount) {
        this.code = code;
        this.name = name;
        this.discount = discount;
    }

    //기능
    /**
     * 할인 정보를 제공
     */
    public static void printCustomerType() {
        System.out.println("할인 정보를 입력해주세요.");
        Arrays.stream(values())
                .forEach(customerType ->
                        System.out.printf("%d. %-15s : %d%%\n", customerType.code, customerType.name, (int) (customerType.discount * 100)));
    }

    /**
     * 입력된 숫자를 통해 할인 사용자의 유형을 반환
     * @param num 할인 정보를 입력 받음 (CustomerType int code를 보고 입력)
     * @return 입력받은 num과 매칭된 CustomerType 상수
     */
    public static CustomerType fromCode(int num) {
        return Arrays.stream(values())
                .filter(customerType -> customerType.code == num)
                .findFirst()
                .orElseThrow(() -> new IndexOutOfBoundsException("num: " + num + " 는 없습니다. 다시 입력해주세요."));
    }

    /**
     * 할인율 적용된 가격을 계산하여 반환
     * @param price 총 가격 (total)
     * @return 총 가격에서 할인을 한 가격
     */
    public int applyDiscount(int price) {
        return ((int)(price * (1 - discount)) / 10 * 10);
    }
}
