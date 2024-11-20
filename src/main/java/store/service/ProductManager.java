package store.service;

import store.model.dao.ProductDao;
import store.model.dto.ProductDto;
import store.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ProductManager {

    private final ProductRepository productRepository;
    private static final String PRODUCT_FILE_PATH = "src/main/resources/products.md";

    public ProductManager(FileIO productFile) {
        this.productRepository = new ProductRepository(
                new ProductDao(productFile), PRODUCT_FILE_PATH
        );
    }

    public List<ProductDto> getProducts() {
        return productRepository.findAllProducts()
                .stream()
                .map(productDomain -> new ProductDto(
                        productDomain.getCode(),
                        productDomain.getName(),
                        productDomain.getPrice(),
                        productDomain.getQuantity(),
                        productDomain.getPromotion()
                ))
                .collect(Collectors.toList());
    }
}
