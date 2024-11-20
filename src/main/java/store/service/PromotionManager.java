package store.service;

import store.model.dao.PromotionDao;
import store.model.dto.PromotionDto;
import store.repository.PromotionRepository;

import java.util.List;
import java.util.stream.Collectors;

public class PromotionManager {

    private final PromotionRepository promotionRepository;
    private static final String PROMOTION_FILE_PATH = "src/main/resources/promotions.md";

    public PromotionManager(FileIO promotionFile) {
        this.promotionRepository = new PromotionRepository(
                new PromotionDao(promotionFile), PROMOTION_FILE_PATH
        );
    }

    public List<PromotionDto> getPromotions() {
        return promotionRepository.findAllPromotions()
                .stream()
                .map(productDomain -> new PromotionDto(
                        productDomain.getCode(),
                        productDomain.getName(),
                        productDomain.getBuy(),
                        productDomain.getGet(),
                        productDomain.getStartDate(),
                        productDomain.getEndDate()
                ))
                .collect(Collectors.toList());
    }
}


