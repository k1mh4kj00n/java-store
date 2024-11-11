package store.model;

public class Products {
    private int code;
    private String name;
    private int price;
    private int quantity;
    private String promotion;

    public Products(int code, String name, int price, int quantity, String promotion) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
    }
    public int getCode() { return code; }
    public String getName() { return name; }
    public int getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public void setMinusQuantity(int quantity) { this.quantity -= quantity; }
    public String getPromotion() { return promotion; }

}
