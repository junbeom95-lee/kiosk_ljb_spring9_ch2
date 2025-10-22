package kiosk;

public class MenuItem { //세부 메뉴 속성 가지는 클래스
    //속성
    private String name;    //이름
    private int price;      //가격
    private String info;    //설명

    //생성자
    public MenuItem(String name, int price, String info) {
        this.name = name;
        this.price = price;
        this.info = info;
    }
    //기능
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getInfo() {
        return info;
    }
}
