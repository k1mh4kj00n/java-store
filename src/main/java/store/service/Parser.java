package store.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {

    private Parser(){}

    public static String replaceData(String readData){
        return readData.replaceAll("[\\[\\]]", "");
    }
    public static List<String> splitStrToList(String readData, String delimiter) {
        return Arrays.stream(readData.split(delimiter))
                .map(String::trim)
                .collect(Collectors.toList());
    }

}
