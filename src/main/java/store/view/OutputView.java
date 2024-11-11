package store.view;

public class OutputView {

    private static final String START_EQUAL_LINE = "=============";
    private static final String END_EQUAL_LINE = "===========";
    //        "=============================="

    private OutputView(){}

    public static void inputMsg(){
        System.out.println();
        System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
    }

    public static void welcomeMsg(){
        System.out.println("안녕하세요. W편의점입니다.");
        System.out.println("현재 보유하고 있는 상품입니다.");
        System.out.println();
    }

    public static void membershipMsg(){
        System.out.println("멤버십 할인을 받으시겠습니까? (Y/N)");
    }

    public static void revisitMsg(){
        System.out.println("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)");
    }

    public static void promotionCheck(String product, String count, boolean promotion){
        System.out.println("현재 " + product + " " + count + "개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)");
        System.out.println("현재 " + product + " " + count + "개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)");
    }


    public static void productMsg(String product, String price, String count, String Event){
        if (Event == null) Event = "";
        if (count.equals("0개")) {
            count = "재고 없음";
            Event = ""; // 재고가 없는 경우 이벤트는 출력하지 않음
        }
        String formattedPrice = price.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
        System.out.println("- " + product + " " + formattedPrice + "원 " + count + (Event.isEmpty() ? "" : " " + Event));
    }

}
