package store.service;

import store.model.domain.ProductDomain;
import store.model.domain.PromotionDomain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileIO {

    public List<ProductDomain> findAllProducts(String filePath) {
        int index = 0;
        int code = FileConstants.START_PRODUCT_CODE;
        String name;
        int price;
        int quantity;
        String promotion;
        String line;
        String[] data;
        List<ProductDomain> products = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            line = null;

            reader.readLine();

            while ((line = reader.readLine()) != null) {
                data = line.split(FileConstants.DELIMITER);
                name = data[FileConstants.READ_NAME];
                price = Integer.parseInt(data[FileConstants.READ_PRODUCT_PRICE]);
                quantity = Integer.parseInt(data[FileConstants.READ_PRODUCT_QUANTITY]);
                promotion = data[FileConstants.READ_PRODUCT_PROMOTION];
                if(promotion.equals("null")) { promotion = null; }

                products.add(new ProductDomain(code, name, price, quantity, promotion));
                code++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return products;

    }

    public List<PromotionDomain> findAllPromotions(String filePath) {
        int index = 0;
        int code = FileConstants.START_PROMOTION_CODE;
        String[] data;
        List<PromotionDomain> promotions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();
            String line = null;
            while ((line = reader.readLine()) != null) {
                data = line.split(FileConstants.DELIMITER);
                promotions.add(new PromotionDomain(code++, data[FileConstants.READ_NAME],
                        Integer.parseInt(data[FileConstants.READ_PROMOTION_BUY]),
                        Integer.parseInt(data[FileConstants.READ_PROMOTION_GET]),
                        LocalDate.parse(data[FileConstants.READ_PROMOTION_START_DATE]),
                        LocalDate.parse(data[FileConstants.READ_PROMOTION_END_DATE]))
                );
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return promotions;
    }

    private int countLines(String filePath) {
        int lines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while (reader.readLine() != null) {
                lines++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return lines;
    }
}
