package store.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private InputView(){}

    public static String getInput(){
        return Console.readLine();
    }

    public static void closeInput(){
        Console.close();
    }

    public static String productInput(){
        OutputView.inputMsg();
        return  getInput();
    }

    public static boolean userCheckInput(String Msg){
        System.out.println(Msg);
        if(getInput().equals("Y")) return true;
        return false;
    }


}
