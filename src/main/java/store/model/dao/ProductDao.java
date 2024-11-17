package store.model.dao;

import store.model.domain.ProductDomain;
import store.service.FileIO;

import java.util.List;

//DB에 직접적으로 저장하는 함수 Session 을 가지고 있고 여기에서 읽기 및 저장을 호출 해야함

public class ProductDao {

    private final FileIO fileIO;

    public ProductDao(FileIO fileIO) {
        this.fileIO = fileIO;
    }

    public List<ProductDomain> findAllProducts(String filePath) {
        // 파일에서 상품 읽기
        return fileIO.findAllProducts(filePath);
    }

    public void saveProduct(String filePath, ProductDomain product) {
        // 상품 저장
    }

}
