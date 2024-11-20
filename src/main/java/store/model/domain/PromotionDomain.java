package store.model.domain;

import store.model.dto.PromotionDto;

import java.time.LocalDate;

public class PromotionDomain {
    private int code;
    private String name;
    private int buy;
    private int get;
    private LocalDate startDate;
    private LocalDate endDate;

    public PromotionDomain(int code, String name, int buy, int get, LocalDate startDate, LocalDate endDate) {
        this.code = code;
        this.name = name;
        this.buy = buy;
        this.get = get;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void update(PromotionDto promotionDto) {
        this.code = promotionDto.getCode();
        this.name = promotionDto.getName();
        this.buy = promotionDto.getBuy();
        this.get = promotionDto.getGet();
        this.startDate = promotionDto.getStartDate();
        this.endDate = promotionDto.getEndDate();
    }

    public int getCode() { return code; }
    public void setCode(int code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getBuy() { return buy; }
    public void setBuy(int buy) { this.buy = buy; }

    public int getGet() { return get; }
    public void setGet(int get) { this.get = get; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
}
