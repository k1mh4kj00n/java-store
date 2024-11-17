package store.controller;

import store.model.dao.ProductDao;
import store.model.dao.PromotionDao;
import store.repository.ProductRepository;
import store.repository.PromotionRepository;
import store.service.FileIO;

public class PosMachine {
    private static final PosMachine instance = new PosMachine();

    private ProductRepository productRepository;
    private PromotionRepository promotionRepository;

    private static final String PRODUCT_FILE_PATH = "src/main/resources/products.md";
    private static final String PROMOTION_FILE_PATH = "src/main/resources/promotions.md";

    private PosMachine() {}

    public static PosMachine getInstance() {
        return instance;
    }

    public void init() {
        FileIO fileIO = new FileIO();

        ProductRepository productRepository = new ProductRepository(
                new ProductDao(fileIO), PRODUCT_FILE_PATH
        );

        PromotionRepository promotionRepository = new PromotionRepository(
                new PromotionDao(fileIO), PROMOTION_FILE_PATH
        );

        this.productRepository = productRepository;
        this.promotionRepository = promotionRepository;
    }
}
