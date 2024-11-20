package store.controller;

import store.model.dto.ProductDto;
import store.service.FileIO;
import store.service.ProductManager;
import store.service.PromotionManager;
import store.service.SaleService;
import store.view.InputView;
import store.view.OutputView;

import java.util.List;
import java.util.Map;

public class PosMachine {
    private static final PosMachine instance = new PosMachine();

    private ProductManager productManager;
    private PromotionManager promotionManager;
    private SaleService saleService;

    private PosMachine() {}

    public static PosMachine getInstance() {
        return instance;
    }

    public void init(FileIO fileIO) {
        this.productManager = new ProductManager(fileIO);
        this.promotionManager = new PromotionManager(fileIO);
        this.saleService = new SaleService(productManager, promotionManager);
    }

    //@Controller Mapping Runner
    public void run(){
        saleController();
    }

    //@Sale Controller Mapping
    public void saleController(){
        List<ProductDto> products = saleService.getProductList();

        for(ProductDto product : products){
            OutputView.productMsg( product.getName(), String.valueOf(product.getPrice()), String.valueOf(product.getQuantity()) , product.getPromotion());
        }

        String readData = InputView.productInput();
        Map<String, Integer> userCart = saleService.userCart(readData); // buysTable

    }
    
}
