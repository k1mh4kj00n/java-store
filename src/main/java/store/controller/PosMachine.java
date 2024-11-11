package store.controller;

import store.model.Products;
import store.model.Promotions;
import store.service.FileIO;
import store.service.Parser;
import store.view.InputView;
import store.view.OutputView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import camp.nextstep.edu.missionutils.DateTimes;

public class PosMachine {
    private static final PosMachine instance = new PosMachine();
    private Products[] products;
    private Promotions[] promotions;
    private Map<String,Integer> userBuysTable = new HashMap<>();
    private Map<Integer,Integer> selectBusyProducts = new HashMap<>();
    private Map<Integer,Integer> promotionBusyProducts = new HashMap<>();

    private PosMachine() {}


    public static PosMachine getInstance() {
        return instance;
    }

    public void init(){
        FileIO fileIO = new FileIO();
        products = fileIO.readProducts("src/main/resources/products.md");
        promotions = fileIO.readPromotions("src/main/resources/promotions.md");
    }
    public void frontLogic(){
        OutputView.welcomeMsg();
        for(Products p : products){
            OutputView.productMsg(
                    p.getName(),
                    Integer.toString(p.getPrice()),
                    Integer.toString(p.getQuantity())+"개",
                    p.getPromotion()
            );
        }
    }

    public void reset(){
        selectBusyProducts.clear();
        promotionBusyProducts.clear();
    }

