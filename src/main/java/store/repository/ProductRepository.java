package store.repository;

import store.model.dao.ProductDao;
import store.model.dao.PromotionDao;
import store.model.domain.ProductDomain;
import store.model.domain.PromotionDomain;

import java.util.List;

public class ProductRepository {

    //File I/O Call And Product Calculator Product값을 계산하는 함수 정의
    private final ProductDao productDao;
    private final String productFilePath;

    public ProductRepository(ProductDao productDao, String productFilePath) {
        this.productDao = productDao;
        this.productFilePath = productFilePath;
    }

    public List<ProductDomain> findAllProducts() {
        return productDao.findAllProducts(productFilePath);
    }

    public void saveProduct(ProductDomain product) {
        productDao.saveProduct(productFilePath, product);
    }


}
