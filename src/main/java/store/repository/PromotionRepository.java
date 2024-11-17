package store.repository;

import store.model.dao.PromotionDao;
import store.model.domain.PromotionDomain;

import java.util.List;

public class PromotionRepository {
    private final PromotionDao promotionDao;
    private final String promotionFilePath;

    public PromotionRepository(PromotionDao promotionDao, String promotionFilePath) {
        this.promotionDao = promotionDao;
        this.promotionFilePath = promotionFilePath;
    }

    public List<PromotionDomain> findAllPromotions() {
        return promotionDao.findAllPromotions(promotionFilePath);
    }

    public void savePromotion(PromotionDomain promotion) {
        promotionDao.savePromotion(promotionFilePath, promotion);
    }
}
