package store;

import store.controller.PosMachine;
import store.view.InputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        PosMachine posMachine = PosMachine.getInstance();
            posMachine.init();
        do{
            posMachine.frontLogic();
            posMachine.productInput();
            posMachine.receipt();
            posMachine.reset();
        }while(InputView.userCheckInput("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)"));
    }

}
