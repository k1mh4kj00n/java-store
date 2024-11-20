package store.model.domain;

import store.model.dto.ProductDto;

//DB Table Mapping Class

public class ProductDomain {
    private int code;
    private String name;
    private int price;
    private int quantity;
    private String promotion;

    public ProductDomain(int code, String name, int price, int quantity, String promotion) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
    }

    public void update(ProductDto productDto) {
        this.code = productDto.getCode();
        this.name = productDto.getName();
        this.price = productDto.getPrice();
        this.quantity = productDto.getQuantity();
        this.promotion = productDto.getPromotion();
    }

    public int getCode() { return code; }
    public void setCode(int code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getPromotion() { return promotion; }
    public void setPromotion(String promotion) { this.promotion = promotion; }

}
