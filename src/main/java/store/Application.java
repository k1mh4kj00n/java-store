package store;

import store.controller.PosMachine;
import store.service.FileIO;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현

        FileIO fileIO = new FileIO();
        PosMachine posMachine = PosMachine.getInstance();
        posMachine.init(fileIO);
        posMachine.run();
    }

}
