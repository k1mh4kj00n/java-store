package store.service;

import store.model.dto.ProductDto;
import store.model.dto.PromotionDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SaleService {

    private List<ProductDto> products ;
    private List<PromotionDto> promotions;

    public SaleService(ProductManager productManager, PromotionManager promotionManager) {
        this.products = productManager.getProducts();
        this.promotions = promotionManager.getPromotions();
    }

    public List<ProductDto> getProductList() {
        return products;
    }
    public List<PromotionDto> getPromotionList() {
        return promotions;
    }

    public Map<String, Integer> userCart(String readData){
        Map<String, Integer> cart = new HashMap<>();
        List<String> ProductAndQuantity = Parser.splitStrToList(readData, ",");

        for (String selectProduct : ProductAndQuantity) {
            List<String> productAndQuantity = Parser.splitStrToList(selectProduct, "-");
            String productName = Parser.replaceData(productAndQuantity.get(0).trim());
            int quantity = Integer.parseInt(Parser.replaceData(productAndQuantity.get(1).trim()));

            cart.put(productName, cart.getOrDefault(productName, 0) + quantity);
        }

        return cart;
    }

}
