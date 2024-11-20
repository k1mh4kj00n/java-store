package store.model.dao;

import store.model.domain.ProductDomain;
import store.model.domain.PromotionDomain;
import store.service.FileIO;

import java.util.List;

public class PromotionDao {
    private final FileIO fileIO;

    public PromotionDao(FileIO fileIO) {
        this.fileIO = fileIO;
    }

    public List<PromotionDomain> findAllPromotions(String filePath) {
        // 파일에서 상품 읽기
        return fileIO.findAllPromotions(filePath);
    }

    public void savePromotion(String filePath, ProductDomain product) {
        // 상품 저장
    }
}