    public void productInput(){
    boolean isValid = false;
        while(!isValid){
            try{
                String userInput =  InputView.productInput();
                userBuysTable = userBuyProducts(userInput);
                storageMatch();
                isValid = true;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void storageMatch() {
        for (Map.Entry<String, Integer> item : userBuysTable.entrySet()) {
            List<Products> joinProducts = joinProducts(item.getKey(), products);

            if(joinProducts.isEmpty()){ return; } // Validation 제품없음
            if( joinProducts.stream().mapToInt(Products::getQuantity).sum() < item.getValue()){ throw new IllegalArgumentException("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");} // Validation 재고 부족함

            storageCheckManagement(item.getValue(), joinProducts);
        }
    }

    private void promotionCheck(Products product, int quantity) {
        Promotions promotion = matchPromotion(product.getPromotion(), promotions);

        int calculatedQuantity = quantity;
        if (quantity > product.getQuantity())
            calculatedQuantity = product.getQuantity();

        int buyQuantity = promotion.getBuy();
        int getQuantity = promotion.getGet();
        int minus = 0;

        if (calculatedQuantity < buyQuantity) return;

        int promotionSets = calculatedQuantity / (buyQuantity + getQuantity);
        int freeQuantity = promotionSets * getQuantity;
        int remainder = calculatedQuantity % (buyQuantity + getQuantity);

        if(!isPromotion(product)) return;

        if (remainder == 0 || (remainder == buyQuantity && product.getQuantity() < quantity) ) {
            selectBusyProducts.put(product.getCode(), selectBusyProducts.getOrDefault(product.getCode(), 0) - freeQuantity);
            promotionBusyProducts.put(product.getCode(), freeQuantity);
            return;
        }

        if (remainder >= buyQuantity) {
            String message = String.format("현재 %s은(는) %d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)", product.getName(), getQuantity);
            if (InputView.userCheckInput(message)) {
                selectBusyProducts.put(product.getCode(), selectBusyProducts.getOrDefault(product.getCode(), 0) - freeQuantity);
                freeQuantity += getQuantity;
                promotionBusyProducts.put(product.getCode(), freeQuantity);
            }
            return;
        }

        if((quantity > product.getQuantity()) ) minus = quantity - product.getQuantity();
        String message = String.format("현재 %s 중 %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)", product.getName(), remainder + minus);
        if (InputView.userCheckInput(message)) {
            selectBusyProducts.put(product.getCode(), selectBusyProducts.getOrDefault(product.getCode(), 0) - freeQuantity);
            promotionBusyProducts.put(product.getCode(), freeQuantity);
        }
    }

    private void storageCheckManagement(int quantity, List<Products> joinProducts) {
        Products promotionProduct = getPromotionProduct(joinProducts);
        Products checkProduct = getNotPromotionProduct(joinProducts);

        if (promotionProduct != null && isPromotion(promotionProduct)) {
            checkProduct = promotionProduct;
        }

        if (checkProduct.getQuantity() < quantity) {
            int shortage = quantity - checkProduct.getQuantity();
            selectBusyProducts.put(checkProduct.getCode(), checkProduct.getQuantity());

            if (checkProduct == promotionProduct) {
                promotionCheck(promotionProduct, quantity);
                checkProduct = getNotPromotionProduct(joinProducts);
            }
            selectBusyProducts.put(checkProduct.getCode(), shortage);
            return;
        }

        selectBusyProducts.put(checkProduct.getCode(), quantity);
        if (promotionProduct != null) {
            promotionCheck(promotionProduct, quantity);
        }
    }

    public void receipt(){
        double membershiprice = 0;
        int totalcount = 0;
        int price = 0;
        int totalPrice = 0;
        int promotionPrice = 0;
        int memberprice =0;
        boolean member = membershipCheck();
        System.out.println("==============W 편의점================");
        System.out.println("상품명\t\t수량\t금액");

        for (Map.Entry<Integer, Integer> item : selectBusyProducts.entrySet()) {
            totalcount += item.getValue();
            joinproduct(item.getKey()).setMinusQuantity(item.getValue());
            price = joinproduct(item.getKey()).getPrice() * item.getValue();
            System.out.println(String.format("%s\t\t%d \t%s",joinproduct(item.getKey()).getName(), item.getValue(), Integer.toString(price).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") ));
            totalPrice += price;
        }
        System.out.println("=============증\t정===============");

        for (Map.Entry<Integer, Integer> item : promotionBusyProducts.entrySet()) {
            joinproduct(item.getKey()).setMinusQuantity(item.getValue());
            promotionPrice = joinproduct(item.getKey()).getPrice() * item.getValue();
            totalcount += item.getValue();
            totalPrice += promotionPrice;
            System.out.println(String.format("%s\t\t%d \t",joinproduct(item.getKey()).getName(), item.getValue()));
        }
        System.out.println("====================================");
        System.out.println( String.format("총구매액\t\t%d\t%s",totalcount, Integer.toString(totalPrice).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") ) );
        System.out.println( String.format("행사할인\t\t\t-%s", Integer.toString(promotionPrice).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") ) );
        if(member) {
            membershiprice = ((double)totalPrice * 0.3) / 1000 ;
            memberprice = (int)membershiprice * 1000;
        }
        System.out.println( String.format("멤버십할인\t\t\t-%s", Integer.toString(memberprice).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",")));
        totalPrice = totalPrice - promotionPrice - (int)membershiprice * 1000;
        System.out.println( String.format("내실돈\t\t\t %s", Integer.toString(totalPrice).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",")   ) );
    }

    private boolean membershipCheck(){
        return InputView.userCheckInput("멤버십 할인을 받으시겠습니까? (Y/N)");
    }

    private String createPromotionMessage(Products product, int promotionSet) {
        return String.format("현재 %s은(는) %d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)", product.getName(), promotionSet);
    }

    private String createNoPromotionMessage(Products product, int promotionSet) {
        return String.format("현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)", product.getName(), promotionSet);
    }

    private boolean isPromotion(Products promotionProduct) {
        Promotions selectPromotion = matchPromotion(promotionProduct.getPromotion(), promotions);
        return matchPromotionDate(selectPromotion.getStartDate(), selectPromotion.getEndDate());
    }

    public Map<String,Integer> userBuyProducts(String buyProducts) {
        Map<String, Integer> buys = new HashMap<>();
        List<String> test1 = Parser.splitStrToList(buyProducts, ",");

        for (String s : test1) {
            test1 = Parser.splitStrToList(s, "-");
            String productName = Parser.replaceData(test1.getFirst());
            int quantity = Integer.parseInt(Parser.replaceData(test1.getLast()));
            buys.put(productName, quantity);
        }
        return buys;
    }

    public static List<Products> joinProducts(String productName, Products[] products) {
        return Arrays.stream(products)
                .filter(product -> product.getName().equalsIgnoreCase(productName))
                .collect(Collectors.toList());
    }

    public Products joinproduct(int code){
        return Arrays.stream(products).filter(product -> product.getCode() == code).findFirst().orElse(null);
    }

    public static Promotions matchPromotion(String promotionName, Promotions[] promotions) {
        return Arrays.stream(promotions)
                .filter(promotion -> promotion.getName().equalsIgnoreCase(promotionName))
                .findFirst()
                .orElse(null);
    }

    public Products getPromotionProduct(List<Products> products){
        return products.stream().filter(product -> product.getPromotion() != null).findFirst().orElse(null);
    }

    public Products getNotPromotionProduct(List<Products> products){
        return products.stream().filter(product -> product.getPromotion() == null).findFirst().orElse(null);
    }

    public boolean matchPromotionDate(LocalDate startPromotionDate, LocalDate endPromotionDate){
        LocalDateTime nowDate = DateTimes.now();
        LocalDate localDate = nowDate.toLocalDate();
        return (localDate.isAfter(startPromotionDate) || localDate.isEqual(startPromotionDate)) &&
                (localDate.isBefore(endPromotionDate) || localDate.isEqual(endPromotionDate));
    }





}
