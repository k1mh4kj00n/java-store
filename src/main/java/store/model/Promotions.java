package store.model;

import java.time.LocalDate;

public class Promotions {
    private int code;
    private String name;
    private int buy;
    private int get;
    private LocalDate startDate;
    private LocalDate endDate;

    public Promotions(int code, String name, int buy, int get, LocalDate startDate, LocalDate endDate) {
        this.code = code;
        this.name = name;
        this.buy = buy;
        this.get = get;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getCode() { return code; }
    public String getName() { return name; }
    public int getBuy() { return buy; }
    public int getGet() { return get; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }

}
