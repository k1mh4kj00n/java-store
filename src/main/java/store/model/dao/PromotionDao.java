package store.model.dao;

import store.model.domain.PromotionDomain;
import store.service.FileIO;

import java.util.List;

public class PromotionDao {
    private final FileIO fileIO;

    public PromotionDao(FileIO fileIO) {
        this.fileIO = fileIO;
    }

    public List<PromotionDomain> findAllPromotions(String filePath) {
        // 파일에서 프로모션 읽기
    }

    public void savePromotion(String filePath, PromotionDomain promotion) {
        // 프로모션 저장
    }
}
