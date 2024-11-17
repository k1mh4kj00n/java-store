package store.model.dto;

import java.time.LocalDate;

public class PromotionDto {
    private int code;
    private String name;
    private int buy;
    private int get;
    private LocalDate startDate;
    private LocalDate endDate;

    public PromotionDto(int code, String name, int buy, int get, LocalDate startDate, LocalDate endDate) {
        this.code = code;
        this.name = name;
        this.buy = buy;
        this.get = get;
        this.startDate = startDate;
        this.endDate = endDate;
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
